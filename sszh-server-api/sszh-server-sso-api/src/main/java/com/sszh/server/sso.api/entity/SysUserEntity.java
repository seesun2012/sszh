package com.sszh.server.sso.api.entity;

import com.sszh.core.entity.CommonEntity;

public class SysUserEntity extends CommonEntity {
    /**
     * 主键ID
     */
    private Integer id;

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

    public SysUserEntity(Integer id, String account, String userName, String passWord, String ranStr) {
        this.id = id;
        this.account = account;
        this.userName = userName;
        this.passWord = passWord;
        this.ranStr = ranStr;
    }

    public Integer getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getRanStr() {
        return ranStr;
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