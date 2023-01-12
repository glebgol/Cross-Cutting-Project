package reactuitest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EncryptionDecryptionTest extends BaseSeleniumTest {
    @AfterClass
    public void deleteFiles() {
        //FileUtil.deleteFile(System.getProperty("user.dir"), TestValues.ENCRYPTED_FILE_NAME);
    }
    @Test
    public void shouldExpectDownloadFileWhenEncryptFile() throws InterruptedException {
        EncryptionPage page = new EncryptionPage()
                .setFile(TestValues.FILE_TO_ENCRYPT)
                .setEncryptedKey(TestValues.KEY)
                .setOutputFileName(TestValues.ENCRYPTED_FILE_NAME);

        page.encrypt();
        waitForMilliseconds(2000);

        page.download();
        waitForMilliseconds(2000);

        boolean isExist = FileUtil.isExist(TestValues.ENCRYPTED_FILE_NAME);
        boolean isEnabledEncryptButton = page.isEnabledEncryptButton();
        boolean isEnabledDownloadButton = page.isEnabledDownloadButton();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(isEnabledEncryptButton, TestValues.NOT_VALID_FORM);
        softAssert.assertTrue(isEnabledDownloadButton, TestValues.DISABLED_DOWNLOAD_BUTTON);
        softAssert.assertTrue(isExist, TestValues.ErrorMessageForNonExistentFile(TestValues.ENCRYPTED_FILE_NAME));
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = "shouldExpectDownloadFileWhenEncryptFile")
    public void shouldExpectDownloadFileWhenDecryptFile() throws InterruptedException {
        DecryptionPage page = new DecryptionPage()
                .setFile(System.getProperty("user.dir") + "\\" + TestValues.ENCRYPTED_FILE_NAME)
                .setDecryptedKey(TestValues.KEY)
                .setOutputFileName(TestValues.DECRYPTED_FILE_NAME)
                .clickDecrypt()
                .clickDownload();

        boolean isExist = FileUtil.isExist(System.getProperty("user.dir") + "\\" + TestValues.DECRYPTED_FILE_NAME);
        boolean isEnabledEncryptButton = page.isEnabledEncryptButton();
        boolean isEnabledDownloadButton = page.isEnabledDownloadButton();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(isEnabledEncryptButton, TestValues.NOT_VALID_FORM);
        softAssert.assertTrue(isEnabledDownloadButton, TestValues.DISABLED_DOWNLOAD_BUTTON);
        softAssert.assertTrue(isExist, TestValues.ErrorMessageForNonExistentFile(TestValues.FILE_TO_ENCRYPT));
        softAssert.assertAll();
    }
}
