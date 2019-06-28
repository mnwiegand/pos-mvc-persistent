package com.example.posmvcpersistent.models;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Batch {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=25, message = "Description must not be empty")
    private String name;

    //date sent, or date received
    // timestamp
    //terms: Net 15, Net 30, Net 60, etc

    @ManyToOne
    private Vendor vendor;
    //Hibernate will create the column vendor_id (based in the field name)

    @ManyToMany
    private List<Category> categories;

    @NotNull
    private Integer lineCount;

    public Batch(){}

    public Batch(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Integer getLineCount() {
        return lineCount;
    }

    public void setLineCount(Integer lineCount) {
        this.lineCount = lineCount;
    }
}
