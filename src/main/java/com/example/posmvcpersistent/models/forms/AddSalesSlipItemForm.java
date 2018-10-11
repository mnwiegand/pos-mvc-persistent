package com.example.posmvcpersistent.models.forms;

import com.example.posmvcpersistent.models.InvItem;
import com.example.posmvcpersistent.models.SalesSlip;

import javax.validation.constraints.NotNull;

//not persistent
public class AddSalesSlipItemForm {

    private SalesSlip salesSlip;

    // to contain the options offered to the user
    private Iterable<InvItem> shopItems;

    public Iterable<InvItem> getShopItems() { return shopItems; }

    @NotNull
    private int salesSlipId;

    @NotNull
    private int shopItemId;

    public void setShopItems(Iterable<InvItem> shopItems) {
        this.shopItems = shopItems;
    }

    public SalesSlip getSalesSlip() {
        return salesSlip;
    }

    public void setSalesSlip(SalesSlip salesSlip) {
        this.salesSlip = salesSlip;
    }

    public int getSalesSlipId() {
        return salesSlipId;
    }

    public void setSalesSlipId(int SalesSlipId) {
        this.salesSlipId = salesSlipId;
    }

    public int getShopItemId() {
        return shopItemId;
    }

    public void setShopItemId(int shopItemId) {
        this.shopItemId = shopItemId;
    }

    public AddSalesSlipItemForm(){}

    public AddSalesSlipItemForm(SalesSlip salesSlip, Iterable<InvItem> shopItems){
        this.salesSlip = salesSlip;
        this.shopItems = shopItems;
    }
}
