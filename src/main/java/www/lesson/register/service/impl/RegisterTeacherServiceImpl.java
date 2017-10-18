package www.lesson.register.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import www.lesson.common.utils.ExcelFomart;
import www.lesson.common.utils.ExcelUtils;
import www.lesson.pojo.Teacher;
import www.lesson.pojo.User;
import www.lesson.register.dao.TeacherDao;
import www.lesson.register.service.RegisterService;
import www.lesson.system.service.SuperUserService;
import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Transactional  //事务同步
@Service
public class RegisterTeacherServiceImpl implements RegisterService<Teacher> {

    @Resource(name = "superUserServiceImpl")
    SuperUserService service;
    @Resource
    TeacherDao teacherDao;

    public void registerByExcel(File file) {


        try {


            InputStream in = new FileInputStream(file);

            //解析数据
            List<List<Object>> l1 = ExcelUtils.upExcel(in, file.getName());


            List<Teacher> teachers = new ArrayList<Teacher>();



            //遍历数据
            for(int i=0;i<l1.size();i++){
                List<Object> l2 = l1.get(i);

                //检测数据格式
                if(ExcelFomart.excelUserFomart(String.valueOf(l2.get(2)),
                        String.valueOf(l2.get(3)),String.valueOf(l2.get(4)))&&
                        ExcelFomart.excelIdFomart(String.valueOf(l2.get(0)))
                        )
                {   //赋值
                    Teacher teacher = new Teacher();
                    teacher.setId(String.valueOf(l2.get(0)));
                    teacher.setUserName(String.valueOf(l2.get(1)));
                    //判断性别
                    teacher.setWoman(String.valueOf(l2.get(2)));
                    teacher.setBirthday(String.valueOf(l2.get(3)));
                    teacher.setIdentification(String.valueOf(l2.get(4)));
                    teachers.add(teacher);
                }else{
                    throw new RuntimeException("第"+String.valueOf(i+2)+"行出现格式错误");
                }      //返回错误行数

            }




            for(int i=0;i<teachers.size();i++){
                User user = new User();
                Teacher t = teachers.get(i);



                //设置user数据
                user.setId(t.getId());
                user.setPassword(t.getIdentification());
                user.setRoleId("2");

                //注册user
                if(!service.insertUser(user)) {

                    throw new RuntimeException("第"+String.valueOf(i+2)+"行出现User注册错误");
                    //返回错误行
                }


                //注册teacher
                if(!teacherDao.insert(t)) {
                    throw new RuntimeException("第"+String.valueOf(i+2)+"行出现Teacher注册错误");      //返回错误行
                }



            }


        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }


    }




    public boolean register(Teacher teacher) {


        try {


            User u = new User();
            u.setRoleId("2");
            u.setId(teacher.getId());
            u.setPassword(teacher.getIdentification());
            service.insertUser(u);
            teacherDao.insert(teacher);

        }catch (Exception e){
            return false;
        }

        return true;
    }

    public boolean update(Teacher teacher) {



        try {


        teacherDao.updateByPrimaryKey(teacher);

        }catch (Exception e){
            return false;
        }

        return true;
    }

    public boolean delete(String id) {

        try{
            teacherDao.deleteByPrimaryKey(id);
            service.deleteUser(id);
        }catch( Exception e ){
            throw new RuntimeException("教师下有班级,课程不能删除");
        }


        return true;
    }

}
