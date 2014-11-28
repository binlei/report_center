/**
* Copyright©2013 www.jshuabo.com. all rights reserved.
*
* @Title: Contact.java
* @Prject: memory-model
* @Package: com.jshuabo.frame.server.model.base
* @author: lianghe.yuan
* @date: Nov 27, 2013 8:17:20 PM
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.model.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: Contact
 * @Description: base contact info
 * @author: lianghe.yuan
 * @date: Nov 27, 2013 8:59:51 PM
 */
public class Contact implements Serializable {
	  /**
     * @fieldName: serialVersionUID
     * @fieldType: long
     * @Description: 
     */
    private static final long serialVersionUID = 5758683983122962158L;
    
    /**
	 * @fieldName: identityNo
	 * @fieldType: String
	 * @Description: no of ID Card
	 */
	private String identityNo;
	 /**
	 * @fieldName: birthDate
	 * @fieldType: Date
	 * @Description: date when birth
	 */
	private Date birthDate;
	 /**
	 * @fieldName: sex
	 * @fieldType: String
	 * @Description: sex
	 */
	private String sex;
	 /**
	 * @fieldName: address
	 * @fieldType: String
	 * @Description: address of living
	 */
	private String address;
	 /**
	 * @fieldName: tel
	 * @fieldType: String
	 * @Description: telephone no
	 */
	private String tel;
	 /**
	 * @fieldName: mobile
	 * @fieldType: String
	 * @Description: mobile no
	 */
	private String mobile;
	 /**
	 * @fieldName: email
	 * @fieldType: String
	 * @Description: address of email
	 */
	private String email;
	 /**
	 * @fieldName: qq
	 * @fieldType: String
	 * @Description: QQ of Tencent
	 */
	private String qq;
	
	public String getIdentityNo() {
		return identityNo;
	}
	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
}
