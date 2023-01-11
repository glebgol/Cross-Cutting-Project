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

    @Test(groups = OUTPUT_TXT)
    public void shouldExpectDownloadFileWhenCalculateZipEncryptedFile() throws InterruptedException {
        CalculationPage page = new CalculationPage();

        page.setFile(TestInfo.ENCRYPTED_AND_ZIPPED_FILE);
        page.setOutputFileName(OUTPUT_TXT);
        page.clickZipCheckBox();
        page.selectFileExtension("txt");
        page.setEncryptedKey(TestInfo.KEY);

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
        CalculationPage page = new CalculationPage();

        page.setFile(TestInfo.XML_FILE);
        page.setOutputFileName(OUTPUT_XML);
        page.selectFileExtension("xml");

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
        CalculationPage page = new CalculationPage();

        page.setFile(TestInfo.JSON_FILE);
        page.setOutputFileName(OUTPUT_JSON);
        page.selectFileExtension("xml");

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
