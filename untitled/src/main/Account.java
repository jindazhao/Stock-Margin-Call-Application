package main;

import persistence.Saveable;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Account implements Saveable {
    private String accountName;
    private Double accountCash;
    private Double accountEquity;
    private List<Stock> accountStocks;
    private List<String> accountStocksName;


    public Account() {
        accountName = null;
        accountCash = 0.0;
        accountEquity = 0.0;
        accountStocks = new ArrayList<>();
        accountStocksName = new ArrayList<>();


    }

    // EFFECTS: Getters and setters
    public void setAccountName(String name) {
        accountName = name;
    }

    public void setAccountCash(Double cash) {
        accountCash = cash;
    }

    public void setAccountEquity(Double equity) {
        accountEquity = equity;
    }

    public String getAccountName() {
        return accountName;
    }

    public Double getAccountCash() {
        return accountCash;
    }

    public Double getAccountEquity() {
        return accountEquity;
    }

    public List<Stock> getEquity() {
        return accountStocks;
    }


    // EFFECTS: if stock is already in account, add it to the current stock, if not then add it to the accountStocks
    public void addStock(Stock stock) {
        if (accountCash + stock.getLoan() >= stock.getBookValue() * stock.getStockMarginRequirement()) {
            if (accountStocksName.contains(stock.getStockSymbol())) {
                Integer index = accountStocksName.indexOf(stock.getStockSymbol());
                Stock sameStock = accountStocks.get(index);
                sameStock.setBookValue(sameStock.getMarketValue() + stock.getMarketValue() /
                        (sameStock.getStockQuantity() + stock.getStockQuantity()));
                sameStock.setStockQuantity(sameStock.getStockQuantity() + stock.getStockQuantity());
                accountCash = accountCash - stock.getPaid();
                accountEquity = accountEquity + stock.getBookValue();
            } else {
                accountStocksName.add(stock.getStockSymbol());
                accountStocks.add(stock);
                accountCash = accountCash - stock.getPaid();
                accountEquity = accountEquity + stock.getBookValue();
            }
        } else {

            System.out.println("Not enough buying power for that!");
        }
    }

    // EFFECTS: remove the stock if found
    public void removeStock(String name) {
        if (accountStocksName.contains(name)) {
            int index = accountStocksName.indexOf(name);
            Stock s = accountStocks.get(index);
            accountCash = accountCash + s.getMarketValue() - s.getLoan();
            accountEquity = accountEquity - s.getMarketValue();
            accountStocks.remove(index);
            accountStocksName.remove(index);

        } else {
            System.out.println("Stock not owned!");
        }
    }

    // EFFECTS: calculates the margin of the stock
    public Double calculateMarginPercentage(Stock stock) {

        return (stock.getMarketValue() - stock.getLoan()) / stock.getMarketValue() * 100.0;
    }

    // EFFECTS: will return true if the margin is less than maintenance requirement
    public Boolean receiveCall(Stock stock) {
        if (calculateMarginPercentage(stock) >= stock.getStockMaintenanceRequirement()) {
            return false;
        }
        return true;
    }


    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(accountName);
        printWriter.print("\n");
        printWriter.print(accountCash);
        printWriter.print("\n");
        printWriter.print(accountEquity);
        printWriter.print("\n");

        for (Stock s : accountStocks) {
            printWriter.print(s.getStockSymbol());
            printWriter.print(";");
            printWriter.print(s.getBook());
            printWriter.print(";");
            printWriter.print(s.getMarket());
            printWriter.print(";");
            printWriter.print(s.getLoan());
            printWriter.print(";");
            printWriter.print(s.getStockMarginRequirement());
            printWriter.print(";");
            printWriter.print(s.getStockMaintenanceRequirement());
            printWriter.print(";");
            printWriter.print(s.getStockQuantity());
            printWriter.print("\n");
        }
    }
}


