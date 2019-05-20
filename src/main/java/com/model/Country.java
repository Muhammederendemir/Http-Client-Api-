package com.model;

import javax.ws.rs.QueryParam;

public class Country {
    @QueryParam("countryNo")
    private int countryNo;
    @QueryParam("countryName")
    private String countryName;
    @QueryParam("countryLanguage")
    private String countryLanguage;

    @Override
    public String toString() {
        return "country{" +
                "countryNo=" + countryNo +
                ", countryName='" + countryName + '\'' +
                ", countryLanguage='" + countryLanguage + '\'' +
                '}';
    }

    public int getCountryNo() {
        return countryNo;
    }

    public void setCountryNo(int countryNo) {
        this.countryNo = countryNo;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryLanguage() {
        return countryLanguage;
    }

    public void setCountryLanguage(String countryLanguage) {
        this.countryLanguage = countryLanguage;
    }
}
