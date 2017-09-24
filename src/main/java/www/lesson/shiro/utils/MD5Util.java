package www.lesson.shiro.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

public class MD5Util {

    public static String getPassword(String password,String salt){
        return new Md5Hash(password, salt).toString();
    }

}
