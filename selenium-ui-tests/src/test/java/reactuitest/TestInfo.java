package reactuitest;

public class TestInfo {
    public static final String Key = "qwsdcvbgfthyrdfw";
    public static final String EncryptedAndZippedFile = System.getProperty("user.dir") + "\\selenium-ui-tests\\src\\test\\resources\\encrypted.zip";
    public static final String ExpectedCalculationResultInfo = "Successfully calculated!";
    public static final String NotValidFormErrorMessage = "Not valid form!";
    public static final String EnabledButtonErrorMessage = "Enabled button!";

    public static String ErrorMessageForNonExistentFile(String fileName) {
        return String.format("File \"%s\" did not downloaded", fileName);
    }

}
