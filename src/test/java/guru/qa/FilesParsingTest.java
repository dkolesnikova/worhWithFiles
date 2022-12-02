package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class FilesParsingTest {

    ClassLoader cl =FilesParsingTest.class.getClassLoader();


    @Test
    void pdfParseTest () throws Exception {
        open("https://junit.org/junit5/docs/current/user-guide/");
        File downLoaderPdf = $("a[href='junit-user-guide-5.9.1.pdf']").download();
        try (InputStream is = new FileInputStream(downLoaderPdf)) {
            PDF content = new PDF(downLoaderPdf);
            assertThat(content.author).contains("Sam Brannen");
        }
    }
    @Test
    void xlsParseTest () throws Exception {
        try (InputStream resourceAsStream = cl.getResourceAsStream("Прайс_WDK 22.12.2020.xlsx")) {
        XLS content = new XLS (resourceAsStream);
        assertThat(content.excel.getSheetAt(0).getRow(2).getCell(1).getStringCellValue()).contains("Окрасочно-сушильные камеры (ОСК)");
        }

    }
    @Test
    void csvParseTest() throws Exception{
        try (
            InputStream resource = cl.getResourceAsStream("qa_guru.csv");
            CSVReader reader = new CSVReader (new InputStreamReader(resource))
        ) {
            List <String[]> content =reader.readAll();
            assertThat(content.get(0)[1]).contains("lesson");
        }

    }
}
