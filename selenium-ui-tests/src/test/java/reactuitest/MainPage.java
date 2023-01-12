package reactuitest;

import helpers.Urls;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BaseSeleniumPage {
    @FindBy(xpath = "//button[@name=\"Calculate\"]")
    private WebElement calculateButton;

    @FindBy(xpath = "//button[@name=\"Zip\"]")
    private WebElement zipButton;

    @FindBy(xpath = "//button[@name=\"UnZip\"]")
    private WebElement unZipButton;

    @FindBy(xpath = "//button[@name=\"Encrypt\"]")
    private WebElement encryptButton;

    @FindBy(xpath = "//button[@name=\"Decrypt\"]")
    private WebElement decryptButton;

    public MainPage() {
        PageFactory.initElements(driver, this);
        driver.get(Urls.MAIN_URL);
    }
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void clickCalculateButton() {
        calculateButton.click();
    }

    public void clickZipButton() {
        zipButton.click();
    }

    public void clickUnZipButton() {
        unZipButton.click();
    }

    public void clickEncryptButton() {
        encryptButton.click();
    }

    public void clickDecryptButton() {
        decryptButton.click();
    }
}
