package main;


import yahoofinance.YahooFinance;

import java.io.IOException;

public class Stock {
    private String stockSymbol;
    private Double bookValue;
    private Double marketValue;
    private Double loan;
    private Double stockMarginRequirement;
    private Double stockMaintenanceRequirement;
    private Double stockQuantity;

    public Stock(String name, Double price, Double margin, Double bankLoan, Double maintenanceRequirement, Double quantity) {
        stockSymbol = name;
        bookValue = price;
        loan = bankLoan;
        setMarketValue();
        stockMarginRequirement = margin;
        stockMaintenanceRequirement = maintenanceRequirement;
        stockQuantity = quantity;

    }

    // EFFECTS: grabs the real time data from the yahoo finacne api of a current stock and sets sets the market price
    public void setMarketValue() {
        try {
            yahoofinance.Stock stock = YahooFinance.get(stockSymbol);
            marketValue = stock.getQuote(true).getPrice().doubleValue();
        } catch (IOException e) {
            System.out.println("Quote not found!");
        }

    }

    // EFFECTS: settrs and getters
    public void setStockQuantity(Double quantity) {
        stockQuantity = quantity;
    }

    public void setBookValue(Double value) {
        bookValue = value;

    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public Double getBookValue() {
        return bookValue * stockQuantity;
    }

    public Double getLoan() {
        return loan;
    }

    public Double getStockMaintenanceRequirement() {
        return stockMaintenanceRequirement;
    }

    public Double getStockMarginRequirement() {
        return stockMarginRequirement;
    }
    public Double getPaid() {
        return (bookValue * stockQuantity) - loan;
    }

    public Double getMarketValue() {
        return marketValue * stockQuantity;
    }

    public Double getStockQuantity() {
        return stockQuantity;
    }

    public Double getBook() {
        return bookValue;
    }

    public Double getMarket() {
        return marketValue;
    }



}
