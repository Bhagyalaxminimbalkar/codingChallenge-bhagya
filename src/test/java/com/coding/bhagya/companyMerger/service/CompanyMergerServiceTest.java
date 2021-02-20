package com.coding.bhagya.companyMerger.service;

import com.coding.bhagya.companyMerger.utility.CsvUtility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CompanyMergerServiceTest {

    public static final String OUTPUT_PROD_CSV = new File("src/main/resources/output").getAbsolutePath() + "/" + "MergedProductCatalog.csv";
    public static final String CSV_FILES = "barcodesA.csv,barcodesB.csv,catalogA.csv,catalogB.csv";

    @Autowired
    public CsvUtility csvUtility;
    @Autowired
    public CompanyMergerService companyMergerService;

    @Test
    public void testMainService() throws URISyntaxException {
        String files = CSV_FILES;

        companyMergerService.mergeCatalog(files, OUTPUT_PROD_CSV);
        File csvOutputFile = new File(OUTPUT_PROD_CSV);
        assertTrue(csvOutputFile.exists());

    }

}
