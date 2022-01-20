package temp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumBasics {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://portfolio.rediff.com/portfolio-login");
		By locator = By.xpath("//input[@id='useremail']");
		driver.findElement(locator).sendKeys("ashish");

	}

}
