package rediff.steps;

import context.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import pages.LandingPage;
import pages.ManageStocksPage;

// add stock, del stock
public class Stocks {
	
	public TestContext context ;
	public ManageStocksPage manageStocksPage;
	public LandingPage landingPage;
	
	
	public Stocks(TestContext context) {
		this.context=context;
		manageStocksPage = context.getPageObjectManager().getManageStocksPage();
		landingPage =  context.getPageObjectManager().getLandingPage();
	}
	
	 @And("I click on add stock button")
	 public void addStock() {
		 landingPage.addStock();
	 }
	 
	 
	 @And("I fill stock details")
	 public void enterStockDetails(DataTable stockData) {
		 System.out.println("Datatable "+ stockData);
		 System.out.println("stockData "+stockData.asList());
		 manageStocksPage.enterStockDetails(stockData.asList());
	 }
	 
	 
	 @And("I select row with {string}")
	 public void selectStock(String stockName) {
		 manageStocksPage.selectStockFromTable(stockName);
	 }
	 
	 @And("I sell shares")
	 public void sellStock(DataTable stockData) {
		 manageStocksPage.sellStock(stockData.asList());
	 }
	 
	 
	 
	 
	 
	 

}
