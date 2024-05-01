package com.adan.student.service;



import com.adan.student.dto.StudentRequest;
import com.adan.student.dto.StudentResponse;
import com.adan.student.entity.Student;
import com.adan.student.repo.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    @Override
    public void saveStudent(StudentRequest studentRequest) {
        Student student = mapToStudent(studentRequest);
        repository.save(student);
    }

    @Override
    public List<StudentResponse> findAllStudents() {
        List<Student> students = repository.findAll();
        return mapToStudentResponseList(students);
    }

    @Override
    public StudentResponse getStudentById(Integer id) {
        Optional<Student> optionalStudent = repository.findById(id);
        return optionalStudent.map(this::mapToStudentResponse).orElse(null);
    }

    @Override
    public boolean updateStudentById(Integer id, StudentRequest studentRequest) {
        Optional<Student> optionalStudent = repository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            updateStudentFromRequest(student, studentRequest);
            repository.save(student);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteStudentById(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<StudentResponse> findAllStudentsBySchool(Integer schoolId) {
        return repository.findAllBySchoolId(schoolId);
    }
    private Student mapToStudent(StudentRequest studentRequest) {
        return Student.builder()
                .firstname(studentRequest.getFirstname())
                .lastname(studentRequest.getLastname())
                .email(studentRequest.getEmail())
                .schoolId(studentRequest.getSchoolId())
                .build();
    }

    private StudentResponse mapToStudentResponse(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .firstname(student.getFirstname())
                .lastname(student.getLastname())
                .email(student.getEmail())
                .schoolId(student.getSchoolId())
                .build();
    }

    private List<StudentResponse> mapToStudentResponseList(List<Student> students) {
        return students.stream()
                .map(this::mapToStudentResponse)
                .collect(Collectors.toList());
    }

    private void updateStudentFromRequest(Student student, StudentRequest studentRequest) {
        student.setFirstname(studentRequest.getFirstname());
        student.setLastname(studentRequest.getLastname());
        student.setEmail(studentRequest.getEmail());
        student.setSchoolId(studentRequest.getSchoolId());
    }
}
