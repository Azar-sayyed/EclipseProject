package PracticePOM.Practice;

import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

@SuppressWarnings("deprecation")
public class newpractice {
	
	AndroidDriver driver;
	
	@BeforeTest
	public void config() throws MalformedURLException, InterruptedException
	{
		
		DesiredCapabilities cap=new DesiredCapabilities();
		cap.setCapability("deviceName", "Pixel_XL_API_30");
		cap.setCapability("platformVersion", "11");
		cap.setCapability("platformName", "android");
		cap.setCapability("atomationName", "UiAutomator2");
		
		cap.setCapability("appPackage", "com.android.settings");
		cap.setCapability("appActivity", "com.android.settings.Settings");
		
		URL url=new URL("http://127.0.0.1:4723/wd/hub");
		
		driver=new AndroidDriver(url,cap);
		
		System.out.println("settings has been started");
		
	}
	@Test
	public void settings() throws InterruptedException
	{
		
		driver.findElement(By.id("com.android.settings:id/search_action_bar")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("android:id/search_src_text")).sendKeys("dis");
		Thread.sleep(5000);
		//driver.findElement(By.id("android:id/icon")).click();
		List <WebElement> options=driver.findElements(By.id("com.android.settings.intelligence:id/list_results"));
		//System.out.print("List are:"+ options);
		driver.hideKeyboard();
		
		for(WebElement option:options)
		{
			String  text= option.getText();
		    System.out.println("list of text is:"+ text);
			
		    
		    Dimension dimension = driver.manage().window().getSize();
			int scrollStart = (int) (dimension.getHeight() * 0.5);
			int scrollEnd = (int) (dimension.getHeight() * 0.5);

			new TouchAction((PerformsTouchActions)driver)
				.press(PointOption.point(0, scrollStart))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
				.moveTo(PointOption.point(0, scrollEnd))
				.release().perform();

			
		    //TouchActions action = new TouchActions(driver);
			//action.scroll(10, 100);
			//action.perform();
			
			Thread.sleep(3000);
			
			
			if(option.getText().equalsIgnoreCase("Lock"));
				
				option.click();
				Thread.sleep(3000);
				break;
				
			
		}
		
		
		
	}
	
	public void shutdown()
	{
	driver.quit();
	}
}
