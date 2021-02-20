package com.coding.bhagya.companyMerger.entity;

public class CatalogVO {
    private String sku;
    private String description;

    public CatalogVO(Object[] perObject) {
        this.sku = (String) perObject[0];
        this.description = (String) perObject[1];

    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return
                 sku + "," + description + ' ';
    }
}
