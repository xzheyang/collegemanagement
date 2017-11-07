package www.lesson.common.utils;


import www.lesson.pojo.User;
import www.lesson.vo.UserSimpleVO;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CopyUtils {


    /*
    *   复制需要,类型和参数名相同
     */
    public static void copyPojoToVO(Object pojo, Object vo) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Class pojoClass = pojo.getClass();
        Class voClass = vo.getClass();


        Field[] pojofields = pojoClass.getDeclaredFields();
        Field[] voFields = voClass.getDeclaredFields();

        Map<String,Class> pojoAttributes = new HashMap<String,Class>();
        Map<String,Class> voAttributes = new HashMap<String,Class>();

        //获得参数名和类型
        for(Field field: pojofields){
            String attributeName = field.getName();
            Class attributeClass = field.getType();
            pojoAttributes.put(attributeName,attributeClass);
        }
        for(Field field: voFields){
            String attributeName = field.getName();
            Class attributeClass = field.getType();
            voAttributes.put(attributeName,attributeClass);
        }

        //获得类型和参数名相同的参数
        Map<String,Class> sameAttributes = new HashMap<String,Class>();
        for(String key: voAttributes.keySet()){
            Class vc = pojoAttributes.get(key);
            if(vc==null){continue;}
            Class pc =  voAttributes.get(key);
            if(pc==vc){
                sameAttributes.put(key,pc);
            }
        }



        // 调用方法版 取值,设置值
        for(String key : sameAttributes.keySet()){
            String getMethodName = "get"+key.substring(0,1).toUpperCase()+key.substring(1,key.length());
            Method getMethod = pojoClass.getMethod(getMethodName);
            Object result = getMethod.invoke(pojo);
            String setMethodName = "set"+key.substring(0,1).toUpperCase()+key.substring(1,key.length());
            Method setMethod = voClass.getMethod(setMethodName,sameAttributes.get(key));
            setMethod.invoke(vo,result);
        }

        /*  简单粗暴版 取值,设置值
        for(String key : sameAttributes.keySet()){
            Field pojoField = pojoClass.getDeclaredField(key);
            pojoField.setAccessible(true);
            Object result = pojoField.get(pojo);
            Field voField = voClass.getDeclaredField(key);
            voField.setAccessible(true);
            voField.set(vo,result);
        }
        */

    }




}
