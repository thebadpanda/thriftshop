package com.shop.thrift.Editor;

import com.shop.thrift.Entity.Size;
import com.shop.thrift.Services.SizeService;

import java.beans.PropertyEditorSupport;

public class SizeEditor extends PropertyEditorSupport{

    private final SizeService sizeService;

    public SizeEditor(SizeService sizeService){
        this.sizeService = sizeService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        Size size = sizeService.findOne(Integer.valueOf(text));
        setValue(size);
    }
}
