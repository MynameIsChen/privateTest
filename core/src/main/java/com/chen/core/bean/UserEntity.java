package com.chen.core.bean;

import com.chen.core.DBFlowDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by chenxianglin on 2017/11/7.
 * Class note:
 */

@Table(database = DBFlowDatabase.class)
public class UserEntity extends BaseModel implements BaseEntity {

    @Column
    @PrimaryKey(autoincrement = true)
    private long id;
    @Column
    private String name;
    @Column
    private int sex;
    @Column
    private int age;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
