package com.shop.thrift.Entity;

        import java.math.BigDecimal;
        import java.util.ArrayList;
        import java.util.List;

        import javax.persistence.Column;
        import javax.persistence.Entity;
        import javax.persistence.FetchType;
        import javax.persistence.GeneratedValue;
        import javax.persistence.GenerationType;
        import javax.persistence.Id;
        import javax.persistence.JoinColumn;
        import javax.persistence.JoinTable;
        import javax.persistence.ManyToMany;
        import javax.persistence.ManyToOne;
        import javax.persistence.Table;

@Entity
@Table(name = "item")

public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="item_name")
    private String name;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public List<Basket> getBasket() {
        return basket;
    }

    public void setBasket(List<Basket> basket) {
        this.basket = basket;
    }

    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size_id")
    private Size size;

    private int sizeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subcategory_id")
    private  Subcategory subcategory;

    private int subcategoryId;


    //public Category getCategory() {
      //  return subcategory.getCategory();
    //}
//
//    //TODO:possible error here!
//    public void setCategory(Category category) {
//        this.category = category;
//    }
//
//    public int getCategoryId() {
//        return categoryId;
//    }
//
//    public void setCategoryId(int categoryId) {
//        this.categoryId = categoryId;
//    }

    //TODO: delete this segment and getters/setters or add annotations

    //private Category category;

    //private int categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id")
    private Color color;

    private int colorId;


    @ManyToMany(mappedBy = "item")
    private List<Basket> basket = new ArrayList<>();


    public int getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(int subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}