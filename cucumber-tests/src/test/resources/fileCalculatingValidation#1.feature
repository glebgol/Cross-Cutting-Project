Feature: file calculating validation
  Background: main page of the site
    Given open site "http://localhost:3000/calculate"

  Scenario: entered invalid output filename
    When attached file "D:\Cross-Cutting-Project\cucumber-tests\testfiles\helloworld.txt"
    And type to input with name "outputFile" text: "invalidfile"
    And select "Txt" file extension
    Then appears message "Invalid filename"
    And button with name "calculate" should be disabled
    And button with name "download" should be disabled

  Scenario: entered invalid encryption keys
    When attached file "D:\Cross-Cutting-Project\cucumber-tests\testfiles\helloworld.txt"
    And type to input with name "key" text: "111"
    And type to input with name "outputFile" text: "output.txt"
    And select "Txt" file extension
    Then appears message "Invalid keys"
    And button with name "calculate" should be disabled
    And button with name "download" should be disabled
