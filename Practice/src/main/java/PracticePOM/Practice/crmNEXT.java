package PracticePOM.Practice;

import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import io.appium.java_client.android.AndroidDriver;




public class crmNEXT {
	
	AndroidDriver driver;
	URL url;
	
	@BeforeMethod
	@SuppressWarnings("deprecation")
	@Parameters({"deviceName","UDID","platformVersion","platformName","mylink"})
	
	public void config(String deviceName,String UDID,String platformVersion,String platformName ,String mylink ) throws MalformedURLException, InterruptedException
	{
		
		
		System.out.println("Printing parameters:"+deviceName);
		System.out.println("Printing parameters:"+UDID);
		System.out.println("Printing parameters:"+platformVersion);
		System.out.println("Printing parameters:"+platformName);
		System.out.println("Printing parameters:"+mylink);


		
		DesiredCapabilities cap=new DesiredCapabilities();
		cap.setCapability("deviceName", deviceName);
		cap.setCapability("udid", UDID);
		cap.setCapability("platformVersion", platformVersion);
		cap.setCapability("platformName", platformName);
		
		
		cap.setCapability("appPackage", "com.crmnextmobile.crmnextofflineplay");
		cap.setCapability("appActivity", "com.crmnextmobile.crmnextofflineplay.qr.QrScannerActivity");
		cap.setCapability("noReset", "true");
		
		//url=new URL(myurl);
		driver=new AndroidDriver(new URL(mylink),cap);
		System.out.println("CRMNXT started now you can play");
		
	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(6000);
		
	}
	@Test(priority=0)
	public void login() throws InterruptedException 
	{
		driver.findElement(By.xpath("//*[@text='1']")).click();
		driver.findElement(By.xpath("//*[@text='2']")).click();
		driver.findElement(By.xpath("//*[@text='3']")).click();
		driver.findElement(By.xpath("//*[@text='4']")).click();
		
		
		
		driver.findElement(By.xpath("//*[@resource-id='TxtName']")).sendKeys("sushant");
		driver.findElement(By.xpath("//*[@resource-id='TxtPassword']")).sendKeys("Abc@12345");
		
		
		Thread.sleep(300);
		

		driver.findElement(By.xpath("//*[@text='Login']")).click();
		
		
		
		}
	/*
	@SuppressWarnings("deprecation")
	@Test(priority=1)
	public void home() throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@resource-id='com.crmnextmobile.crmnextofflineplay:id/iv_drawer']")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.findElement(By.xpath("(//*[@resource-id='com.crmnextmobile.crmnextofflineplay:id/tv_title'])[1]")).click();

		
		
	}
	@SuppressWarnings("deprecation")
	@Test(priority=2)
	public void logout() throws InterruptedException
	{
		
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='‎‏‎‎‎‎‎‏‎‏‏‏‎‎‎‎‎‎‏‎‎‏‎‎‎‎‏‏‏‏‏‏‏‏‏‏‎‏‎‎‎‏‏‎‏‎‎‎‏‏‎‎‎‏‏‏‏‎‏‎‎‎‎‏‏‎‏‏‎‏‎‎‏‎‎‏‎‎‎‎‎‎‏‎‏‎‎‎‎‏‏‏‎‎‎‎‎Navigate up‎‏‎‎‏‎']")).click();
		Thread.sleep(9000);
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);

		driver.findElement(By.id("com.crmnextmobile.crmnextofflineplay:id/img_logout")).click();
		
		Thread.sleep(9000);

	}*/
		
		
	@AfterMethod
	public void shutdown()
	{
	driver.quit();
	}
}
