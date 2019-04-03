package br.com.emersonmendes.dto;

import java.io.Serializable;
import java.util.Objects;

public class Car implements Serializable {

    public Car(String color, Integer year, String name) {
        this.color = color;
        this.year = year;
        this.name = name;
    }

    public Car() {
    }

    private String color;

    private Integer year;

    private String name;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(color, car.color) &&
                Objects.equals(year, car.year) &&
                Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, year, name);
    }

    @Override
    public String toString() {
        return "Car { color='" + color + '\'' + ", year=" + year + ", name='" + name + '\'' + '}';
    }

}
