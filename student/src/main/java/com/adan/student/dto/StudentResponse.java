package com.adan.student.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class StudentResponse {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private Integer schoolId;
}
