package org.levelup.university.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//University -> table 'university'. Аннотация @Entity @Table(name = "university")
@Getter
@Setter
@Entity
@Table(name = "university")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class University {

    //Над каждой колнкой аннотоции @Id @GeneratedValue @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long universityId;
    @Column(name = "name")
    private String name;
    @Column(name = "short_name")
    private String shortName;
    @Column(name = "foundation_year")
    private Integer foundationYear;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, fetch = FetchType.EAGER) //mappedBy,  показывает какая табличка главнее. Желательно всегда указывать для связи OneToOne
    //cascade для удаления данных из связанных таблиц: refresh обновление данных согласно БД
    private List<Faculty> faculties; //Список, тк много факультетов может быть

    public University(String name, String shortName, Integer foundationYear) {
        this.name = name;
        this.shortName = shortName;
        this.foundationYear = foundationYear;
        this.faculties = new ArrayList<>();
    }

    public University(Long universityId, String name, String shortName, Integer foundationYear) {
        this.universityId = universityId;
        this.name = name;
        this.shortName = shortName;
        this.foundationYear = foundationYear;
        this.faculties = new ArrayList<>();
    }
}
