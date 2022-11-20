# Cross-Cutting-Project
Spring Boot, React, Design Patterns

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
