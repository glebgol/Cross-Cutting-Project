package com.example.seleniumuitests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TestSelenium {
    @Test
    public void main(){
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:3000/");

        WebElement element= driver.findElement(By.name("Calculate"));
        element.click();

    }
}
