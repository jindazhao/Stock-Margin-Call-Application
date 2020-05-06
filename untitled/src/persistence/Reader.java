package persistence;

import main.Account;
import main.Stock;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reader {
    public static final String DELIMTER = ";";

    public static Account readAccount(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    private static Account parseContent(List<String> fileContent) {
        Account account = new Account();
        account.setAccountName(fileContent.get(0));
        account.setAccountCash(Double.parseDouble(fileContent.get(1)));
        account.setAccountEquity(Double.parseDouble(fileContent.get(2)));
        fileContent.remove(0);
        fileContent.remove(0);
        fileContent.remove(0);
        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            account.addStock(parseAccountStocks(lineComponents));
        }




        return account;
    }
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMTER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    private static Stock parseAccountStocks(List<String> components) {
        Stock stock = new Stock(components.get(0), Double.parseDouble(components.get(1)),
                Double.parseDouble(components.get(3)), Double.parseDouble(components.get(4)),
                Double.parseDouble(components.get(5)),
                Double.parseDouble(components.get(6)));
        return stock;

    }


}
