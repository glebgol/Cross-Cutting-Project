package steps;

import io.cucumber.java.en.Then;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.Assert.assertTrue;

public class DownloadingSteps {
    @Then("download file, verify downloading and delete file")
    public void downloadFileVerifyDownloadingAndDeleteFile() {
        boolean isExist;
        try {
            File file = $(By.id("#download")).download();
            isExist = file.exists();
            file.delete();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        assertTrue(isExist);
    }
}
