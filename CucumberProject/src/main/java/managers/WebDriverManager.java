package managers;
// manage failures
// make more scenarios
// grid
// jenkins + git

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import reports.ExtentManager;


public class WebDriverManager {

	public WebDriver driver;
	public Properties prop;
	ExtentTest test ;

	public WebDriverManager() {
		System.out.println("***************WebDriverManager INIT*****************");
        try {
        	prop = new Properties();
    		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//project.properties");
			prop.load(fs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	//open browser
	public void openBrowser(String bName) {
		log("Opening  browser "+bName);
		if(getProperty("grid_run").equals("Y")) {
			
			// run on grid
        	DesiredCapabilities cap=null;
        	
			if(bName.equals("Mozilla")){
				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				cap.setJavascriptEnabled(true);
				cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				
			}else if(bName.equals("Chrome")){
				 cap = DesiredCapabilities.chrome();
				 cap.setBrowserName("chrome");
				 cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			}
			
			try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
			
			
		}else {
		if(bName.equals("Chrome"))
			driver= new ChromeDriver();
		else if(bName.equals("Mozilla"))
			driver= new FirefoxDriver();
		}
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	// navigation
	public void navigate(String urlKey) {
		log("Opening Login Page");
		driver.get(getProperty(urlKey));
	}
	
	// click on element
	public void click(String locatorKey) {
		log("Clicking on "+locatorKey);
		getElement(locatorKey).click();
	}
	
	// typing in textfield
	public void type(String locatorKey,String value) {
		log("Typing in "+locatorKey);
		getElement(locatorKey).sendKeys(value);
	}
	
	
	
	// select a value from dropdown
	public void select(String locatorKey,String text) { 
		Select s= new Select(getElement(locatorKey));
		s.selectByVisibleText(text);
	}
	
	public void enter(String locatorKey) {
		getElement(locatorKey).sendKeys(Keys.ENTER);	
	}
	
	// check presence of element + visibility
	public boolean isElementPresent(String locatorKey) {
		try {
			//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
			WebDriverWait wait = new WebDriverWait(driver,10);
			// presence
			wait.until(ExpectedConditions.presenceOfElementLocated(getLocator(locatorKey)));
			// visibility
			wait.until(ExpectedConditions.visibilityOfElementLocated(getLocator(locatorKey)));
		}catch(Exception e) {
			return false;
		}
		return true;	
	}
	
	// check title of webpage
	public boolean validateTitle(String expected) {
		return false;
	}

	// read a value from prop file
	public String getProperty(String key) {
		return prop.getProperty(key);
	}
	
    public By getLocator(String locatorKey) {
    	By locator = null;
		// decide locator
		if(locatorKey.endsWith("_id"))
			locator = By.id(getProperty(locatorKey));
		else if(locatorKey.endsWith("_xpath"))
			locator = By.xpath(getProperty(locatorKey));
		else if(locatorKey.endsWith("_name"))
			locator = By.name(getProperty(locatorKey));	
		else if(locatorKey.endsWith("_css"))
			locator = By.cssSelector(getProperty(locatorKey));
	
		return locator;
    }
	// central function to extract objects
	public WebElement getElement(String locatorKey) {
		// presence+visibility
		if(isElementPresent(locatorKey)) {
		// extract
		WebElement e = driver.findElement(getLocator(locatorKey));
		return e;
		}else {
			// report the failure
			// stop
			return null;
		}
	}
	
	public int  getRowWithCellData(String data) {
		return -1;
	}
	
	public void log(String message) {
		System.out.println(message);
		test.log(Status.INFO, message);
	}
	// critical failure - stop the test
	// non critical failure - continue
	public void logFailure(String failureMessage) {
		//log the failure
		System.out.println("Failure "+failureMessage);
		test.log(Status.FAIL, failureMessage);
		// take screenshot of page
		takeScreenShot();
	}
	
	public void takeScreenShot(){
		// fileName of the screenshot
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		// take screenshot
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			// get the dynamic folder name
			FileUtils.copyFile(srcFile, new File(ExtentManager.screenshotFolderPath+"//"+screenshotFile));
			//put screenshot file in reports
			test.log(Status.INFO,"Screenshot-> "+ test.addScreenCaptureFromPath(ExtentManager.screenshotFolderPath+"//"+screenshotFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void initRep(ExtentTest test) {
		this.test=test;
	}

	public void quit() {
		if(driver!=null)
			driver.quit();
		
	}
	
	
	

}
