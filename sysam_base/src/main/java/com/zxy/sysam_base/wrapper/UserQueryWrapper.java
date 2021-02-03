/**
 *
 */
package com.zxy.sysam_base.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxy.sysam_base.entity.User;
import com.zxy.sysam_base.entity.UserRole;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * UserQueryWrapper.java
 * @description User用户实体查询包装类
 * @author hby
 * @since 2021-01-29
 *
 */
public class UserQueryWrapper extends QueryWrapper<User> {

    //	用户id
    private Integer id;
    //	用户名
    private String username;

    //	密码
    private String password;

    //	姓名
    private String name;

    //	性别
    private Integer sex;

    //	年龄
    private Integer age;

    //	电话
    private String tel;

    //	邮箱
    private String mail;

    //	启用状态   0-未启用  1已启用
    private Integer enable;

    //	用户角色
    private List<UserRole> userRoles;

    //	创建时间
    private Date createtime;

    //	更新时间
    private Date updatetime;

    //	是否删除   0-未删除  1-删除
    private Integer isDel;

    //	描述
    private String describes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        if (StringUtils.isNotBlank(this.username)) {
            this.like(User.USERNAME, this.username);
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
        if (this.enable != null) {
            this.eq(User.ENABLE, this.enable);
        }
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

}
