package com.adan.student.repo;

import com.adan.student.dto.StudentResponse;
import com.adan.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<StudentResponse> findAllBySchoolId(Integer schoolId);
}
