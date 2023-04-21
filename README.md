## Cross-Cutting-Project
* Counting arithmetic expressions in an encrypted any number of times and archived file (txt, xml, json);
* encrypting-decrypting files; 
* zip-unzip files.

Rest-Service: `Spring Boot`

Web UI: `React JS`

Used test-runners: `JUnit, TestNG`

Integration testing: `REST-assured`

UI testing: `Selenium WebDriver, Selenide`

BDD testing: `Cucumber`

### Used Design Patterns:
#### 1. Decorator
```java 
IFileReader reader = new ZipFileReader(
   new EncryptedFileReader(SECOND_KEY,
   new EncryptedFileReader(FIRST_KEY,
   new TxtFileReader(TWICE_ENCRYPTED_ZIP))));
```
#### 2. Builder
```java 
IFileReaderBuilder builder = new FileReaderBuilder("txt", TWICE_ENCRYPTED_ZIP);
builder.setEncrypting(FIRST_KEY);
builder.setEncrypting(SECOND_KEY);
builder.setZipping(true);

IFileReader reader = builder.getFileReader();
```
#### 3. Template Method
```java
public interface IFileReader {
    String getInputFilename();
    
    void write(IStream stream, String outputFilename);
    IStream read();
    IStream transform(IStream stream);
    IStream calculate(IStream stream);
    
    default void calculate(String outputFileName) {
        IStream readingResult = read();
        IStream calculatedResult = calculate(readingResult);
        write(calculatedResult, outputFileName);
    }
}
```
