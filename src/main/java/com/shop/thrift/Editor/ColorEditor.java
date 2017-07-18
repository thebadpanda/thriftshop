package com.shop.thrift.Editor;

import com.shop.thrift.Entity.Color;
import com.shop.thrift.Services.ColorService;

import java.beans.PropertyEditorSupport;

public class ColorEditor extends PropertyEditorSupport{

    private final ColorService colorService;

    public ColorEditor(ColorService colorService){
        this.colorService = colorService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Color color = colorService.findOne(Integer.valueOf(text));
        setValue(color);
    }

}
