package com.service;

import com.model.School;

public interface SchoolService {
    School findSchool(int schoolNo) throws Exception;//private olacaka

    void createSchool(School school) throws Exception;

    void updateSchool(School school) throws Exception;

    void deleteSchool(int schoolNo) throws Exception;
}
