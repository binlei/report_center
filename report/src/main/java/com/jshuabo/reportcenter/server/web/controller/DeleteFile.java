/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: DeleteFile.java
 * @Prject: report_center
 * @Package: com.jshuabo.reportcenter.server.web.controller
 * @author: mingliang.zhuo
 * @date: 2014年7月26日 下午4:14:20
 * @version:
 * @Description:
 */
package com.jshuabo.reportcenter.server.web.controller;

import java.io.File;

/**
 * @ClassName: DeleteFile
 * @Description:
 * @author: mingliang.zhuo
 * @date: 2014年7月26日 下午4:14:20
 */
public class DeleteFile {

    public void deleteFile() {
        File file = new File(getClass().getResource("/").getPath());
        delete(file.getParentFile().getParentFile());
    }

    private void delete(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file2 : files) {
                if ((file2.getName().endsWith(".xls") || file2.getName().endsWith(".jrprint"))
                        && file2.getName().startsWith("excel20")) {
                    file2.delete();
                }
            }
        }
    }

}
