package www.lesson.register.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import www.lesson.common.utils.ExcelFomart;
import www.lesson.common.utils.ExcelUtils;
import www.lesson.pojo.Class;
import www.lesson.register.dao.ClassDao;
import www.lesson.register.service.RegisterClassService;
import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Transactional  //事务同步
@Service
public class RegisterClassServiceImpl implements RegisterClassService {

    @Resource
    ClassDao classDao;


    public void registerClassByExcel(File file) {

        /**
         *          班级id=学年+序号
         *
         **/

        try {



            InputStream in = new FileInputStream(file);

            //解析数据
            List<List<Object>> l1 = ExcelUtils.upExcel(in, file.getName());


            List<Class> classes = new ArrayList<Class>();


            //遍历数据
            for(int i=0;i<l1.size();i++){
                List<Object> l2 = l1.get(i);

                String myYear = String.valueOf(l2.get(1));

            //检测数据格式
            if(ExcelFomart.excelYearFomart(myYear))
            {
                Class c = new Class();
                //学年班级排位
                int cid ;

                /*方案1: 如果之前上传的有误,将会出错

                String maxId = classDao.selectMaxId();
                String year = maxId.substring(0,4);


                //匹配上一id是否是本学年
                if(year.equals(myYear)){
                    //获得上一id,并赋值序号
                    String lastid = maxId.substring(maxId.length()-3,maxId.length());
                    cid = Integer.parseInt(lastid)+1;
                }else{cid = 1;}
                */


                //方案2: 效率低,较安全
                List<String> l = classDao.listPreClassIds(myYear);
                if(l.size()>0){
                    //获得这学年最大Id
                    String maxId = Collections.max(l);
                    cid = Integer.parseInt(maxId.substring(maxId.length()-3,maxId.length()))+1;
                }else{  cid=1; }



                //自动生成班级id
                c.setId(myYear+String.format("%03d",cid));

                c.setName(String.valueOf(l2.get(0)));
                c.setYear(myYear);
                c.setTeacherId(String.valueOf(l2.get(2)));
                classes.add(c);


                //注册Class
                if(!classDao.insert(c)){
                    throw new RuntimeException("第"+String.valueOf(i+2)+"行出现User注册错误");
                }

            }else{
                throw new RuntimeException("第"+String.valueOf(i+2)+"行出现格式注册错误");
            }      //返回错误行数

        }



        } catch (FileNotFoundException e) {

        e.printStackTrace();
    }



    }

    public void regClassByExcelNeedId(File file) {

    }


    public boolean registerClass(Class c) {

        try {

            //学年班级排位
            int cid ;
            List<String> l = classDao.listPreClassIds(c.getYear());
            if(l.size()>0){
                //获得这学年最大Id
                String maxId = Collections.max(l);
                cid = Integer.parseInt(maxId.substring(maxId.length()-3,maxId.length()))+1;
            }else{  cid=1; }

            //自动生成班级id
            c.setId(c.getYear()+String.format("%03d",cid));


            classDao.insert(c);
        }catch (Exception e){
            return false;
        }

        return true;
    }

    public boolean deleteClass(String id) {

        classDao.deleteByPrimaryKey(id);

        return true;
    }

    public boolean updateClass(Class c) {

        try {
            classDao.updateByPrimaryKey(c);
        }catch (Exception e){
            return  false;
        }
        return true;
    }


}
