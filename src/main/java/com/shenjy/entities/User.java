package com.shenjy.entities;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2018/06/27
 */
public class User implements Serializable {

    private long uid;

    @JSONField(serialize=false)
    private String name;

    @JSONField(format="yyyy-MM-dd HH:mm")
    private Date ctime;

    @JSONField(serializeUsing = ToStringSerializer.class)
    public Long getUid() {
        return uid;
    }

    @JSONField(serializeUsing = ToStringSerializer.class)
    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}
