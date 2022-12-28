# Cross-Cutting-Project
Spring Boot, React, Design Patterns, Unit testing, Api testing

### Design Patterns:
1. Decorator 
2. Builder


### Using REST API

| Method | Url                      | Params                    |Description                                                  |
|:-------|:-------------------------|---------------------------|:-------------------------------------------------------------|
| Post   | api/file-reader/calculate| -file'type='file'<br/> -f |Calculates math expressions in file and write to output file |
| Post    | api/file-reader/encrypt  |                           |Encrypts file|
| Post    | api/file-reader/decrypt  |                           |Decrypts file|
| Post    | api/file-reader/zip      |                           |Zip file|
| Post    | api/file-reader/unzip    |                           |Unzip file|

### UNIT Testing JUnit
1. EncryptedFileReader - 4 tests
2. TxtFileReader - 1 test
3. JsonFileReader - 1 test
4. XmlFileReader - 1 test
5. ZipFileReader - 3 tests
6. CalculatingEngine - 9 tests

### API Testing Rest-Assured
1. 12 tests