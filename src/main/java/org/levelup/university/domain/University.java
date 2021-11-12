package org.levelup.university.domain;

import lombok.*;

import javax.persistence.*;

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

    public University(String name, String shortName, Integer foundationYear) {
        this.name = name;
        this.shortName = shortName;
        this.foundationYear = foundationYear;
    }
}
