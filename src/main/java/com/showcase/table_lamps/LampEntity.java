package com.showcase.table_lamps;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lamps")


public class LampEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer lampId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "stand", nullable = false)
    private String stand;

    @Column(name = "material", nullable = false)
    private String material;

    @Column(name = "power_type", nullable = false)
    private String power_type;

    @Column(name = "height", nullable = false)
    private Double height;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "price", nullable = false)
    private Double price;


    public LampEntity() {
    }

    public LampEntity(String name, String stand, String material, String power_type, Double height, String image, Double price) {
        this.name = name;
        this.stand = stand;
        this.material = material;
        this.power_type = power_type;
        this.height = height;
        this.image = image;
        this.price = price;
    }


    public Integer getLampId() {
        return lampId;
    }
    public void setId(Integer lampId) {
        this.lampId = lampId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getStand() {
        return stand;
    }
    public void setStand(String stand) { this.stand = stand; }

    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) { this.material = material; }

    public String getPower_type() {
        return power_type;
    }
    public void setPower_type(String power_type) { this.power_type = power_type; }

    public Double getHeight() {
        return height;
    }
    public void setHeight(Double height) { this.height = height; }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) { this.price = price; }

    @Override
    public String toString() {

        return "Lamp [lampId=" + lampId + ", name=" + name
                + ", stand=" + stand
                + ", material=" + material
                + ", power_type=" + power_type
                + ", height=" + height
                + ", image=" + image
                + ", price=" + price
                + "]";
    }

    public void setFileName(String resultFileName) {
    }
}
