package dto;


import annotations.AutoIncrement;
import annotations.Column;
import annotations.PrimaryKey;
import annotations.Table;

import java.util.Objects;


@Table(name = dto.ConstDto.TABLE_NAME_PERSONS)
public class Person {


    @PrimaryKey
    @AutoIncrement
    @Column(name = dto.ConstDto.ID)
    private int id;

    @Column(name = dto.ConstDto.SURNAME)
    private String surname;

    @Column(name = dto.ConstDto.NAME)
    private String name;


    public Person(String surname, String name) {
        this.surname = surname;
        this.name = name;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return dto.ConstDto.TABLE_NAME_PERSONS + "{" +
                dto.ConstDto.ID + "=" + id +
                ", " + dto.ConstDto.SURNAME + "='" + surname + '\'' +
                ", " + dto.ConstDto.NAME + "='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                Objects.equals(surname, person.surname) &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name);
    }


    public int getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }


    public Person setId(int id) {
        this.id = id;
        return this;
    }

    public Person setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }
}
