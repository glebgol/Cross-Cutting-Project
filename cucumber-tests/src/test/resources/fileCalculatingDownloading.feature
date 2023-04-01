Feature: calculating and downloading file
  Background: main page of the site
    Given open site "http://localhost:3000/calculate"

  Scenario: unencrypted txt file
    When attached file "D:\Cross-Cutting-Project\cucumber-tests\testfiles\helloworld.txt"
    And type to input with name "outputFile" text: "output.txt"
    And select "Txt" file extension
    And click "calculate" button
    Then download file, verify downloading and delete file

  Scenario: encrypted txt file
    When attached file "D:\Cross-Cutting-Project\cucumber-tests\testfiles\encrypted.txt"
    And type to input with name "outputFile" text: "output.txt"
    And select "Txt" file extension
    And type to input with name "key" text: "qwsdcvbgfthyrdfw"
    And click "calculate" button
    Then download file, verify downloading and delete file

  Scenario: encrypted and archived file
    When attached file "D:\Cross-Cutting-Project\cucumber-tests\testfiles\encrypted.zip"
    And type to input with name "outputFile" text: "output.txt"
    And click checkbox
    And select "Txt" file extension
    And type to input with name "key" text: "qwsdcvbgfthyrdfw"
    And click "calculate" button
    Then download file, verify downloading and delete file
