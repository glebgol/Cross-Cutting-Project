package reactuitest;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class EncryptionDecryptionTest extends BaseSeleniumTest {
    @AfterClass
    public void deleteFiles() {
        FileUtil.deleteFile(System.getProperty("user.dir"), TestValues.ENCRYPTED_FILE_NAME);
        FileUtil.deleteFile(System.getProperty("user.dir"), TestValues.DECRYPTED_FILE_NAME);
    }
    @Test
    public void shouldExpectDownloadFileWhenEncryptFile() throws InterruptedException {
        String encryptedFileName = TestValues.ENCRYPTED_FILE_NAME;
        EncryptionPage page = new EncryptionPage()
                .setFile(TestValues.FILE_TO_ENCRYPT)
                .setEncryptedKey(TestValues.KEY)
                .setOutputFileName(encryptedFileName)
                .clickEncrypt()
                .clickDownload();

        boolean isExist = FileUtil.isExist(encryptedFileName);
        boolean isEnabledEncryptButton = page.isEnabledEncryptButton();
        boolean isEnabledDownloadButton = page.isEnabledDownloadButton();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(isEnabledEncryptButton, TestValues.NOT_VALID_FORM);
        softAssert.assertTrue(isEnabledDownloadButton, TestValues.DISABLED_DOWNLOAD_BUTTON);
        softAssert.assertTrue(isExist, TestValues.ErrorMessageForNonExistentFile(encryptedFileName));
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = "shouldExpectDownloadFileWhenEncryptFile")
    public void shouldExpectDownloadFileWhenDecryptFile() throws InterruptedException {
        DecryptionPage page = new DecryptionPage()
                .setFile(TestValues.DOWNLOADED_ENCRYPTED_FILE)
                .setDecryptedKey(TestValues.KEY)
                .setOutputFileName(TestValues.DECRYPTED_FILE_NAME)
                .clickDecrypt()
                .clickDownload();

        boolean isExist = FileUtil.isExist(TestValues.DECRYPTED_FILE_NAME);
        boolean isEnabledEncryptButton = page.isEnabledEncryptButton();
        boolean isEnabledDownloadButton = page.isEnabledDownloadButton();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(isEnabledEncryptButton, TestValues.NOT_VALID_FORM);
        softAssert.assertTrue(isEnabledDownloadButton, TestValues.DISABLED_DOWNLOAD_BUTTON);
        softAssert.assertTrue(isExist, TestValues.ErrorMessageForNonExistentFile(TestValues.FILE_TO_ENCRYPT));
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = "shouldExpectDownloadFileWhenDecryptFile")
    public void verifyOriginalFileAndTheEncryptedAndAfterDecryptedFileEqual() throws IOException {
        boolean isEqual = FileUtil.isEqualFileBytes(TestValues.FILE_TO_ENCRYPT, System.getProperty("user.dir") + "\\" + TestValues.DECRYPTED_FILE_NAME);
        Assert.assertTrue(isEqual, TestValues.FILE_TO_ENCRYPT + "   " + System.getProperty("user.dir") + "\\" + TestValues.DECRYPTED_FILE_NAME);
    }
}
