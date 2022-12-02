package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SelenideFilesTest {

    // static {
    //    Configuration.fileDownload = FileDownloadMode.PROXY; //без href
    // }


    @Test
    void selenideDownloadTest() throws Exception {
        open ("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloaderFile = $("#raw-url").download();//href
        try (InputStream is = new FileInputStream(downloaderFile)) {
            byte[] bytes = is.readAllBytes();
            String textContent = new String(bytes, StandardCharsets.UTF_8);
            assertThat(textContent).contains("This repository is the home");

        }

    }

    @Test
    void selenideUploadFile () {
        open("https://fineuploader.com/demos.html");
        $("input[type='file']").uploadFromClasspath("cats_01.jpg");
        $("div.qq-file-info").shouldHave(Condition.text("cats_01.jpg"));
    }

}
