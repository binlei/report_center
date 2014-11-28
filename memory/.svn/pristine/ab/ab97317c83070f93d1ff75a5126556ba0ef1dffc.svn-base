/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: EasyuiTreeNode.java
 * @Prject: memory-model
 * @Package: com.jshuabo.frame.server.model.base
 * @author: jing.huang
 * @date: 2014年5月6日 下午9:03:23
 * @version: v1.0
 * @Description:
 */
package com.jshuabo.frame.server.model.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * @ClassName: EasyuiTreeNode
 * @Description:
 * @author: jing.huang
 * @date: 2014年5月6日 下午9:03:23
 */
public class EasyuiTreeNode implements Serializable {
      /**
     * @fieldName: serialVersionUID
     * @fieldType: long
     * @Description: 
     */
    private static final long serialVersionUID = 1L;
    /**
     * @fieldName: id
     * @fieldType: String
     * @Description: 树形节点id
     */
    private String id;
     /**
     * @fieldName: text
     * @fieldType: String
     * @Description: 节点文本
     */
    private String text;
     /**
     * @fieldName: iconCls
     * @fieldType: String
     * @Description: 图标类型
     */
    private String iconCls;
     /**
     * @fieldName: state
     * @fieldType: String
     * @Description: 节点状态"open","closed",默认"open"
     */
    private String state = "open";
     /**
     * @fieldName: checked
     * @fieldType: Boolean
     * @Description: 该节点是否选中
     */
    private Boolean checked = false;
     /**
     * @fieldName: attributes
     * @fieldType: Map<String,Object>
     * @Description: 自定义属性
     */
    private Map<String, Object> attributes;
     /**
     * @fieldName: children
     * @fieldType: List<EasyuiTreeNode>
     * @Description: 若干子节点
     */
    private List<EasyuiTreeNode> children;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getIconCls() {
        return iconCls;
    }
    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public Boolean getChecked() {
        return checked;
    }
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
    public Map<String, Object> getAttributes() {
        return attributes;
    }
    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
    public List<EasyuiTreeNode> getChildren() {
        return children;
    }
    public void setChildren(List<EasyuiTreeNode> children) {
        this.children = children;
    }
    
}
