package com.nevile.system.base.vo;

import java.util.Date;

/**
 * 数据校验测试类
 */
public class ValidData {
    private Integer age;

    private String name;

    private float salary;

    private Date birthday;


    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public float getSalary() {
        return salary;
    }

    public Date getBirthday() {
        return birthday;
    }


    public void setAge(Integer age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    @Override
    public String toString() {
        return "VaildData{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", birthday=" + birthday +
                '}';
    }
}
