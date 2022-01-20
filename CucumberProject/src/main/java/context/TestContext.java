package context;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import managers.PageObjectManager;
import reports.ExtentManager;

public class TestContext {
	public String name;
	PageObjectManager pageObjectManager;
	ExtentReports rep;
	ExtentTest test ;
	
	public TestContext() {
		System.out.println("**********TestContext INIT****************");
		pageObjectManager = new PageObjectManager();
	}
	
	public PageObjectManager getPageObjectManager() {
		return pageObjectManager;
	}
	
	
	public void init(String scenarioName) {
		rep = ExtentManager.getReport(System.getProperty("user.dir")+"\\reports\\");
		test = rep.createTest(scenarioName);
		//pass the test object to webdrivermanager
		pageObjectManager.getWebDriverManager().initRep(test);
	}
	
	public void quit() {
		rep.flush();
		pageObjectManager.getWebDriverManager().quit();
	}
	
	

}
