package com.dao;

import com.model.School;

public interface SchoolDAO {
    public School getSchoolByNo(int schoolNo) throws Exception;

    public void saveSchool(School school) throws Exception;

    public void updateSchool(School school) throws Exception;

    public void deleteSchoolByNo(int schoolNo) throws Exception;
}
