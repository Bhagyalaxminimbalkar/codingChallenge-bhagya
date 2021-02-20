package com.coding.bhagya.companyMerger.utility;

import com.coding.bhagya.companyMerger.entity.BarcodeVO;
import com.coding.bhagya.companyMerger.entity.CatalogVO;
import com.coding.bhagya.companyMerger.entity.ProductCatalog;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CsvUtility {
    private final static String COMMA_DELIMITER = ",";

    public static final String INPUT_PROD_CSV = new File("src/main/resources/input").getAbsolutePath() ;

    public String getFileName(String file) {
        File f = new File(INPUT_PROD_CSV + "/" + file);
        System.out.println("fileNAme "+f.getAbsolutePath());
        return f.getAbsolutePath();
    }

    private  List<List<String>> csvReader(String firstFile) throws IOException {

        List<List<String>> result = Files.readAllLines(Paths.get(getFileName(firstFile)))
                .stream()
                .map(line -> Arrays.asList(line.split(COMMA_DELIMITER)))
                .collect(Collectors.toList());
    return result.subList(1,result.size());
    }

    public List<BarcodeVO> getBarcodeList(String firstFile) throws IOException {
    List<List<String>> resultList = csvReader(firstFile);
        List<BarcodeVO> result = new ArrayList<>();
        resultList.stream().map(
                temp -> result.add(new BarcodeVO(temp.toArray()))).collect(Collectors.toList());
        return result;
    }

    public List<CatalogVO> getCatalogList(String firstFile) throws IOException {
        List<List<String>> resultList = csvReader(firstFile);
        List<CatalogVO> result = new ArrayList<>();
        resultList.stream().map(
                temp -> result.add(new CatalogVO(temp.toArray()))).collect(Collectors.toList());
        return result;

    }

    private String convertToCSV(ProductCatalog data) {
        return Stream.of(data.toString())
                .collect(Collectors.joining(","));
    }

    public void objectToCsv(HashSet<ProductCatalog> productCatalogList, String file) throws IOException {
        File csvOutputFile = new File(file);
        try (
                PrintWriter pw = new PrintWriter(csvOutputFile)) {
            pw.println("SKU,Description,Source");
            productCatalogList.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }
    }

    public void appendExistingCsvFile(String fileName, String contentToAppend) throws IOException {
        Files.write(
                Paths.get(fileName),
                contentToAppend.getBytes(),
                StandardOpenOption.APPEND);

    }

    public void updateExistingCsvFile(String fileName, String s) throws IOException {
        fileName=getFileName(fileName);
        System.out.println(s);
        List<List<String>> resultList =   Files.readAllLines(Paths.get(fileName))
                .stream()
                .map(line -> Arrays.asList(line.split(COMMA_DELIMITER)))
                .collect(Collectors.toList());
        List<CatalogVO> result = new ArrayList<>();
        resultList.stream().filter(temp -> !s.contains(temp.get(0)))
                .map(temp -> result.add(new CatalogVO(temp.toArray())))
                .collect(Collectors.toList());

        File csvOutputFile = new File(fileName);
        try (
                PrintWriter pw = new PrintWriter(csvOutputFile)) {
            result.stream().forEach(pw::println);
        }

       // return result;
    }
}
