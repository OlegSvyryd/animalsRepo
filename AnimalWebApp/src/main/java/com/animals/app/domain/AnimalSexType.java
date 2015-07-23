package com.animals.app.domain;

/**
 * Created by Rostyslav.Viner on 22.07.2015.
 */
public class AnimalSexType {

    private Long id;
    private String sex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "AnimalSexType{" +
                "id=" + id +
                ", sex='" + sex + "'" +
                '}';
    }
}