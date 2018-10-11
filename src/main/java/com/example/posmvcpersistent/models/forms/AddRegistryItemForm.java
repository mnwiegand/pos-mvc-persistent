package com.example.posmvcpersistent.models.forms;

import com.example.posmvcpersistent.models.InvItem;
import com.example.posmvcpersistent.models.Registry;

import javax.validation.constraints.NotNull;

//not persistent
public class AddRegistryItemForm {

    private Registry registry;

    // to contain the options offered to the user
    private Iterable<InvItem> shopItems;

    public Iterable<InvItem> getShopItems() { return shopItems; }

    @NotNull
    private int registryId;

    @NotNull
    private int shopItemId;

    public void setShopItems(Iterable<InvItem> shopItems) {
        this.shopItems = shopItems;
    }

    public Registry getRegistry() {
        return registry;
    }

    public void setRegistry(Registry registry) {
        this.registry = registry;
    }

    public int getRegistryId() {
        return registryId;
    }

    public void setRegistryId(int RegistryId) {
        this.registryId = registryId;
    }

    public int getShopItemId() {
        return shopItemId;
    }

    public void setShopItemId(int shopItemId) {
        this.shopItemId = shopItemId;
    }

    public AddRegistryItemForm(){}

    public AddRegistryItemForm(Registry registry, Iterable<InvItem> shopItems){
        this.registry = registry;
        this.shopItems = shopItems;
    }
}
