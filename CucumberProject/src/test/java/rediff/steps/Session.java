package rediff.steps;

import org.openqa.selenium.WebDriver;

import context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import pages.LoginPage;

// login, logout
public class Session {
	
    public TestContext context ;
    public LoginPage loginPage;
	
	public Session(TestContext context) {
		context.name="ABC";
		this.context=context;
		loginPage = context.getPageObjectManager().getLoginPage();
	}

	@Given("I login inside application")
	public void login() {
		//log
		loginPage.doDefaultLogin();
	}
	
	@Before
	public void init(Scenario scenario) {
		System.out.println("----------------Start---------------------");
		context.init(scenario.getName());
	}
	
	@After
	public void quit() {
		System.out.println("----------------End---------------------");
		context.quit();
	}
}
