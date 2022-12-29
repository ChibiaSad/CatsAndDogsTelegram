package com.example.catsanddogstelegram.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**Абстрактный класс животные со свойствами <b> animalId</b>, <b>registerDate</b>, <b>gender</b>,
 * <b>animalName</b>,<b>animalAge</b>, <b>description</b>
 * @author new developers
 * @version 1.0
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "animals")
@Data
public abstract class Animal {

     /**Поле идентификационный номер которое
     * присваивает номер животному в порядке их поступления в приют
     * не зависимо от дальнейшего наследника класса
     * с помощью указанных аннотаций {@link @Id}  и {@link @GeneratedValue(strategy = GenerationType.IDENTITY)}
     * аннотация {@Column} указывает на название колонки параметра в БД
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id")
    private Integer animalId;

    /** Поле дата регистрации */
    @Column(name = "register_date")
    private Timestamp registerDate;

    /** Поле пол живатного */
    @Column(name = "animal_gender")
    private Boolean isMale;

    /** Поле имя животного */
    @Column(name = "animal_name")
    private String animalName;

    /** Поле возраст животного */
    @Column(name = "animal_age")
    private int animalAge;

    /** Поле описание животного */
    @Column(name = "description")
    private String description;

    /**Метод БД определяющий зависимость много животных у одного пользователя*/
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "chat_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(animalId, animal.animalId) && Objects.equals(registerDate, animal.registerDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animalId, registerDate);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "animalId=" + animalId +
                ", registerDate=" + registerDate +
                ", gender=" + isMale +
                ", animalName='" + animalName + '\'' +
                ", animalAge=" + animalAge +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }
}