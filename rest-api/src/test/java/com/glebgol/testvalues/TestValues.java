package com.glebgol.testvalues;

public class TestValues {
    public static final String ZIPPED_AND_TWICE_ENCRYPTED_TXT = System.getProperty("user.dir") + "\\src\\test\\resources\\double_encrypted.zip";
    public static final String XML_FILE = System.getProperty("user.dir") + "\\src\\test\\resources\\input.xml";
    public static final String JSON_FILE = System.getProperty("user.dir") + "\\src\\test\\resources\\input.json";


    public static final String ENCRYPTED_TXT = System.getProperty("user.dir") + "\\src\\test\\resources\\encrypted.txt";
    public static final String OUTPUT_TXT = "output.txt";
    public static final String OUTPUT_XML = "output.xml";
    public static final String OUTPUT_JSON = "output.json";


    public static final String FIRST_KEY = "qwsdcvbgfthyrdfw";
    public static final String SECOND_KEY = "asdfghjkqewrtyto";
    public static final String KEYS = "qwsdcvbgfthyrdfw,asdfghjkqewrtyto";


    public static String MESSAGE_FOR_NOT_UPLOADED_FILE(String fileName) {
        return String.format("File \"%s\" did not uploaded.", fileName);
    }

}
