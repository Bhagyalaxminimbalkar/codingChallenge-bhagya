package com.coding.bhagya.companyMerger.entity;

public class ProductCatalog {
    private String sku;
    private String description;
    private String company;


    public ProductCatalog(CatalogVO catA, String companyType) {
        this.sku= catA.getSku();
        this.description = catA.getDescription();
        this.company = companyType;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return ""  + sku + '\'' +
                "," + description + '\'' +
                "," + company + '\'' ;
    }
}
