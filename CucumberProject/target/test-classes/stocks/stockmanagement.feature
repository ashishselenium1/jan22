@StockManagement
Feature: Managing Stocks
  This manages buying and selling of stocks

Background:
    Given I login inside application
    And I select portfolio 'My Portfolio1'


  @BuyNewStock
  Scenario: Buying New Stock
    And I click on add stock button
    And I fill stock details
    |Reliance Industries Ltd|24-04-2020   |10       |400  |
    
    
  
  @SellStock  
      Scenario: Selling Stock
      And I select row with 'Tata Motors Ltd'
      And I sell shares
      |24-05-2020|20|1000|
    