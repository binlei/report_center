package com.jshuabo.reportcenter.server.utils.io;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.jshuabo.frame.server.exception.BaseRuntimeException;

/**
 * @ClassName: FileUtils
 * @Description: File 工具类
 * @author: peng.wu
 * @date: 2014年10月18日 下午12:50:35
 */
public class FileUtils {
    
    /**
     * @Title: uploadFile 
     * @param fileName
     * @return: File need upload file
     * @date: 2014年10月18日 下午12:52:58
     */
    public static File uploadFile(MultipartFile multipartFile,String fileName){
        File file = new File(fileName);
        try {
            multipartFile.transferTo(file);
        } catch (IllegalStateException e) {
            throw new BaseRuntimeException("IllegalStateException with to importExcelRecord :"+e.getLocalizedMessage());
        } catch (IOException e) {
            throw new BaseRuntimeException("IOException with to importExcelRecord :"+e.getLocalizedMessage());
        }
        return file; 
    }

}
