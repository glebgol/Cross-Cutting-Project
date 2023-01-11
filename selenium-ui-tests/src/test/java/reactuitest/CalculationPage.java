package reactuitest;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CalculationPage extends BaseSeleniumPage {
    private final String URL = "http://localhost:3000/calculate";

    @FindBy(xpath = "//form/input[@name='file']")
    private WebElement fileInput;

    @FindBy(xpath = "//form/input[@name='outputFile']")
    private WebElement outputFileNameInput;

    @FindBy(xpath = "//form/input[@type='checkbox']")
    private WebElement izZippedCheckBox;

    @FindBy(xpath = "//form/input[@name='key']")
    private WebElement encryptedKeyInput;

    @FindBy(xpath = "//select")
    private WebElement fileExtensionSelect;
    
    @FindBy(xpath = "//form/button")
    private WebElement submitButton;

    @FindBy(xpath = "//button[@name='download']")
    private WebElement downloadButton;

    @FindBy(xpath = "//p/b")
    public WebElement resultInfo;

    public CalculationPage() {
        driver.get(URL);
        PageFactory.initElements(driver, this);
    }

    public void setFile(String filePath) {
        fileInput.sendKeys(filePath);
    }

    public void setOutputFileName(String fileName) {
        outputFileNameInput.sendKeys(fileName);
    }

    public void clickZipCheckBox() {
        izZippedCheckBox.click();
    }

    public void setEncryptedKey(String key) {
        encryptedKeyInput.sendKeys(key);
    }

    public void selectFileExtension(String extension) {
        fileExtensionSelect.sendKeys(extension);
    }

    public void calculate() {
        submitButton.click();
    }

    public boolean isEnabledSubmitButton() {
        return submitButton.isEnabled();
    }

    public void download() {
        downloadButton.click();
    }

    public boolean isEnabledDownloadButton() {
        return downloadButton.isEnabled();
    }

    public String getResultText() {
        return resultInfo.getText();
    }
}
