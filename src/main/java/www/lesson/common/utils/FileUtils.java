package www.lesson.common.utils;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
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

}
