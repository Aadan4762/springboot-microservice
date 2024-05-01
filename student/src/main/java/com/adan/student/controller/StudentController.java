package com.adan.student.controller;

import com.adan.student.dto.StudentRequest;
import com.adan.student.dto.StudentResponse;
import com.adan.student.entity.Student;
import com.adan.student.service.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentServiceImpl service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public void save(@RequestBody StudentRequest studentRequest) {
        service.saveStudent(studentRequest);
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> findAllStudents() {
        return ResponseEntity.ok(service.findAllStudents());
    }

    @GetMapping("/school/{school-id}")
    public ResponseEntity<List<StudentResponse>> findAllStudents(
            @PathVariable("school-id") Integer schoolId
    ) {
        return ResponseEntity.ok(service.findAllStudentsBySchool(schoolId));
    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable("id") Integer id) {
        StudentResponse studentResponse = service.getStudentById(id);
        return ResponseEntity.ok(studentResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStudentById(
            @PathVariable("id") Integer id,
            @RequestBody StudentRequest studentRequest
    ) {
        boolean updated = service.updateStudentById(id, studentRequest);
        return updated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable("id") Integer id) {
        boolean deleted = service.deleteStudentById(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
