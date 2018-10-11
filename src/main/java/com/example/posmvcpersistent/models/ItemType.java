package com.example.posmvcpersistent.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ItemType extends Category{

    @ManyToMany(mappedBy = "itemTypes")
    private List<Category> categories;

    @ManyToMany
    private List<VendorInvoice> invoices;

    @OneToMany
    @JoinColumn(name = "item_type_id")
    private List<InvItem> shopItems = new ArrayList<>();

}
