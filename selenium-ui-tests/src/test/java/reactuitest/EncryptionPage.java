package reactuitest;

import helpers.TestValues;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EncryptionPage extends BaseSeleniumPage {
    private final String URL = "http://localhost:3000/encrypt";

    @FindBy(xpath = "//input[@type='file']")
    private WebElement fileInput;

    @FindBy(xpath = "//input[@name='outputFile']")
    private WebElement outputFileNameInput;

    @FindBy(xpath = "//input[@name='key']")
    private WebElement keyInput;

    @FindBy(xpath = "//button[@name='encrypt']")
    private WebElement encryptButton;

    @FindBy(xpath = "//button[@name=\"download\"]")
    private WebElement downloadButton;

    public EncryptionPage() {
        driver.get(URL);
        PageFactory.initElements(driver, this);
    }

    public EncryptionPage setFile(String filePath) {
        fileInput.sendKeys(filePath);
        return this;
    }

    public EncryptionPage setOutputFileName(String fileName) {
        outputFileNameInput.sendKeys(fileName);
        return this;
    }

    public EncryptionPage setEncryptedKey(String key) {
        keyInput.sendKeys(key);
        return this;
    }

    public EncryptionPage clickEncrypt() throws InterruptedException {
        encryptButton.click();
        Thread.sleep(TestValues.TIME_TO_ENC_DEC);
        return this;
    }

    public EncryptionPage clickDownload() throws InterruptedException {
        downloadButton.click();
        Thread.sleep(TestValues.TIME_TO_DOWNLOAD);
        return this;
    }

    public boolean isEnabledEncryptButton() {
        return encryptButton.isEnabled();
    }

    public boolean isEnabledDownloadButton() {
        return downloadButton.isEnabled();
    }
}
