package www.lesson.common.utils;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class FileUtils {

    public static int MIN_FILE_SIZE=2048 ;

    public static File multipartToFile(MultipartFile multfile) throws IOException {
        CommonsMultipartFile cf = (CommonsMultipartFile)multfile;

        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
        File file = fi.getStoreLocation();
        //手动创建临时文件
        if(file.length() < MIN_FILE_SIZE){
            File tmpFile = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") +
                    file.getName());
            multfile.transferTo(tmpFile);
            return tmpFile;
        }
        return file;
    }

    public static File multipartToFile(MultipartFile multfile,String path) throws IOException {

        String suffix = multfile.getOriginalFilename().substring(multfile.getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID().toString()+ suffix;
        File up=new File(path,fileName);
        multfile.transferTo(up);

        return up;
    }




    //读取文件头
    public static String bytesToHex(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    //获取魔数
    public static String getFileType(MultipartFile file) throws IOException {
        byte[] ms = new byte[28];
        InputStream in = null;

        try {
            in = file.getInputStream();
            in.read(ms, 0, 28); //读取28位魔数
        }finally {
            if(in!=null){
                in.close();
            }
        }

        return bytesToHex(ms).toUpperCase();
    }

}
