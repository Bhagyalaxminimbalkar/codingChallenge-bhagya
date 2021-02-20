package com.coding.bhagya.companyMerger.entity;

public class BarcodeVO {

    private int supplierID;
    private String sku;
    private String barcode;

    public BarcodeVO(Object[] perObject) {
        this.supplierID = (Integer.parseInt(String.valueOf(perObject[0])));
        this.sku = (String) perObject[1];
        this.barcode = (String) perObject[2];
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }


}
