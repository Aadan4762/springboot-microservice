package com.adan.school.service;

import com.adan.school.dto.SchoolRequest;
import com.adan.school.dto.SchoolResponse;

import java.util.List;

public interface SchoolService {

    void saveSchool(SchoolRequest schoolRequest);
    List<SchoolResponse> findAllSchools();
    SchoolResponse findSchoolById(Integer id);
    boolean updateSchoolById(Integer id, SchoolRequest schoolRequest);
    boolean deleteSchoolById(Integer id);
}
