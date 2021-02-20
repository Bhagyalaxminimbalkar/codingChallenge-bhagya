package com.coding.bhagya.companyMerger.utility;

import com.coding.bhagya.companyMerger.entity.BarcodeVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class CsvUtilityTest {

    public static final String BARCODES_A_CSV = "barcodesA.csv";

    @Autowired
    public CsvUtility csvUtility = new CsvUtility();

    @Test
    @DisplayName("Test positive for File reading")
    public void testReadingCsv() throws IOException, URISyntaxException {
        List<BarcodeVO> barCodeA = csvUtility.getBarcodeList(BARCODES_A_CSV);
        assertThat(barCodeA.get(0).getBarcode().contains("z2783613083817"),
                is(true));
    }

    @Test
    @DisplayName("Test negative scenario for No file")
    public void testFileINotFound() {
        File csvOutputFile = new File(csvUtility.getFileName("WRONG"));
        assertFalse(csvOutputFile.exists());

    }

}
