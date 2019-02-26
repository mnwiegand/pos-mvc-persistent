package com.example.posmvcpersistent.models.forms;


import com.example.posmvcpersistent.models.Category;
import com.example.posmvcpersistent.models.Vendor;

import javax.validation.constraints.NotNull;

public class AddVendorCategoryForm {

    private Vendor vendor;

    private Iterable<Category> categories;

    public Iterable<Category> getCategories() {
        return categories;
    }

    @NotNull
    private int vendorId;

    @NotNull
    private int categoryId;

    public void setCategories(Iterable<Category> categories) {
        this.categories = categories;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

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

    public AddVendorCategoryForm(){}

    public AddVendorCategoryForm(Vendor vendor, Iterable<Category> categories){
        this.vendor = vendor;
        this.categories = categories;
    }
}
