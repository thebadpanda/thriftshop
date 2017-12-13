package com.shop.thrift.dto.Filter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ItemFilter {

    private static final Pattern PEATTERN = Pattern.compile("[0-9]+");

    private String search = "";

    private String maxPrice = "";

    private String minPrice = "";

    private Integer max;

    private Integer min;

    private List<Integer> sizeIds = new ArrayList<>();

    private List<Integer> colorIds = new ArrayList<>();

    private List<Integer> categoryIds = new ArrayList<>();

    private List<Integer> subcategoryIds = new ArrayList<>();

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public List<Integer> getSubcategoryIds() {
        return subcategoryIds;
    }

    public void setSubcategoryIds(List<Integer> subcategoryIds) {
        this.subcategoryIds = subcategoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public static Pattern getPEATTERN() {
        return PEATTERN;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        if(PEATTERN.matcher(maxPrice).matches())max = Integer.valueOf(maxPrice);
        this.maxPrice = maxPrice;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        if(PEATTERN.matcher(minPrice).matches())min = Integer.valueOf(minPrice);
        this.minPrice = minPrice;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public List<Integer> getSizeIds() {
        return sizeIds;
    }

    public void setSizeIds(List<Integer> sizeIds) {
        this.sizeIds = sizeIds;
    }

    public List<Integer> getColorIds() {
        return colorIds;
    }

    public void setColorIds(List<Integer> colorIds) {
        this.colorIds = colorIds;
    }
}
