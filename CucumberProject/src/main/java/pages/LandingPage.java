package pages;

import managers.WebDriverManager;

public class LandingPage {
	WebDriverManager app;

	public LandingPage(WebDriverManager app) {
		this.app=app;
	}
	
	public void selectPortFolio(String portfolioName) {
		app.log("Selecting portfolio "+ portfolioName);
		app.select("portfolio_selection_css", portfolioName);
	}

	public void addStock() {
		app.log("Click on add stock button");
		app.click("add_stock_button_xpath");
	}
}
