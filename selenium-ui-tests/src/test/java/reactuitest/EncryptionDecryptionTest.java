package reactuitest;

import helpers.FileUtil;
import helpers.TestValues;
import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class EncryptionDecryptionTest extends BaseSeleniumTest {
    @AfterGroups(groups = "enc-dec-group")
    public void deleteFiles() {
        String path = System.getProperty("user.dir");
        FileUtil.deleteFile(path, TestValues.ENCRYPTED_FILE_NAME);
        FileUtil.deleteFile(path, TestValues.DECRYPTED_FILE_NAME);
    }

    @Test(groups = "enc-dec-group")
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

    @Test(groups = "enc-dec-group", dependsOnMethods = "shouldExpectDownloadFileWhenEncryptFile")
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

    @Test(groups = "enc-dec-group", dependsOnMethods = "shouldExpectDownloadFileWhenDecryptFile")
    public void verifyOriginalFileAndTheEncryptedAndAfterDecryptedFileEqual() throws IOException {
        String originalFileName = TestValues.FILE_TO_ENCRYPT;
        String encryptedAndAfterDecryptedFileName = TestValues.DOWNLOADED_DECRYPTED_FILE;

        boolean isEqual = FileUtil.isEqualFileBytes(originalFileName, encryptedAndAfterDecryptedFileName);

        Assert.assertTrue(isEqual, TestValues.ErrorMessageNotEqualFiles(originalFileName, encryptedAndAfterDecryptedFileName));
    }
}
