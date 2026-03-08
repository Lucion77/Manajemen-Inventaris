package com.wms.model;

/**
 * Kelas abstrak dasar untuk entitas database.
 * Menunjukkan penerapan konsep Inheritance dan Properties.
 */
public abstract class BaseEntity {
    protected int id;

    public BaseEntity() {
    }

    public BaseEntity(int id) {
        this.id = id;
    }

    // Properties (Getter / Setter) dengan hak akses public
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Polymorphism: abstract method to be implemented by child
    public abstract String getDetails();
}
