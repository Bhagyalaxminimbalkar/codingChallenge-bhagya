package com.coding.bhagya.companyMerger.service;

import com.coding.bhagya.companyMerger.entity.BarcodeVO;
import com.coding.bhagya.companyMerger.entity.CatalogVO;
import com.coding.bhagya.companyMerger.entity.ProductCatalog;
import com.coding.bhagya.companyMerger.utility.CsvUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class CompanyMergerService {


    @Autowired
    private CsvUtility csvUtility;


    public void mergeCatalog(String files, String outputFile) {
        try {
            String[] file = files.split(",");
            List<BarcodeVO> barCodeA = csvUtility.getBarcodeList(file[0]);
            List<BarcodeVO> barCodeB = csvUtility.getBarcodeList(file[1]);

            List<CatalogVO> catalogA = csvUtility.getCatalogList(file[2]);
            List<CatalogVO> catalogB = csvUtility.getCatalogList(file[3]);

            // traverse catalog A find SKU and barcodeA search in barcode B, if found company is A
            List<CatalogVO> operatedList = new ArrayList<>();

            HashSet<ProductCatalog> productCatalogList = new HashSet<>();
            catalogA.stream().forEach(catA -> {
                productCatalogList.add(new ProductCatalog(catA, "A"));
                barCodeA.stream().forEach(barA -> {
                    if (catA.getSku().equals(barA.getSku())) {
                        barCodeB.stream().filter(barB -> barB.getBarcode().equals(barA.getBarcode())).forEach(barB -> {
                            catalogB.stream()
                                    .filter(b -> b.getSku().equals(barB.getSku()))
                                    .forEach(item -> {
                                        operatedList.add(item);
                                    });
                            catalogB.removeAll(operatedList);
                        });
                    }
                }); //barcodeA end
            });

            catalogB.stream().forEach(catB -> {
                productCatalogList.add(new ProductCatalog(catB, "B"));
            });

            productCatalogList.stream().forEach(System.out::println);
            csvUtility.objectToCsv(productCatalogList, outputFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProductToCatalog(String input) throws IOException, URISyntaxException {
        String[] splitInput = input.split(",");
        csvUtility.appendExistingCsvFile(csvUtility.getFileName(splitInput[2]), splitInput[0] + "," + splitInput[1]);
    }

    public void deleteProductToCatalog(String recordToDelete) throws IOException {
        String[] splitInput = recordToDelete.split(",");
        csvUtility.updateExistingCsvFile(splitInput[2], splitInput[0] + "," + splitInput[1]);

    }
}
