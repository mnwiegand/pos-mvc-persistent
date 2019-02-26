package com.example.posmvcpersistent.models;

import java.util.List;

public class ListCreationDTO {
    private List<InvItem> invItems;



    public ListCreationDTO() {
    }

    public ListCreationDTO(List<InvItem> invItems) {
            this.invItems = invItems;
        }

    public void addInvItem(InvItem invItem){
        this.invItems.add(invItem);
    }

    public List<InvItem> getInvItems() {
        return invItems;
    }

    public void setInvItems(List<InvItem> invItems) {
        this.invItems = invItems;
    }
}
