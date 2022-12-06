package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class HomeParsingTest {
    ClassLoader cl = HomeParsingTest.class.getClassLoader();

    @Test
    void zipHomeParsingTest() throws Exception {
        try (
                InputStream resources = cl.getResourceAsStream("ezyzip.zip");
                ZipInputStream zis = new ZipInputStream(resources)
                )
        {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                String entryName = entry.getName();

                if (entryName.contains(".xlsx")){
                    XLS content = new XLS(zis);
                    assertThat(content.excel.getSheetAt(0).getRow(2).getCell(1).getStringCellValue()).contains("Окрасочно-сушильные камеры (ОСК)");
                }
                else if (entryName.contains(".pdf")) {
                    PDF content = new PDF(zis);
                    assertThat(content.text).contains("Тестирование Дот Ком");
                }
                else if (entryName.contains(".csv")) {
                    CSVReader reader = new CSVReader (new InputStreamReader(zis));
                    List<String[]> content =reader.readAll();
                    assertThat(content.get(0)[1]).contains("lesson");
                }
            }
        }
    }



}
