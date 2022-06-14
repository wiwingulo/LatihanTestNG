package com.nexsoft.testng;

import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class CRUDUserBiodata {

	WebDriver driver;
	public JavascriptExecutor jse;

	private void sleep(int n) {
		try {
			Thread.sleep(n);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@BeforeClass
	public void init() {
		System.setProperty("url", "http://localhost/cicool/cicool/");
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		driver = new ChromeDriver();
		jse = (JavascriptExecutor) driver;
		driver.get(System.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test(priority = 0)
	public void login() {
		driver.findElement(By.cssSelector(".fa.fa-sign-in")).click();
		driver.findElement(By.cssSelector("input[placeholder='Email']")).clear();
		driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("wiwingulo@gmail.com");
		driver.findElement(By.cssSelector("input[placeholder='Password']")).clear();
		driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("Kmkm12345");
		driver.findElement(By.cssSelector("button[type='submit']")).click();

		String username = driver.findElement(By.cssSelector("span[class='hidden-xs']")).getText();
		Assert.assertEquals(username, "Wiwin Gulo");
	}

	@Test(priority = 1, dataProvider = "getNexSoftData", dataProviderClass = com.nexsoft.testng.dataprovider.DataProviderNexsoft.class)
	public void createData(String param1, String param2, String param3, String param4) {

		driver.get("http://localhost/cicool/cicool/administrator/userbiodata");

		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		System.out.println("create data --> " + sdf2.format(new Date().getTime()));

//
//		try {
//			driver.findElement(By.id("btn_add_new")).click();
//		 }catch (org.openqa.selenium.StaleElementReferenceException ex ) {
//			 driver.findElement(By.id("btn_add_new")).click();
//		}
		driver.findElement(By.id("btn_add_new")).click();
		driver.findElement(By.id("first_name")).click();
		driver.findElement(By.id("first_name")).sendKeys(param1);
		driver.findElement(By.id("last_name")).click();
		driver.findElement(By.id("last_name")).sendKeys(param2);
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).sendKeys(param3);
		driver.findElement(By.id("gender")).click();
		driver.findElement(By.id("gender")).sendKeys(param4);

		jse.executeScript("window.scrollBy(100, 500)", "");
		WebElement chooseFile = driver.findElement(By.cssSelector("input[title='file input"));

		int randomVal = 1 + (int) (Math.random() * ((3 - 1) + 1));
		if (randomVal == 1)
			chooseFile.sendKeys("C:\\Users\\Nexsoft\\eclipse-workspace\\LatihanTestNG\\src\\test\\resources\\ayam.PNG");
		else if (randomVal == 2)
			chooseFile.sendKeys("C:\\Users\\Nexsoft\\eclipse-workspace\\LatihanTestNG\\src\\test\\resources\\ayam2.PNG");
		else
			chooseFile.sendKeys("C:\\Users\\Nexsoft\\eclipse-workspace\\LatihanTestNG\\src\\test\\resources\\ayam3.PNG");

		sleep(1000);

		driver.findElement(By.xpath("//a[@id='btn_save']")).click();

		sleep(1000);

	}

	@Test(priority = 1)
	public void getUsername() {
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		System.out.println(driver.findElement(By.linkText("Wiwin Gulo")).getText() + sdf2.format(new Date().getTime()));
	}

	@Test(priority = 1)
	public void getUsername2() {
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		System.out.println(driver.findElement(By.linkText("Wiwin Gulo")).getText() + sdf2.format(new Date().getTime()));
	}

}
