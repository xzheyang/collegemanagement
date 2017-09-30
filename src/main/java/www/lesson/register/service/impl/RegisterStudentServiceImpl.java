package www.lesson.register.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import www.lesson.common.utils.ExcelFomart;
import www.lesson.common.utils.ExcelUtils;
import www.lesson.pojo.Class;
import www.lesson.pojo.Student;
import www.lesson.pojo.User;
import www.lesson.register.dao.ClassDao;
import www.lesson.register.dao.StudentDao;
import www.lesson.register.service.RegisterStudentService;
import www.lesson.system.service.SuperUserService;
import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@Transactional  //事务同步              要抛出异常才会事务失败
@Service
public class RegisterStudentServiceImpl implements RegisterStudentService {

    @Resource(name = "superUserServiceImpl")
    SuperUserService service;

    @Resource
    StudentDao studentDao;

    @Resource
    ClassDao classDao;


    public void registerStudentByExcel(File file) {

            /**
             *          先注册User(id=班级id+班级排位,默认密码是身份证号),再注册Student
             *
             **/


        try {


            InputStream in = new FileInputStream(file);



        //解析数据
        List<List<Object>> l1 = ExcelUtils.upExcel(in, file.getName());


        List<Student> students = new ArrayList<Student>();



        //遍历数据
        for(int i=0;i<l1.size();i++){
            List<Object> l2 = l1.get(i);

            //检测数据格式
            if(ExcelFomart.excelUserFomart(String.valueOf(l2.get(1)),
                    String.valueOf(l2.get(2)),String.valueOf(l2.get(3))))
            {
                Student u2 = new Student();


                u2.setUserName(String.valueOf(l2.get(0)));
                //判断性别
                u2.setWoman(String.valueOf(l2.get(1)));
                u2.setBirthday(String.valueOf(l2.get(2)));
                u2.setIdentification(String.valueOf(l2.get(3)));
                u2.setClassId(String.valueOf(l2.get(4)));
                students.add(u2);
            }else{
                throw new RuntimeException("第"+String.valueOf(i+2)+"行出现格式错误");
            }      //返回错误行数

        }

            //支持不同班级上传
            String flag = "";
            //班级学生序号(这里暂不支持多次上传一个班级,只能使用额外添加)
            int x=1;


        for(int i=0;i<students.size();i++){
            User user = new User();
            Student u = students.get(i);
            //不同班级判断
            String classIdFlag = u.getClassId();
            if(!classIdFlag.equals(flag)){
                x=1;
                flag = classIdFlag;
            }

            //学年
            //Class c = classDao.selectByPrimaryKey(u.getClassId());
            //id自动生成
            u.setId(u.getClassId()+String.format("%02d",x++));

            //设置user数据
            user.setId(u.getId());
            user.setPassword(u.getIdentification());
            user.setRoleId("3");

            //注册user
            if(!service.insertUser(user)) {

                throw new RuntimeException("第"+String.valueOf(i+2)+"行出现User注册错误");
                      //返回错误行
            }


            //注册student
            if(!studentDao.insert(u)) {
                throw new RuntimeException("第"+String.valueOf(i+2)+"行出现Student注册错误");      //返回错误行
            }



        }


    } catch (FileNotFoundException e) {

        e.printStackTrace();
    }




    }

    public void regStuByExcelNeedId(File file) {

    }



    public boolean registerStudent(Student student) {

        try {


            User u = new User();
            u.setRoleId("3");
            u.setId(student.getId());
            u.setPassword(student.getIdentification());
            service.insertUser(u);
            studentDao.insert(student);

        }catch (Exception e){
            throw new RuntimeException("注册失败");
        }

        return true;
    }



}
