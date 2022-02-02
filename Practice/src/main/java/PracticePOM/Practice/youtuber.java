package PracticePOM.Practice;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;

public class youtuber {
	
	AppiumDriver driver;
	
	

	//@BeforeTest
	public void load() throws MalformedURLException, InterruptedException
	{
		
		DesiredCapabilities cap=new DesiredCapabilities();
		cap.setCapability("deviceName", "Pixel_XL_API_30");
		cap.setCapability("platformVersion", "11");
		cap.setCapability("platformName", "android");
		cap.setCapability("atomationName", "UiAutomator2");
		cap.setCapability("appPackage", "com.google.android.youtube");
		cap.setCapability("appActivity", "com.google.android.youtube.app.honeycomb.Shell$HomeActivity");
//		cap.setCapability("newCommandTimeout", "50000");
		
		
		URL url=new URL("http://127.0.0.1:4723/wd/hub");
		
		driver=new AppiumDriver(url,cap);
		
		Search();
		
		
	}
	
	//@Test
	public void Search() throws InterruptedException
	{
		System.out.println("Youtube has been started");
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='Search']")).click();
		driver.findElement(By.id("search_edit_text")).sendKeys("Selenium");
		driver.findElement(By.className("android.widget.TextView"));
		
		
		
	}
	
	
}
