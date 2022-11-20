# Cross-Cutting-Project
Spring Boot, React, Design Patterns, Unit testing, Api testing

### Design Patterns:
1. Decorator 
2. Builder


### Using REST API

| Method| Url                                                                                                                                                                             | Description                                                  |
|:------|:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:-------------------------------------------------------------|
| GET   | api/file-reader/calculate/?inputfile={inputfileName}&outputfile={outputfileName}&iszipped={true/false}&decryptionkeys={keys}&extension={extension}                              | Calculates math expressions in file and write to output file |
| GET   | api/file-reader/encrypt/?inputfile={inputfileName}&outputfile={outputfileName}&key={key}                                                                                        | Encrypts file                                                |
| GET   | api/file-reader/decrypt/?inputfile={inputfileName}&outputfile={outputfileName}&key={key}                                                                                        | Decrypts file                                                |
| GET   | api/file-reader/zip/?inputfile={inputfileName}                                                                                                                                  | Zip file                                                     |
| GET   | api/file-reader/unzip/?inputfile={inputfileName}&outputfile={outputfileName}                                                                                                    | Unzip file                                                   |

### UNIT Testing
1. EncryptedFileReader - 4 tests
2. TxtFileReader - 1 test
3. JsonFileReader - 1 test
4. XmlFileReader - 1 test
5. ZipFileReader - 3 tests
6. CalculatingEngine - 9 tests

### API Testing
1. 12 tests