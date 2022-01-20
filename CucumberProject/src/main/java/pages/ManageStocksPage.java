package pages;

import java.util.List;

import io.cucumber.datatable.DataTable;
import managers.WebDriverManager;

public class ManageStocksPage {

	WebDriverManager app;

	public ManageStocksPage(WebDriverManager app) {
		this.app=app;
	}
	
	public void enterStockDetails(List<String> stockData) {
		app.log("ManageStocksPage - Add stock Details");
		app.type("stockname_name", stockData.get(0));
		// you
		
		
	}

	public void selectStockFromTable(String stockName) {
		// find the row num on which stock is lying
		int rNum=app.getRowWithCellData(stockName);
		// select the radio button
	}

	public void sellStock(List<String> stockData) {
		// entering the details of stock and sell
		
	}

}
