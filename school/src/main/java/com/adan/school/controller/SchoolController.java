package com.adan.school.controller;

import com.adan.school.dto.SchoolRequest;
import com.adan.school.dto.SchoolResponse;
import com.adan.school.entity.FullSchoolResponse;
import com.adan.school.service.SchoolServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schools")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolServiceImpl service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody SchoolRequest schoolRequest) {
        service.saveSchool(schoolRequest);
    }

    @GetMapping
    public ResponseEntity<List<SchoolResponse>> findAllSchools() {
        return ResponseEntity.ok(service.findAllSchools());
    }

    @GetMapping("/with-students/{school-id}")
    public ResponseEntity<FullSchoolResponse> findAllSchoolsWithStudents(
            @PathVariable("school-id") Integer schoolId
    ) {
        return ResponseEntity.ok(service.findSchoolsWithStudents(schoolId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolResponse> findSchoolById(@PathVariable("id") Integer id) {
        SchoolResponse school = service.findSchoolById(id);
        if (school != null) {
            return ResponseEntity.ok(school);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSchoolById(@PathVariable("id") Integer id,
                                                 @RequestBody SchoolRequest schoolRequest) {
        boolean updated = service.updateSchoolById(id, schoolRequest);
        if (updated) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchoolById(@PathVariable("id") Integer id) {
        boolean deleted = service.deleteSchoolById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
