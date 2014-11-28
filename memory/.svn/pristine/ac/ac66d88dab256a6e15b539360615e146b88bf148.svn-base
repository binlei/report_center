/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: ZipUtil.java
 * @Prject: memory-util
 * @Package: com.jshuabo.frame.server.util.io
 * @author: peng.wu
 * @date: 2014年4月21日 上午9:11:28
 * @version:
 * @Description:
 */
package com.jshuabo.frame.server.util.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: ZipUtil
 * @Description:  ZIP工具类
 * @author: peng.wu
 * @date: 2014年4月21日 上午9:11:28
 */
public class ZipUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(ZipUtil.class);
    
    private static final int BUFF_SIZE = 1024 * 1024; // 1M Byte

    /**
     * 批量压缩文件（夹）
     * 
     * @param resFileList 要压缩的文件（夹）列表
     * @param zipFile 生成的压缩文件
     * @throws IOException 当压缩过程出错时抛出
     */
    public static void zipFiles(Collection<File> resFileList, File zipFile) throws IOException {
        ZipOutputStream zipout = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile),BUFF_SIZE));
        for (File resFile : resFileList) {
            zipFile(resFile, zipout, "");
        }
        zipout.close();
    }

    /**
     * 批量压缩文件（夹）
     * 
     * @param resFileList 要压缩的文件（夹）列表
     * @param zipFile 生成的压缩文件
     * @param comment 压缩文件的注释
     * @throws IOException 当压缩过程出错时抛出
     */
    public static void zipFiles(Collection<File> resFileList, File zipFile, String comment)
            throws IOException {
        ZipOutputStream zipout =  new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile),BUFF_SIZE));
        for (File resFile : resFileList) {
            zipFile(resFile, zipout, "");
        }
        zipout.setComment(comment);
        zipout.close();
    }

    /**
     * 解压缩一个文件
     * 
     * @param zipFile 压缩文件
     * @param folderPath 解压缩的目标目录
     * @throws IOException 当压缩过程出错时抛出
     */
    @SuppressWarnings("rawtypes")
    public static void upZipFile(File zipFile, String folderPath) throws IOException {
        ZipFile zf = new ZipFile(zipFile);
        for (Enumeration entries = zf.entries(); entries.hasMoreElements();) {
            ZipEntry entry = ((ZipEntry) entries.nextElement());
            InputStream in = zf.getInputStream(entry);
            OutputStream out = new FileOutputStream(folderPath + File.separator + entry.getName());
            byte buffer[] = new byte[BUFF_SIZE];
            int realLength;
            while ((realLength = in.read(buffer)) > 0) {
                out.write(buffer, 0, realLength);
            }
            in.close();
            out.close();
        }
    }

    public static void zipFile(File resFile, ZipOutputStream zipout, String rootpath)
            throws IOException {
        rootpath =
                rootpath + (rootpath.trim().length() == 0 ? "" : File.separator)
                        + resFile.getName();
        if (resFile.isDirectory()) {
            File[] fileList = resFile.listFiles();
            for (File file : fileList) {
                zipFile(file, zipout, rootpath);
            }
        } else {
            byte buffer[] = new byte[BUFF_SIZE];
            BufferedInputStream in =
                    new BufferedInputStream(new FileInputStream(resFile), BUFF_SIZE);
            zipout.putNextEntry(new ZipEntry(rootpath));
            int realLength;
            while ((realLength = in.read(buffer)) != -1) {
                zipout.write(buffer, 0, realLength);
            }
            in.close();
            zipout.flush();
            zipout.closeEntry();
        }
    }

    public static boolean zipFile(File file, String zipName) throws IOException {
        boolean result = false;
        ZipOutputStream zipos = null;
        FileInputStream fis = null;
        try {
            File zipFile = new File(zipName);
            zipos = new ZipOutputStream(new FileOutputStream(zipFile));
            fis = new FileInputStream(file);
            ZipEntry entry = new ZipEntry(file.getName());
            zipos.putNextEntry(entry);
            byte[] buffer = new byte[1024];
            int size = 0;
            while ((size = fis.read(buffer)) != -1) {
                zipos.write(buffer, 0, size);
            }

            result = true;
        } catch (Exception e) {
             logger.error("catch Exception:[{" + e.getLocalizedMessage() + "}]");
        } finally {
            try {
                fis.close();
                zipos.close();
            } catch (Exception e) {
                 logger.error("catch Exception:[{" + e.getLocalizedMessage() + "}]");
            }
        }
        return result;
    }
}
