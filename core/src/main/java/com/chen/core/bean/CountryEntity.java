package com.chen.core.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * Created by chenxianglin on 2017/11/1.
 * Class note:
 */

public class CountryEntity implements Serializable {
    private String code;
    private String country;
    private String letter;

    public CountryEntity() {

    }

    public CountryEntity(String code, String country) {
        this.code = code;
        this.country = country;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    @JsonIgnore
    public CountryEntity getDefault() {
        return new CountryEntity("86", "中国");
    }
}
