package com.animals.app.domain;

import java.io.Serializable;

/**
 * Created by Rostyslav.Viner on 06.08.2015.
 */
public class AnimalBreed implements Serializable {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnimalBreed that = (AnimalBreed) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "AnimalBreed{" +
                "id=" + id +
                '}';
    }
}
