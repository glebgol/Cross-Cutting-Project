# Cross-Cutting-Project
Rest-Service, Unit-Testing, Api-Testing, UI-Testing, Design Patterns, React

### Design Patterns:
1. Decorator 
2. Builder


### Using REST API

| Method | Url                     | Params                                                            | Description                                                                                                                                                       |
|:-------|:------------------------|:------------------------------------------------------------------|:------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| POST   | api/v1/calculate        | file<br/>outputfile<br/>iszipped<br/>decryptionkeys<br/>extension | Calculates math expressions in file and write to output file. Input file can be encrypted any number of times and archived. Supported extensions: txt, xml, json. |
| POST   | api/v1/encrypt          | file<br/>outputfile<br/>key                                       | Encrypts file                                                                                                                                                     |
| POST   | api/v1r/decrypt         | file<br/>outputfile<br/>key                                       | Decrypts file                                                                                                                                                     |
| POST   | api/v1/zip              | file                                                              | Zip file                                                                                                                                                          |
| POST   | api/v1/unzip            | file<br/>outputfile                                               | Unzip file                                                                                                                                                        |
| GET    | downloadFile/{filecode} |                                                                   | Downloads file                                                                                                                                                    |

### UNIT Testing JUnit

### API Testing Rest-Assured and JUNIT

### UI-testing with Selenium WebDriver and TestNG

### React JS