package com.sszh.server.sso.api.entity;

import com.sszh.core.entity.CommonEntity;

import java.util.Date;

public class SysMenuEntity extends CommonEntity {
    /**
     * 权限ID
     */
    private Integer id;

    /**
     * 父菜单ID，一级菜单为0
     */
    private Integer parentId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单URL，如：sys/menu
     */
    private String url;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String perms;

    /**
     * 类型   1：菜单 2：按钮
     */
    private Integer type;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单状态：-1 禁用  1启用
     */
    private Integer status;

    /**
     * 系统标识：1 运营  2 商户PC
     */
    private Integer systemMark;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 创建时间
     */
    private Date createTime;

    public SysMenuEntity(Integer id, Integer parentId, String name, String url, String perms, Integer type, String icon, Integer status, Integer systemMark, Integer orderNum, Date createTime) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.url = url;
        this.perms = perms;
        this.type = type;
        this.icon = icon;
        this.status = status;
        this.systemMark = systemMark;
        this.orderNum = orderNum;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getPerms() {
        return perms;
    }

    public Integer getType() {
        return type;
    }

    public String getIcon() {
        return icon;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getSystemMark() {
        return systemMark;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public Date getCreateTime() {
        return createTime;
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
        SysMenuEntity other = (SysMenuEntity) that;
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