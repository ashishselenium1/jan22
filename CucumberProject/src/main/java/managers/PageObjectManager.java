package managers;

import com.aventstack.extentreports.ExtentTest;

import pages.LandingPage;
import pages.LoginPage;
import pages.ManageStocksPage;

public class PageObjectManager {
	
	LoginPage loginPage;
	LandingPage landingPage;
	ManageStocksPage manageStocksPage;
	
	WebDriverManager app;
	
	public PageObjectManager() {
		System.out.println("***************PageObjectManager INIT*****************");
		app = new WebDriverManager();
	}
	
	
	public LoginPage getLoginPage() {
		if(loginPage==null) 
			loginPage = new LoginPage(app);
		return loginPage;
	}
	
	public LandingPage getLandingPage() {
		if(landingPage==null) 
			landingPage = new LandingPage(app);
		return landingPage;
	}
	
	public ManageStocksPage getManageStocksPage() {
		if(manageStocksPage==null) 
			manageStocksPage = new ManageStocksPage(app);
		return manageStocksPage;
	}

	public WebDriverManager getWebDriverManager() {
		return app;
	}
}
