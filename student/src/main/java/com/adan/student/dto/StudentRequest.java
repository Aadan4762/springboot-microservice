package com.adan.student.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequest {
    private String firstname;
    private String lastname;
    private String email;
    private Integer schoolId;
}
