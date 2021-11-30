package org.levelup.university.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data //генерирует конструтор, но только в String
@Entity
@Table(name = "faculties")
@ToString(exclude = {
        "university",
        "subjects"
}) // toString только двух полей (id, name)
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //говорит Hibernate, что данное поле генерируется автоматически. Без этой аннотации но при наличии аннотации  @Id, будет проблема
    private Integer id;
    private String name;

    //Связь с таблицей university
    //bidirectional - unidirectional

    //1-M -> OneToMany, ManyToOne ввиды аннотаций в зависимости от связей
    @ManyToOne
    @JoinColumn(name = "university_id") //по какой колонке надо связать
    private University university;

    @ManyToMany(mappedBy = "faculties", cascade = CascadeType.PERSIST)
    private List<Subject> subjects;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "faculty_id") //name тоже не подсвечивалась красным
    private FacultyInfo info;

    /***
     * @JoinColumn(name =
     * referencedColumnName - если поле  из таблицы  FacultyInfo
     * name - если поле из таблицы Faculty
     */

}
