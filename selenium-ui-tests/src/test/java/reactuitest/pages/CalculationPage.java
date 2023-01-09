package reactuitest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import reactuitest.BaseSeleniumPage;

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

    //private Select fileExtensionSelect = new Select(driver.findElement(By.xpath("//select")));

    @FindBy(xpath = "//form/button")
    private WebElement submitButton;

    @FindBy(xpath = "//button[@name='download']")
    private WebElement downloadButton;

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
        //fileExtensionSelect.selectByValue(extension);
    }

    public void submitCalculation() {
        submitButton.click();
    }

    public boolean isEnabledSubmitButton() {
        return submitButton.isEnabled();
    }

    public void downloadFile() {
        downloadButton.click();
    }

    public boolean isEnabledDownloadButton() {
        return downloadButton.isEnabled();
    }
}
