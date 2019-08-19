package com.sszh.server.sso.api.entity;

import com.sszh.core.entity.CommonEntity;

public class SysUserEntity extends CommonEntity {
    /**
     * 主键ID
     */
    private String id;

    /**
     * 登陆账号
     */
    private String account;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码：（由原始密码+随机串组合）MD5值组合
     */
    private String passWord;

    /**
     * 随机字符串：由创建密码时随机生成的UUID
     */
    private String ranStr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    public String getRanStr() {
        return ranStr;
    }

    public void setRanStr(String ranStr) {
        this.ranStr = ranStr == null ? null : ranStr.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysUserEntity other = (SysUserEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }
}