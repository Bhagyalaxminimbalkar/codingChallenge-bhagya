package com.coding.bhagya.companyMerger.controller;

import com.coding.bhagya.companyMerger.service.CompanyMergerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
public class ProductCatalogController {

    public static final String OUTPUT_PROD_CSV = new File("src/main/resources/output").getAbsolutePath() + "/" + "MergedProductCatalog.csv";
    public static final String INPUT_PROD_CSV = new File("src/main/resources/input").getAbsolutePath();

    @Autowired
    private CompanyMergerService companyMergerService;

    @PostMapping(path = "/upload-csv-file")
    public String uploadCSVFile(@RequestBody String files) {
        companyMergerService.mergeCatalog(files,OUTPUT_PROD_CSV);
        return HttpStatus.OK.toString();
    }

    @PostMapping(path = "/addProduct")
    public String addProduct(@RequestBody String recordToAdd) {
        try {
            companyMergerService.addProductToCatalog(recordToAdd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return HttpStatus.OK.toString();
    }


    @PostMapping(path = "/deleteProduct")
    public String deleteProduct(@RequestBody String recordToDelete) throws IOException {
        companyMergerService.deleteProductToCatalog(recordToDelete);
        return HttpStatus.OK.toString();
    }

//    @PostMapping(path = "/updateProduct")
//    public String updateProduct(@RequestBody String file) {
//        companyMergerService.updateProductToCatalog(file);
//        return HttpStatus.OK.toString();
//    }
//



}
