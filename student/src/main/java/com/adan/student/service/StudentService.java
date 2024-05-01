package com.adan.student.service;

import com.adan.student.dto.StudentRequest;
import com.adan.student.dto.StudentResponse;

import java.util.List;

public interface StudentService {

   void saveStudent(StudentRequest studentRequest);
   List<StudentResponse> findAllStudents();
   StudentResponse getStudentById(Integer id);
   boolean updateStudentById(Integer id, StudentRequest studentRequest);
   boolean deleteStudentById(Integer id);
   List<StudentResponse> findAllStudentsBySchool(Integer schoolId);
}
