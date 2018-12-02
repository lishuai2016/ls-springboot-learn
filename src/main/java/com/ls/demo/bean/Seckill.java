package com.ls.demo.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/13 23:38
 */
public class Seckill implements Serializable {
    private static final long serialVersionUID = 1L;

    private long seckillId;
    private String name;
    private int number;
    private Timestamp startTime;
    private Timestamp endTime;
    private Timestamp createTime;

    private int version;

    public long getSeckillId() {
        return seckillId;
    }
    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }
}

