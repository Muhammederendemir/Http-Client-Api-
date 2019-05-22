package com.model;

public class School {
    //  @QueryParam("schoolNo")
    private int schoolNo;
    //  @QueryParam("schoolName")
    private String schoolName;
    //  @QueryParam("countryNo")
    private int countryNo;

    @Override
    public String toString() {
        return "school{" +
                "schoolNo=" + schoolNo +
                ", schoolName='" + schoolName + '\'' +
                ", countryId='" + countryNo + '\'' +
                '}';
    }

    public int getSchoolNo() {
        return schoolNo;
    }

    public void setSchoolNo(int schoolNo) {
        this.schoolNo = schoolNo;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getCountryNo() {
        return countryNo;
    }

    public void setCountryNo(int countryId) {
        this.countryNo = countryId;
    }
}
