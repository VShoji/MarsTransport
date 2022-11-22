package br.unicamp.marstransport.models;
import androidx.annotation.NonNull;

import java.util.Objects;

public class City {
    private String name;
    private Coordinates coordinates;

    public City(@NonNull String name, @NonNull Coordinates coordinates) {
        setName(name);
        setCoordinates(coordinates);
    }

    public City(@NonNull String name, double x, double y) {
        setName(name);
        setCoordinates(x, y);
    }

    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        if (name.trim().isEmpty()) throw new IllegalArgumentException("name cannot be empty");
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(@NonNull Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCoordinates(double x, double y) {
        this.coordinates.setX(x);
        this.coordinates.setY(y);
    }

    public void setCoordinateX(double x) {
        this.coordinates.setX(x);
    }

    public void setCoordinateY(double y) {
        this.coordinates.setY(y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return name.equals(city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, coordinates);
    }

    @Override
    public String toString() {
        return "Name: " + name + "Coordinates: " + coordinates;
    }
}