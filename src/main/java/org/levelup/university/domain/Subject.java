package org.levelup.university.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Data
@Entity
@Table(name = "subjects")
@NoArgsConstructor //Конструктор без параметров
@ToString(exclude = "faculties")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int hours;

    @ManyToMany
    @JoinTable(
            name = "subjects_on_faculties",
            joinColumns = @JoinColumn(name = "subject_id"), //колонка из связующей таблицы, которая указывает на ИД из таблицы subjects
            inverseJoinColumns = @JoinColumn(name = "faculty_id") //колонка из связующей таблицы, которая указывает на ИД из таблицы faculties

    )
    private List<Faculty> faculties; //это вторая таблица

    public Subject(String name, int hours) {
        this.name = name;
        this.hours = hours;
    }





}
