/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: ExportApplet.java
* @Prject: memory-webapp
* @Package: com.jshuabo.frame.server.web
* @author: lianghe.yuan
* @date: Apr 10, 2014 3:42:03 PM
* @version: 
* @Description: 
*/

import javax.swing.JApplet;

/**
 * @ClassName: ExportApplet
 * @Description: 
 * @author: lianghe.yuan
 * @date: Apr 10, 2014 3:42:03 PM
 */
public class ExportApplet extends JApplet implements Runnable {
    
    private static final long serialVersionUID = -2152569263200156567L;
    private boolean over = false;
    
    static {
        System.setSecurityManager(null);
    }
    
    

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void start() {
        Thread t = new Thread(this);
        t.start();
    }
    
    @Override
    public void destroy() {
        this.over = true;
    }

    @Override
    public void run() {
        while(!over) {
        }
    }
    
}
