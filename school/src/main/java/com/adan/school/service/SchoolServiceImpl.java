package com.adan.school.service;

import com.adan.school.dto.SchoolRequest;
import com.adan.school.dto.SchoolResponse;
import com.adan.school.entity.FullSchoolResponse;
import com.adan.school.repo.SchoolRepository;
import com.adan.school.client.StudentClient;
import com.adan.school.entity.School;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository repository;
    private final StudentClient client;

    public void saveSchool(SchoolRequest schoolRequest) {
        School school = School.builder()
                .name(schoolRequest.getName())
                .email(schoolRequest.getEmail())
                .build();
        repository.save(school);
    }

    public List<SchoolResponse> findAllSchools() {
        List<School> schools = repository.findAll();
        return schools.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public SchoolResponse findSchoolById(Integer id) {
        return repository.findById(id)
                .map(this::convertToResponse)
                .orElse(null);
    }

    public boolean updateSchoolById(Integer id, SchoolRequest schoolRequest) {
        return repository.findById(id).map(school -> {
            school.setName(schoolRequest.getName());
            school.setEmail(schoolRequest.getEmail());
            repository.save(school);
            return true;
        }).orElse(false);
    }

    public boolean deleteSchoolById(Integer id) {
        return repository.findById(id).map(school -> {
            repository.delete(school);
            return true;
        }).orElse(false);
    }

    public FullSchoolResponse findSchoolsWithStudents(Integer schoolId) {
        var school = repository.findById(schoolId)
                .orElse(
                        School.builder()
                                .name("NOT_FOUND")
                                .email("NOT_FOUND")
                                .build()
                );
        var students = client.findAllStudentsBySchool(schoolId);
        return FullSchoolResponse.builder()
                .name(school.getName())
                .email(school.getEmail())
                .students(students)
                .build();
    }

    private SchoolResponse convertToResponse(School school) {
        return SchoolResponse.builder()
                .id(school.getId())
                .name(school.getName())
                .email(school.getEmail())
                .build();
    }
}