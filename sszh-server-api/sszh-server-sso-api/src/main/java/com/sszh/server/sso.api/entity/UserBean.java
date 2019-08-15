package com.sszh.server.sso.api.entity;

import com.sszh.core.entity.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 用户实体
 */
@RequiredArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data
@Builder
public class UserBean extends CommonEntity {

    private static final long serialVersionUID = 8256603124539536859L;
    
    private Long id;                //id主键
    private String account;         //登陆账号
    private String userName;        //用户名
    private String passWord;        //密码
    private String ranStr;          //随机字符串（用于加密密码）

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRanStr() {
        return ranStr;
    }

    public void setRanStr(String ranStr) {
        this.ranStr = ranStr;
    }
    
    
}
