package rediff.steps;

import context.TestContext;
import io.cucumber.java.en.And;
import pages.LandingPage;

// select the portfolio
// create a portfolio
// delete portfolio
public class Portfolio {
	
	
	public TestContext context ;
	public LandingPage landingPage;
	
	public Portfolio(TestContext context) {
		System.out.println("Portfolio.java "+context.name);
		this.context=context;
		landingPage = context.getPageObjectManager().getLandingPage();
	}
	
	@And("I select portfolio {string}")
	public void selectPortFolio(String portfolioName) {
		landingPage.selectPortFolio(portfolioName);
	}

}
