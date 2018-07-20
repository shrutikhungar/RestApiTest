package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class Base {
	WebDriver driver;
	public Properties prop;
	FileInputStream fis;
	String filePath;
	public Base() {
		filePath=System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\config\\config.properties";
		prop=new Properties();
		try {
			fis=new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("******Code in Catch 1******");
		}
		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("******Code in Catch 2******");
		}
	}

}