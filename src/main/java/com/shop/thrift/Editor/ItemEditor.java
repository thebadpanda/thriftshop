package com.shop.thrift.Editor;

import com.shop.thrift.Entity.Item;
import com.shop.thrift.Services.ItemService;

import java.beans.PropertyEditorSupport;

public class ItemEditor extends PropertyEditorSupport {

    private final ItemService itemService;

    public ItemEditor(ItemService itemService){
        this.itemService = itemService;
    }

    @Override
    public void setAsText(String text){
        Item item =  itemService.findOne(Integer.valueOf(text));
        setValue(item);
    }

}
