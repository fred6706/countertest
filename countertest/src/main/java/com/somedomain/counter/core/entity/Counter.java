package com.somedomain.counter.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Objects;


@Entity
public class Counter {


    private Integer id;
    @Column(unique = true)
    private String name;
    private Integer value;



    public Counter() {
    }

    public Counter(String name) {
        this(name, 0);
    }

    public Counter(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Transient
    public void bumpValue() {
        value += 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Counter)) {
            return false;
        }
        Counter counter = (Counter) o;
        return Objects.equals(id, counter.id) && Objects.equals(name, counter.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Counter{" +
               "name='" + name + '\'' +
               ", value=" + value +
               '}';
    }

}
