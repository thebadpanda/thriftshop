package com.shop.thrift.Editor;

import com.shop.thrift.Entity.Basket;
import com.shop.thrift.Services.BasketService;

import java.beans.PropertyEditorSupport;

public class BasketEditor extends PropertyEditorSupport {

    private final BasketService basketService;

    public BasketEditor(BasketService basketService){
        this.basketService = basketService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Basket basket = basketService.findOne(Integer.valueOf(text));
        setValue(basket);
    }
}
