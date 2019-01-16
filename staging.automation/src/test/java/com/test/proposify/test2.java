package com.test.proposify;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class test2 {


    public static void main(String[] args) {
        // declaration and instantiation of objects/variables
    	System.setProperty("webdriver.gecko.driver","//Users//Milan//Downloads//geckodriver");
		WebDriver driver;
		driver = new FirefoxDriver(); 
    	
        String baseUrl = "http://google.com/";
        driver.get(baseUrl);
        //close Fire fox
        driver.close();
       
    }

}