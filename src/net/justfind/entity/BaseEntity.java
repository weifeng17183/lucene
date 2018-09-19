package net.justfind.entity;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -6718838800112233445L;

    public static final String CREATE_DATE_PROPERTY_NAME = "createDate";// "创建日期"属性名称
    public static final String MODIFY_DATE_PROPERTY_NAME = "modifyDate";// "修改日期"属性名称

    protected String id;// ID
    protected Date createDate;// 创建日期
    protected Date modifyDate;// 修改日期

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) object;
            if (this.getId() == null || baseEntity.getId() == null) {
                return false;
            } else {
                return (this.getId().equals(baseEntity.getId()));
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id == null ? System.identityHashCode(this) : (this.getClass().getName() + this.getId()).hashCode();
    }

}
