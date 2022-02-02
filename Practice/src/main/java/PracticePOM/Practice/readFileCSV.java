package PracticePOM.Practice;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInput;
import java.net.URL;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;



public class readFileCSV {

	String CSV_PATH = "C:\\Users\\MY\\Downloads\\TestData.csv";
	// String XLS_PATH= "D:\\Azar\\PracticeData.xlsx";
	// AndroidDriver driver;
	CSVReader csvReader;
	String[] csvCell;
	String xlsCell;
	 AppiumDriver driver;
	
	//RemoteWebDriver driver;

	@BeforeTest
	public void setup() throws Exception {

		/*
		 * 
		 * //System.setProperty("webdriver.chrome.driver",
		 * "/Users/d33p4k/driver/chromedriver");
		 * WebDriverManager.chromedriver().setup(); driver = new ChromeDriver();
		 */
		DesiredCapabilities cap=new DesiredCapabilities();
		cap.setCapability("deviceName", "PixelEmulator");
		//cap.setCapability("udid", "emulator-5554");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "10");
		cap.setCapability("browserName", "Chrome");
		//cap.setCapability("uidid", "");

		cap.setCapability("chromedriverExecutable","C:\\Users\\MY\\Downloads\\chromedriver74\\chromedriver.exe");

		
		URL url =new URL("http://127.0.0.1:4723/wd/hub");
		driver=new AppiumDriver(url,cap);
		System.out.println("Appliction started.....");
		
	
		driver.get("https://www.amazon.com");
		driver.manage().timeouts().getImplicitWaitTimeout();
        Thread.sleep(5000); 
        
        dataRead_CSV(driver);
        dataRead_XLS(driver);

	}
			
						
							
					
		
		
		
	//@Test(priority=0)
	public void dataRead_CSV(AppiumDriver dr) throws IOException, CsvValidationException, InterruptedException {
		
			this.driver=dr;
		csvReader = new CSVReader(new FileReader(CSV_PATH));

		dr.findElement(By.id("nav-logobar-greeting")).click();
		
		// driver.findElement(By.id("createAccountSubmit")).click();

		csvCell = csvReader.readNext();
		// String CustomerName = csvCell[0];
		String CustomerEmail = csvCell[0];
		// String CustomerPassword = csvCell[2];
		// String CustomerConfirmPassword = csvCell[3];
		//driver.findElement(By.id("ap_email_login")).clear();
		dr.findElement(By.id("ap_email_login")).sendKeys(CustomerEmail);
		dr.findElement(By.xpath("//span[@id='continue']")).click();

		/*
		 * driver.findElement(By.id("ap_email")).clear();
		 * driver.findElement(By.id("ap_email")).sendKeys(CustomerEmail);
		 * driver.findElement(By.id("ap_password")).clear();
		 * driver.findElement(By.id("ap_password")).sendKeys(CustomerPassword);
		 * driver.findElement(By.id("ap_password_check")).clear();
		 * driver.findElement(By.id("ap_password_check")).sendKeys(
		 * CustomerConfirmPassword);
		 */

	}
	
	//@Test(priority=1)
	public void dataRead_XLS(AppiumDriver dr) throws IOException, InterruptedException {
		this.driver=dr;
		FileInputStream fis = new FileInputStream("D:\\Azar\\PracticeData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int sheets = workbook.getNumberOfSheets();

		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("Data")) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rows = sheet.iterator();
				Row row = rows.next();
				Iterator<Cell> cells = row.iterator();
				Cell cell = cells.next();

				xlsCell = cell.getStringCellValue();
				String CustomerPassword = xlsCell;

				System.out.println(CustomerPassword);
				// driver.findElement(By.id("ap_password")).clear();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//*[@type='password']")).sendKeys(CustomerPassword);
// 	    		driver.findElement(By.name("password")).sendKeys(CustomerPassword);
				// driver.findElement(By.id("ap_password")).sendKeys();
				driver.findElement(By.id("signInSubmit")).click();
				System.out.println(driver.findElement(By.xpath("//div[@id='auth-warning-message-box']")));

			}
		}

	}

	@AfterTest
	public void exit() {
		driver.close();
		driver.quit();
	}
}
