package org.levelup.university.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "faculty_info")
@NoArgsConstructor
public class FacultyInfo {

    @Id
    @Column(name = "faculty_id")
    private Integer facultyId;
    private String phone;
    private String mail;
}
