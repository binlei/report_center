/**
  * Copyright©2014 www.jshuabo.com. all rights reserved.
  *
  * @Title: FtpUtil.java
  * @Prject: memory-util
  * @Package: com.jshuabo.frame.server.util.ftp
  * @author: lianghe.yuan
  * @date: Jun 13, 2014 11:43:04 AM
  * @version: 
  * @Description: 
  */
package com.jshuabo.frame.server.util.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: FtpUtil
 * @Description: 
 * @author: lianghe.yuan
 * @date: Jun 13, 2014 11:43:04 AM
 */
public class FtpUtil {
    private static final Logger logger = LoggerFactory.getLogger(FtpUtil.class);
    
    private FTPClient ftpClient = null;
    private String host;
    private int port;
    private String username;
    private String password;
    
    private boolean isLocalPassiveMode = true;
    private int connectTimeout = 1000 * 60;
    private int bufferSize = 10000;
    private int fileType = FTPClient.BINARY_FILE_TYPE;
    private String controlEncoding = "UTF-8";
    
    public FtpUtil(String host, int port, String username, String password) {
        super();
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }
    
    public FtpUtil(String host, int port, String username, String password, boolean isLocalPassiveMode, int fileType, int connectTimeout, int bufferSize, String controlEncoding) {
        super();
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.isLocalPassiveMode = isLocalPassiveMode;
        this.fileType = fileType;
        this.connectTimeout = connectTimeout;
        this.bufferSize = bufferSize;
        this.controlEncoding = controlEncoding;
    }

    public boolean connect() {
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(host, port);
            if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                if (ftpClient.login(username, password)) {
                    ftpClient.setConnectTimeout(connectTimeout);
                    ftpClient.setControlEncoding(controlEncoding);
                    ftpClient.setBufferSize(bufferSize);
                    ftpClient.setFileType(fileType);
                    
                    if (isLocalPassiveMode)
                        ftpClient.enterLocalPassiveMode();
                    
                    return true;
                } else {
                    logger.error("connect to server [{}] with wrong username [{}] or password [{}}", new Object[]{host + ":" + port, username, password});
                }
            } else {
                disconnect();
            }
        } catch (SocketException e) {
            logger.error("connect to server [{}] with SocketException : {}", new Object[]{host + ":" + port, e.getLocalizedMessage()});
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error("connect to server [{}] with IOException : {}", new Object[]{host + ":" + port, e.getLocalizedMessage()});
            throw new RuntimeException(e);
        }
        return false;
    }
    
    public void disconnect() {
        if (ftpClient != null) {
            if (!ftpClient.isConnected()) {
                logger.info("disconnect from server [{}] : {}", new Object[]{host + ":" + port, "client is not connected, so needn't disconnect!"});
            } else {
                try {
                    ftpClient.logout();
                    ftpClient.disconnect();
                    ftpClient = null;
                } catch (IOException e) {
                    logger.error("disconnect from server [{}] with IOException : {}", new Object[]{host + ":" + port, e.getLocalizedMessage()});
                    throw new RuntimeException(e);
                }
            }
        }
        logger.info("disconnect from server [{}] : {}", new Object[]{host + ":" + port, "client is null, so needn't disconnect!"});
    }
    
    public boolean reconnect() {
        disconnect();
        return connect();
    }
    
    public boolean isAvailable() {
        if (ftpClient != null)
            return ftpClient.isAvailable();
        
        logger.info("connect is not available from server [{}]", new Object[]{host + ":" + port});
        return false;
    }
    
    public void checkAvailable() {
        if (!isAvailable()) {
            logger.info("connect is not available from server [{}]", new Object[]{host + ":" + port});
            throw new RuntimeException("connect is not available from server!");
        }
    }
    
    public boolean changeWorkingDirectory(String pathname) {
        checkAvailable();
        try {
            return ftpClient.changeWorkingDirectory(pathname);
        } catch (IOException e) {
            logger.error("changeWorkingDirectory [{}] failed with IOException : {}", new Object[]{pathname, e.getLocalizedMessage()});
            throw new RuntimeException(e);
        }
    }
    
    public boolean createDirectory(String pathname) {
        checkAvailable();
        try {
            return ftpClient.makeDirectory(pathname);
        } catch (IOException e) {
            logger.error("createDirectory [{}] failed with IOException : {}", new Object[]{pathname, e.getLocalizedMessage()});
            throw new RuntimeException(e);
        }
    }
    
    public boolean deleteFile(String pathname) {
        checkAvailable();
        try {
            return ftpClient.deleteFile(pathname);
        } catch (IOException e) {
            logger.error("createDirectory [{}] failed with IOException : {}", new Object[]{pathname, e.getLocalizedMessage()});
            throw new RuntimeException(e);
        }
    }
    
    public String[] getFileList(String pathname) {
        checkAvailable();
        try {
            return ftpClient.listNames();
        } catch (IOException e) {
            logger.error("getFileList [{}] failed with IOException : {}", new Object[]{pathname, e.getLocalizedMessage()});
            throw new RuntimeException(e);
        }
    }
    
    public boolean upload(String srcFileName) {
        return upload(srcFileName, srcFileName);
    }
    
    public boolean upload(String srcFileName, String dstFileName) {
        checkAvailable();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(srcFileName);
            return ftpClient.storeFile(dstFileName, inputStream);
        } catch (FileNotFoundException e) {
            logger.error("uploadFile with FileNotFoundException : {}", new Object[]{e.getLocalizedMessage()});
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error("uploadFile with IOException : {}", new Object[]{e.getLocalizedMessage()});
            throw new RuntimeException(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                inputStream = null;
            }
        }
    }
    
    public boolean upload(InputStream inputStream, String dstFileName) {
        checkAvailable();
        try {
            return ftpClient.storeFile(dstFileName, inputStream);
        } catch (IOException e) {
            logger.error("uploadFile with IOException : {}", new Object[]{e.getLocalizedMessage()});
            throw new RuntimeException(e);
        }
    }
    
    public boolean download(String remoteFileName, String localFileName) {
        checkAvailable();
        OutputStream localOutputStream = null;
        try {
            File localFile = new File(localFileName);
            localOutputStream = new FileOutputStream(localFile);
            ftpClient.retrieveFile(remoteFileName, localOutputStream);
            return true;
        } catch (IOException e) {
            logger.error("downloadFile with IOException : {}", new Object[]{e.getLocalizedMessage()});
            throw new RuntimeException(e);
        } finally {
            if (localOutputStream != null) {
                try {
                    localOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                localOutputStream = null;
            }
        }
    }
    
    public InputStream download(String remoteFileName) {
        checkAvailable();
        try {
            return ftpClient.retrieveFileStream(remoteFileName);
        } catch (IOException e) {
            logger.error("downloadFile with IOException : {}", new Object[]{e.getLocalizedMessage()});
            throw new RuntimeException(e);
        }
    }
    
    public boolean rename(String from, String to) {
        checkAvailable();
        try {
            return ftpClient.rename(from, to);
        } catch (IOException e) {
            logger.error("rename file with IOException : {}", new Object[]{e.getLocalizedMessage()});
            throw new RuntimeException(e);
        }
    }
    
    public FTPClient getFtpClient() {
        return this.ftpClient;
    }
    
    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
        this.ftpClient.setConnectTimeout(connectTimeout);
    }
    
    public void setControlEncoding(String controlEncoding) {
        this.controlEncoding = controlEncoding;
        this.ftpClient.setControlEncoding(controlEncoding);
    }
    
    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
        this.ftpClient.setBufferSize(bufferSize);
    }
    
    public void setFileType(int fileType) {
        try {
            this.ftpClient.setFileType(fileType);
            this.fileType = fileType;
        } catch (IOException e) {
            logger.error("setFileType [{}] failed with IOException : {}", new Object[]{fileType, e.getLocalizedMessage()});
            throw new RuntimeException(e);
        }
    }
    
    public static void main(String[] args) {
        FtpUtil t = new FtpUtil("10.0.0.62", 21, "anonymous", "");
        t.connect();
        t.checkAvailable();
        
        Long t1 = System.currentTimeMillis();
        t.download("system/qq_4.7.2.2175_android.apk", "d:/qq_4.7.2.2175_android.apk");
        Long t2 = System.currentTimeMillis();
        
        System.out.println((t2 - t1) / 1000);
    }
}
