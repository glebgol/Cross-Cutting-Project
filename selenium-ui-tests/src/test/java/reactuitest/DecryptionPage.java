package reactuitest;

import helpers.TestValues;
import helpers.Urls;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DecryptionPage extends BaseSeleniumPage {
    @FindBy(xpath = "//input[@type='file']")
    private WebElement fileInput;

    @FindBy(xpath = "//input[@name='outputFile']")
    private WebElement outputFileNameInput;

    @FindBy(xpath = "//input[@name='key']")
    private WebElement keyInput;

    @FindBy(xpath = "//form/button")
    private WebElement decryptButton;

    @FindBy(xpath = "//button[@name='download']")
    private WebElement downloadButton;

    public DecryptionPage() {
        driver.get(Urls.DECRYPT_URL);
        PageFactory.initElements(driver, this);
    }

    public DecryptionPage setFile(String filePath) {
        fileInput.sendKeys(filePath);
        return this;
    }

    public DecryptionPage setOutputFileName(String fileName) {
        outputFileNameInput.sendKeys(fileName);
        return this;
    }

    public DecryptionPage setDecryptedKey(String key) {
        keyInput.sendKeys(key);
        return this;
    }

    public DecryptionPage clickDecrypt() throws InterruptedException {
        decryptButton.click();
        Thread.sleep(TestValues.TIME_TO_ENC_DEC);
        return this;
    }

    public DecryptionPage clickDownload() throws InterruptedException {
        downloadButton.click();
        Thread.sleep(TestValues.TIME_TO_DOWNLOAD);
        return this;
    }

    public boolean isEnabledEncryptButton() {
        return decryptButton.isEnabled();
    }

    public boolean isEnabledDownloadButton() {
        return downloadButton.isEnabled();
    }
}
