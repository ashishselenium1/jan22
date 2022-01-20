package pages;

import managers.WebDriverManager;

public class LoginPage {
	
	WebDriverManager app;
	
	public LoginPage(WebDriverManager app) {
		this.app=app;
	}
	
	public void doDefaultLogin() {
		//log
		app.log("Default Login");
		// open browser
		app.openBrowser("Chrome");
		// navigate to website
		app.navigate("url");
		// login
		app.type("login_username_xpath", app.getProperty("defaultUsername"));
		app.type("login_password_id", app.getProperty("defaultPassword"));
		app.enter("login_password_id");
		// validate login
		
		
	}

}
