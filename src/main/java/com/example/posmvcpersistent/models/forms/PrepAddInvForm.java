package com.example.posmvcpersistent.models.forms;


import com.example.posmvcpersistent.models.Category;
import com.example.posmvcpersistent.models.Vendor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PrepAddInvForm {

    private Iterable<Vendor> vendors;

    private Iterable<Category> categories;

    @NotNull
    @Range(min=1, max=100, message = "Enter an integer between 1 and 100")
    private int lineCount;

    public Iterable<Vendor> getVendors() {
        return vendors;
    }

    @NotNull
    private int vendorId;

    @NotNull
    private int categoryId;

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setVendors(Iterable<Vendor> vendors) {
        this.vendors = vendors;
    }

    public Iterable<Category> getCategories() {
        return categories;
    }

    public void setCategories(Iterable<Category> categories) {
        this.categories = categories;
    }

    public int getLineCount() {
        return lineCount;
    }

    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
    }

    public PrepAddInvForm(){}

    public PrepAddInvForm(Iterable<Vendor> vendors, Iterable<Category> categories, int lineCount){
        this.vendors = vendors;
        this.categories = categories;
        this.lineCount = lineCount;
    }

}
