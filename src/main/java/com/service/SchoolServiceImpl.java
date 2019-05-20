package com.service;

import com.dao.SchoolDAO;
import com.dao.SchoolDAOImpl;
import com.model.School;

public class SchoolServiceImpl implements SchoolService {
    SchoolDAO schoolDAO = new SchoolDAOImpl();

    public School findSchool(int schoolNo) throws Exception {
        return schoolDAO.getSchoolByNo(schoolNo);
    }

    public void createSchool(School school) throws Exception {
        schoolDAO.saveSchool(school);
    }

    public void updateSchool(School school) throws Exception {
        schoolDAO.updateSchool(school);
    }

    public void deleteSchool(int schoolNo) throws Exception {
        schoolDAO.deleteSchoolByNo(schoolNo);
    }
}
