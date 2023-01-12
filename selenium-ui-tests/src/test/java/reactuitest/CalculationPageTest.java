package reactuitest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CalculationPageTest extends BaseSeleniumTest {
    protected final String OUTPUT_TXT = "output.txt";
    protected final String OUTPUT_XML = "output.xml";
    protected final String OUTPUT_JSON = "output.json";

    protected final long TIME_TO_CALCULATE = 3500;
    protected final long TIME_TO_DOWNLOAD = 4000;

    @AfterMethod(onlyForGroups = OUTPUT_TXT)
    void deleteTxtFile() {
        FileUtil.deleteFile(System.getProperty("user.dir"), OUTPUT_TXT);
    }

    @AfterMethod(onlyForGroups = OUTPUT_XML)
    void deleteXmlFile() {
        FileUtil.deleteFile(System.getProperty("user.dir"), OUTPUT_XML);
    }

    @AfterMethod(onlyForGroups = OUTPUT_JSON)
    void deleteJsonFile() {
        FileUtil.deleteFile(System.getProperty("user.dir"), OUTPUT_JSON);
    }

    @Test(groups = OUTPUT_TXT)
    public void shouldExpectDownloadFileWhenCalculateZipEncryptedFile() throws InterruptedException {
        CalculationPage page = new CalculationPage()
                .setFile(TestInfo.ENCRYPTED_AND_ZIPPED_FILE)
                .setOutputFileName(OUTPUT_TXT)
                .clickZipCheckBox()
                .selectFileExtension("txt")
                .setEncryptedKey(TestInfo.KEY);

        page.calculate();
        waitForMilliseconds(TIME_TO_CALCULATE);
        page.download();
        waitForMilliseconds(TIME_TO_DOWNLOAD);

        boolean isExist = FileUtil.isExist(OUTPUT_TXT);
        boolean isEnabledSubmitButton = page.isEnabledSubmitButton();
        boolean isEnabledDownloadButton = page.isEnabledDownloadButton();
        String resultInfo = page.getResultText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(isEnabledSubmitButton, TestInfo.NOT_VALID_FORM);
        softAssert.assertEquals(resultInfo, TestInfo.SUCCESSFULLY_CALCULATED);
        softAssert.assertTrue(isEnabledDownloadButton, TestInfo.DISABLED_DOWNLOAD_BUTTON);
        softAssert.assertTrue(isExist, TestInfo.ErrorMessageForNonExistentFile(OUTPUT_TXT));
        softAssert.assertAll();
    }

    @Test(groups = OUTPUT_XML)
    public void shouldExpectDownloadFileWhenCalculateXmlFile() throws InterruptedException {
        CalculationPage page = new CalculationPage()
                .setFile(TestInfo.XML_FILE)
                .setOutputFileName(OUTPUT_XML)
                .selectFileExtension("xml");

        page.calculate();
        waitForMilliseconds(TIME_TO_CALCULATE);
        page.download();
        waitForMilliseconds(TIME_TO_DOWNLOAD);

        boolean isExist = FileUtil.isExist(OUTPUT_XML);
        boolean isEnabledSubmitButton = page.isEnabledSubmitButton();
        boolean isEnabledDownloadButton = page.isEnabledDownloadButton();
        String resultInfo = page.getResultText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(isEnabledSubmitButton, TestInfo.NOT_VALID_FORM);
        softAssert.assertEquals(resultInfo, TestInfo.SUCCESSFULLY_CALCULATED);
        softAssert.assertTrue(isEnabledDownloadButton, TestInfo.DISABLED_DOWNLOAD_BUTTON);
        softAssert.assertTrue(isExist, TestInfo.ErrorMessageForNonExistentFile(OUTPUT_XML));
        softAssert.assertAll();
    }

    @Test(groups = OUTPUT_JSON)
    public void shouldExpectDownloadFileWhenCalculateJsonFile() throws InterruptedException {
        CalculationPage page = new CalculationPage()
                .setFile(TestInfo.JSON_FILE)
                .setOutputFileName(OUTPUT_JSON)
                .selectFileExtension("json");

        page.calculate();
        waitForMilliseconds(TIME_TO_CALCULATE);
        page.download();
        waitForMilliseconds(TIME_TO_DOWNLOAD);

        boolean isExist = FileUtil.isExist(OUTPUT_JSON);
        boolean isEnabledSubmitButton = page.isEnabledSubmitButton();
        boolean isEnabledDownloadButton = page.isEnabledDownloadButton();
        String resultInfo = page.getResultText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(isEnabledSubmitButton, TestInfo.NOT_VALID_FORM);
        softAssert.assertEquals(resultInfo, TestInfo.SUCCESSFULLY_CALCULATED);
        softAssert.assertTrue(isEnabledDownloadButton, TestInfo.DISABLED_DOWNLOAD_BUTTON);
        softAssert.assertTrue(isExist, TestInfo.ErrorMessageForNonExistentFile(OUTPUT_JSON));
        softAssert.assertAll();
    }
}
