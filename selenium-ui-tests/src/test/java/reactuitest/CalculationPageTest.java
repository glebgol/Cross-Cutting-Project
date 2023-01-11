package reactuitest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CalculationPageTest extends BaseSeleniumTest {
    protected final String OUTPUT_TXT = "output.txt";
    protected final long TIME_TO_CALCULATE = 3500;
    protected final long TIME_TO_DOWNLOAD = 4000;
    @AfterMethod(onlyForGroups = OUTPUT_TXT)
    void deleteFile() {
        FileUtil.deleteFile(System.getProperty("user.dir"), OUTPUT_TXT);
    }
    @Test(groups = OUTPUT_TXT)
    public void shouldExpectDownloadFileWhenCalculateZipEncryptedFile() throws InterruptedException {
        CalculationPage page = new CalculationPage();

        page.setFile(TestInfo.EncryptedAndZippedFile);
        page.setOutputFileName(OUTPUT_TXT);
        page.clickZipCheckBox();
        page.selectFileExtension("txt");
        page.setEncryptedKey(TestInfo.Key);

        page.calculate();
        waitForMilliseconds(TIME_TO_CALCULATE);

        page.download();
        waitForMilliseconds(TIME_TO_DOWNLOAD);

        boolean isExist = FileUtil.isExist(OUTPUT_TXT);
        boolean isEnabledSubmitButton = page.isEnabledSubmitButton();
        boolean isEnabledDownloadButton = page.isEnabledDownloadButton();
        String resultInfo = page.getResultText();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(isEnabledSubmitButton, TestInfo.NotValidFormErrorMessage);
        softAssert.assertEquals(resultInfo, TestInfo.ExpectedCalculationResultInfo);
        softAssert.assertTrue(isEnabledDownloadButton, TestInfo.EnabledButtonErrorMessage);
        softAssert.assertTrue(isExist, TestInfo.ErrorMessageForNonExistentFile(OUTPUT_TXT));

        softAssert.assertAll();
    }
}
