package UI;

import main.Account;
import main.Stock;
import persistence.Reader;
import persistence.Writer;
import yahoofinance.YahooFinance;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class GUI extends JFrame implements ActionListener {
    private static final String ACCOUNT_FILE = "C:\\Users\\Dell XPS 13\\IdeaProjects\\" +
            "untitled2\\untitled\\data\\account.txt";
    private Account account;
    private JPanel menuPanel;
    private JPanel setNamePanel;
    private JPanel addStockPanel;
    private JPanel accountDetailsPanel;
    private JPanel equityPanel;
    private JPanel functionPanel;

    private Reader reader;
    private Writer writer;

    private JTextField text1;
    private JTextField text2;
    private JTextField text3;
    private JTextField text4;
    private JTextField text5;
    private JTextField text6;
    private JTextField text7;
    private JTextField accountText1;
    private JTextField accountText2;
    private JTextField functionalityText1;
    private JTextField functionalityText2;
    private JTextField functionalityText3;

    private JLabel functionalityLabel1;

    private JTable table1;
    private JTable table;

    // EFFECTS: constructs a new gui
    public GUI() {
        super("Margin Calls");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(550, 750));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        account = new Account();
        menuPanel = new Panel().getPanel();
        add(menuPanel);

        JButton btn1 = new JButton("Set Name and Cash Balance in Account");
        JButton btn2 = new JButton("    Add/Remove Stock to your account    ");
        JButton btn3 = new JButton("                 View Account details                 ");
        JButton btn4 = new JButton("                    View Equity Status                   ");
        JButton btn7 = new JButton("                        Functionalities                       ");
        JButton btn5 = new JButton("                                Save                                 ");
        JButton btn6 = new JButton("                                Load                                 ");

        setButtonsCommands(btn1, btn2, btn3, btn4, btn5, btn6);
        btn7.setActionCommand("Functionalities");
        btn7.addActionListener(this);

        JLabel jLabel = new JLabel("Stonks");
        JLabel label2 = new JLabel();
        doJLabelSettings(jLabel, label2);

        JLabel label = new JLabel(new ImageIcon("C:\\Users\\Dell XPS 13\\IdeaProjects\\untitled2\\" +
                "untitled\\data\\stocks-iamchamp-adobe.jpg"));
        //label.setMinimumSize(new Dimension(1200, 5));

        menuPanel.add(label);
        addButtonstoMenu(btn1, btn2, btn3);
        addButtonstoMenu(btn4, btn7, btn5);
        menuPanel.add(btn6);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        makePanels();
        menuPanel.setVisible(true);


    }

    // EFFECTS: sets the settings of the JLabels
    public void doJLabelSettings(JLabel jLabel, JLabel label2) {
        jLabel.setFont(new Font("SansSerif", Font.BOLD, 60));
        label2.setIcon(new ImageIcon("Filename here"));
        label2.setMinimumSize(new Dimension(20, 20));
        menuPanel.add(jLabel);
        menuPanel.add(label2);
    }

    // EFFECTS: sets the commands of the buttons on the menu
    public void setButtonsCommands(JButton btn1, JButton btn2, JButton btn3, JButton btn4, JButton btn5, JButton btn6) {
        btn1.setActionCommand("Set Name and Cash Balance in Account");
        btn1.addActionListener(this);
        btn2.setActionCommand("Add/Remove Stock to your account");
        btn2.addActionListener(this);
        btn3.setActionCommand("View Account details");
        btn3.addActionListener(this);
        btn4.setActionCommand("View Equity Status");
        btn4.addActionListener(this);
        btn5.setActionCommand("Save");
        btn5.addActionListener(this);
        btn6.setActionCommand("Load");
        btn6.addActionListener(this);
    }

    // EFFECTS: constructs the JPanels
    public void makePanels() {
        makeSetPanel();
        makeAddStockPanel();
        makeViewAccountPanel();
        makeEquityPanel();
        makeFunctionPanel();

    }

    // EFFECTS: constructs the makeSetPanel
    public void makeSetPanel() {
        setNamePanel = new Panel().getPanel();

        JLabel label = new JLabel("Enter in your First and Last Name: ");
        label.setFont(new Font("Sanserif", Font.PLAIN, 20));
        accountText1 = new JTextField(5);
        accountText1.setMaximumSize(new Dimension(1000, 40));
        JLabel label2 = new JLabel("Enter in your cash amount: ");
        label2.setFont(new Font("Sanserif", Font.PLAIN, 20));
        accountText2 = new JTextField(5);
        accountText2.setMaximumSize(new Dimension(1000, 40));

        JButton setButton = new JButton("    Set Name    ");
        setButton.setActionCommand("Set Name");
        setButton.addActionListener(this);

        JButton menuButton = new JButton("Back to Menu");
        menuButton.setActionCommand("Back to Menu");
        menuButton.addActionListener(this);
        setNamePanel.add(label);
        setNamePanel.add(accountText1);
        setNamePanel.add(label2);
        setNamePanel.add(accountText2);
        setNamePanel.add(setButton);
        setNamePanel.add(menuButton);
    }

    // EFFECTS: constructs the makeAddStockPanel
    public void makeAddStockPanel() {
        addStockPanel = new Panel().getPanel();

        JLabel label1 = new JLabel("Enter in the stock symbol in caps");
        label1.setFont(new Font("Sanserif", Font.PLAIN, 17));
        text1 = new JTextField(5);
        JLabel label2 = new JLabel("Enter in the book value");
        label2.setFont(new Font("Sanserif", Font.PLAIN, 17));
        text2 = new JTextField(5);
        JLabel label3 = new JLabel("Enter in the initial margin requirement");
        label3.setFont(new Font("Sanserif", Font.PLAIN, 17));

        text3 = new JTextField(5);
        JLabel label4= new JLabel("Enter in the loan from the bank on this stock");
        label4.setFont(new Font("Sanserif", Font.PLAIN, 17));

        text4 = new JTextField(5);
        JLabel label5 = new JLabel("Enter in the stock maintenance requirement");
        label5.setFont(new Font("Sanserif", Font.PLAIN, 17));
        text5 = new JTextField(5);
        JLabel label6 = new JLabel("Enter in the stock quantity");
        label6.setFont(new Font("Sanserif", Font.PLAIN, 17));
        text6 = new JTextField(5);


        JButton button = new JButton("Add Stock");
        button.setActionCommand("Add Stock");
        button.addActionListener(this);

        JLabel label7 = new JLabel("Or please enter the name of the stock you want removed from your account");
        text7 = new JTextField(5);
        label7.setFont(new Font("Sanserif", Font.PLAIN, 15));


        JButton button1 = new JButton("Remove Stock");
        button1.setActionCommand("Remove Stock");
        button1.addActionListener(this);


        JButton menuButton = new JButton(" Back to Menu ");
        menuButton.setActionCommand("Back to Menu");
        menuButton.addActionListener(this);

        text1.setMaximumSize(new Dimension(1000, 40));
        text2.setMaximumSize(new Dimension(1000, 40));
        text3.setMaximumSize(new Dimension(1000, 40));
        text4.setMaximumSize(new Dimension(1000, 40));
        text5.setMaximumSize(new Dimension(1000, 40));
        text6.setMaximumSize(new Dimension(1000, 40));
        text7.setMaximumSize(new Dimension(1000, 40));

        addStockPanel.add(label1);
        addStockPanel.add(text1);
        addStockPanel.add(label2);
        addStockPanel.add(text2);
        addStockPanel.add(label3);
        addStockPanel.add(text3);
        addStockPanel.add(label4);
        addStockPanel.add(text4);
        addStockPanel.add(label5);
        addStockPanel.add(text5);
        addStockPanel.add(label6);
        addStockPanel.add(text6);


        addStockPanel.add(button);
        addStockPanel.add(label7);
        addStockPanel.add(text7);
        addStockPanel.add(button1);
        addStockPanel.add(menuButton);
    }

    // EFFECTS: constructs the makeViewAccountPanel
    public void makeViewAccountPanel() {
        accountDetailsPanel = new Panel().getPanel();

        table1 = new JTable(15, 2);
        table1.setValueAt("Account Name:", 0, 0);
        table1.setValueAt("Account Cash Balance:", 1, 0);
        table1.setValueAt("Account Market Value:", 2, 0);
        table1.setValueAt("Account Book Cost:", 3, 0);
        table1.setValueAt("Account Loan Amount:", 4, 0);
        table1.setValueAt("Buying Power:", 5, 0);

        table1.setValueAt(account.getAccountName(), 0, 1);
        table1.setValueAt(account.getAccountCash(), 1, 1);
        Double market = 0.0;
        for (Stock s : account.getEquity()) {
            market = market + s.getMarketValue();
        }
        table1.setValueAt(market, 2, 1);
        table1.setValueAt(account.getAccountEquity(), 3, 1);
        Double loan = 0.0;
        for (Stock s1 : account.getEquity()) {
            loan = loan + s1.getLoan();
        }
        table1.setValueAt(loan, 4, 1);
        table1.setValueAt(account.getAccountCash() + loan, 5, 1);
        table1.setFont(new Font("Sansserif", Font.PLAIN, 20));
        table1.setRowHeight(30);


        JLabel label = new JLabel("Your Account Details:");
        label.setFont(new Font("Sanserif", Font.BOLD, 25));


        JButton menuButton = new JButton("Back to Menu");
        menuButton.setActionCommand("Back to Menu");
        menuButton.addActionListener(this);

        accountDetailsPanel.add(label);
        accountDetailsPanel.add(table1, BorderLayout.CENTER);

        accountDetailsPanel.add(menuButton);

    }

    // EFFECTS: constructs the makeEquityPanel
    public void makeEquityPanel() {

        equityPanel = new Panel().getPanel();
        table = new JTable(50, 7);
        table.setValueAt("Symbol", 0, 0);
        table.setValueAt("Quantity", 0, 1);
        table.setValueAt("Book Value", 0, 2);
        table.setValueAt("Market Val.", 0, 3);
        table.setValueAt("Maint. Req.", 0, 4);
        table.setValueAt("Margin", 0, 5);
        table.setValueAt("Call?", 0, 6);
        Integer i = 1;
        for (Stock s : account.getEquity()) {
            table.setValueAt(s.getStockSymbol(), i, 0 );
            table.setValueAt(s.getStockQuantity(), i, 1 );
            table.setValueAt(s.getBookValue(), i, 2 );
            table.setValueAt(s.getMarketValue(), i, 3 );
            table.setValueAt(s.getStockMaintenanceRequirement(), i, 4 );
            table.setValueAt(account.calculateMarginPercentage(s), i,5 );
            table.setValueAt(account.receiveCall(s), i, 6 );
            i = i + 1;

        }

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setActionCommand("Refresh");
        refreshButton.addActionListener(this);
        JButton menuButton = new JButton("Back to Menu");
        menuButton.setActionCommand("Back to Menu");
        menuButton.addActionListener(this);
        JScrollPane pane = new JScrollPane(table);
        equityPanel.add(refreshButton);
        equityPanel.add(pane);
        equityPanel.add(menuButton);
    }

    // EFFECTS: constructs the makeFunctionPanel
    public void makeFunctionPanel() {
        functionPanel = new Panel().getPanel();
        JLabel label = new JLabel("Please enter in the symbol of your stock: ");
        JLabel label1 = new JLabel("Please enter in the amount of cash you are putting in:");
        JLabel label2 = new JLabel("Please enter in the margin requirement: ");

        label.setFont(new Font("Sanserif", Font.PLAIN, 17));
        label1.setFont(new Font("Sanserif", Font.PLAIN, 17));
        label2.setFont(new Font("Sanserif", Font.PLAIN, 17));
        functionalityText1 = new JTextField(5);
        functionalityText2 = new JTextField(5);
        functionalityText3 = new JTextField(5);

        functionalityText1.setMaximumSize(new Dimension(1000, 40));
        functionalityText2.setMaximumSize(new Dimension(1000, 40));
        functionalityText3.setMaximumSize(new Dimension(1000, 40));

        functionalityLabel1 = new JLabel("You buying power is: ");
        functionalityLabel1.setFont(new Font("Sanserif", Font.BOLD, 20));
        JButton menuButton = new JButton("Back to Menu");
        menuButton.setActionCommand("Back to Menu");
        menuButton.addActionListener(this);
        JButton calculateButton = new JButton("    Calculate    ");
        calculateButton.setActionCommand("Calculate");
        calculateButton.addActionListener(this);
        functionPanel.add(label);
        functionPanel.add(functionalityText1);
        functionPanel.add(label1);
        functionPanel.add(functionalityText2);
        functionPanel.add(label2);
        functionPanel.add(functionalityText3);
        functionPanel.add(functionalityLabel1);
        functionPanel.add(calculateButton);
        functionPanel.add(menuButton);

    }

    // EFFECTS: adds the menu buttons to the menu panel
    public void addButtonstoMenu(JButton bt1, JButton btn2, JButton btn3) {
        menuPanel.add(bt1);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        menuPanel.add(btn2);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        menuPanel.add(btn3);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: action listener
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Set Name and Cash Balance in Account")) {
            viewSetNamePanel();
        } else if (e.getActionCommand().equals("Add/Remove Stock to your account")) {
            viewAddStockPanel();
        } else if (e.getActionCommand().equals("View Account details")) {
            viewAccountDetailsPanel();
        } else if (e.getActionCommand().equals("View Equity Status")) {
            viewEquityPanel();
        } else if (e.getActionCommand().equals("Save")) {
            saveAccount();
        } else if (e.getActionCommand().equals("Load")) {
            loadAccount();
        } else if (e.getActionCommand().equals("Back to Menu")) {
            backToMenu();
        } else if (e.getActionCommand().equals("Add Stock")) {
            addStock();
        } else if (e.getActionCommand().equals("Remove Stock")) {
            removeStock();
        } else if (e.getActionCommand().equals("Refresh")) {
            refresh();
        } else if (e.getActionCommand().equals("Set Name")) {
            setAccountSettings();
        } else if (e.getActionCommand().equals("Functionalities")) {
            goToFunctionPanel();
        } else if (e.getActionCommand().equals("Calculate")) {
            calculate();
        }
    }

    public void loadAccount() {
        try {
            reader = new Reader();
            account = reader.readAccount(new File(ACCOUNT_FILE));
            table1.setValueAt("Account Name:", 0, 0);
            table1.setValueAt("Account Cash Balance:", 1, 0);
            table1.setValueAt("Account Market Value:", 2, 0);
            table1.setValueAt("Account Book Cost:", 3, 0);
            table1.setValueAt("Account Loan Amount:", 4, 0);
            table1.setValueAt("Buying Power:", 5, 0);

            table1.setValueAt(account.getAccountName(), 0, 1);
            table1.setValueAt(account.getAccountCash(), 1, 1);
            Double market = 0.0;
            for (Stock s : account.getEquity()) {
                market = market + s.getMarketValue();
            }
            table1.setValueAt(market, 2, 1);
            table1.setValueAt(account.getAccountEquity(), 3, 1);
            Double loan = 0.0;
            for (Stock s1 : account.getEquity()) {
                loan = loan + s1.getLoan();
            }
            table1.setValueAt(loan, 4, 1);
            table1.setValueAt(account.getAccountCash() + loan, 5, 1);
            Integer i = 1;
            for (Stock s : account.getEquity()) {
                table.setValueAt(s.getStockSymbol(), i, 0 );
                table.setValueAt(s.getStockQuantity(), i, 1 );
                table.setValueAt(s.getBookValue(), i, 2 );
                table.setValueAt(s.getMarketValue(), i, 3 );
                table.setValueAt(s.getStockMaintenanceRequirement(), i, 4 );
                table.setValueAt(account.calculateMarginPercentage(s), i,5 );
                table.setValueAt(account.receiveCall(s), i, 6 );
                i = i + 1;
            }
        } catch (IOException e) {
            account = new Account();
        }

    }

    public void saveAccount() {
        try {
            writer = new Writer(new File(ACCOUNT_FILE));
            writer.write(account);
            writer.close();
            System.out.println("Account saved to file " + ACCOUNT_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save account to " + ACCOUNT_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: below are the functions of th various buttons in ActionListener
    public void viewSetNamePanel() {
        add(setNamePanel);
        menuPanel.setVisible(false);
        addStockPanel.setVisible(false);
        accountDetailsPanel.setVisible(false);
        equityPanel.setVisible(false);
        setNamePanel.setVisible(true);
    }

    public void viewAddStockPanel() {
        add(addStockPanel);
        menuPanel.setVisible(false);
        accountDetailsPanel.setVisible(false);
        equityPanel.setVisible(false);
        setNamePanel.setVisible(false);
        functionPanel.setVisible(false);
        addStockPanel.setVisible(true);
    }

    public void viewAccountDetailsPanel() {
        add(accountDetailsPanel);
        menuPanel.setVisible(false);
        addStockPanel.setVisible(false);
        equityPanel.setVisible(false);
        setNamePanel.setVisible(false);
        functionPanel.setVisible(false);
        accountDetailsPanel.setVisible(true);
    }

    public void viewEquityPanel() {
        add(equityPanel);
        menuPanel.setVisible(false);
        addStockPanel.setVisible(false);
        accountDetailsPanel.setVisible(false);
        setNamePanel.setVisible(false);
        functionPanel.setVisible(false);
        equityPanel.setVisible(true);
    }

    public void backToMenu() {
        setNamePanel.setVisible(false);
        addStockPanel.setVisible(false);
        accountDetailsPanel.setVisible(false);
        equityPanel.setVisible(false);
        functionPanel.setVisible(false);
        menuPanel.setVisible(true);
    }

    public void addStock() {
        Stock stock = new Stock(text1.getText(), Double.parseDouble(text2.getText()),
                Double.parseDouble(text3.getText()), Double.parseDouble(text4.getText()),
                Double.parseDouble(text5.getText()), Double.parseDouble(text6.getText()));

        account.addStock(stock);
        table1.setValueAt("Account Name:", 0, 0);
        table1.setValueAt("Account Cash Balance:", 1, 0);
        table1.setValueAt("Account Market Value:", 2, 0);
        table1.setValueAt("Account Book Cost:", 3, 0);
        table1.setValueAt("Account Loan Amount:", 4, 0);
        table1.setValueAt("Buying Power:", 5, 0);

        table1.setValueAt(account.getAccountName(), 0, 1);
        table1.setValueAt(account.getAccountCash(), 1, 1);
        Double market = 0.0;
        for (Stock s : account.getEquity()) {
            market = market + s.getMarketValue();
        }
        table1.setValueAt(market, 2, 1);
        table1.setValueAt(account.getAccountEquity(), 3, 1);
        Double loan = 0.0;
        for (Stock s1 : account.getEquity()) {
            loan = loan + s1.getLoan();
        }
        table1.setValueAt(loan, 4, 1);
        table1.setValueAt(account.getAccountCash() + loan, 5, 1);
    }

    public void removeStock() {
        String name = text7.getText();
        account.removeStock(name);
        table1.setValueAt("Account Name:", 0, 0);
        table1.setValueAt("Account Cash Balance:", 1, 0);
        table1.setValueAt("Account Market Value:", 2, 0);
        table1.setValueAt("Account Book Cost:", 3, 0);
        table1.setValueAt("Account Loan Amount:", 4, 0);
        table1.setValueAt("Buying Power:", 5, 0);

        table1.setValueAt(account.getAccountName(), 0, 1);
        table1.setValueAt(account.getAccountCash(), 1, 1);
        Double market = 0.0;
        for (Stock s : account.getEquity()) {
            market = market + s.getMarketValue();
        }
        table1.setValueAt(market, 2, 1);
        table1.setValueAt(account.getAccountEquity(), 3, 1);
        Double loan = 0.0;
        for (Stock s1 : account.getEquity()) {
            loan = loan + s1.getLoan();
        }
        table1.setValueAt(loan, 4, 1);
        table1.setValueAt(account.getAccountCash() + loan, 5, 1);
    }

    public void refresh() {

        Integer i = 1;
        for (Stock s : account.getEquity()) {
            table.setValueAt(s.getStockSymbol(), i, 0 );
            table.setValueAt(s.getStockQuantity(), i, 1 );
            table.setValueAt(s.getBookValue(), i, 2 );
            table.setValueAt(s.getMarketValue(), i, 3 );
            table.setValueAt(s.getStockMaintenanceRequirement(), i, 4 );
            table.setValueAt(account.calculateMarginPercentage(s), i,5 );
            table.setValueAt(account.receiveCall(s), i, 6 );
            i = i + 1;
        }
    }

    public void setAccountSettings() {
        account.setAccountName(accountText1.getText());
        account.setAccountCash(Double.parseDouble(accountText2.getText()));
        table1.setValueAt("Account Name:", 0, 0);
        table1.setValueAt("Account Cash Balance:", 1, 0);
        table1.setValueAt("Account Market Value:", 2, 0);
        table1.setValueAt("Account Book Cost:", 3, 0);
        table1.setValueAt("Account Loan Amount:", 4, 0);
        table1.setValueAt("Buying Power:", 5, 0);

        table1.setValueAt(account.getAccountName(), 0, 1);
        table1.setValueAt(account.getAccountCash(), 1, 1);
        Double market = 0.0;
        for (Stock s : account.getEquity()) {
            market = market + s.getMarketValue();
        }
        table1.setValueAt(market, 2, 1);
        table1.setValueAt(account.getAccountEquity(), 3, 1);
        Double loan = 0.0;
        for (Stock s1 : account.getEquity()) {
            loan = loan + s1.getLoan();
        }
        table1.setValueAt(loan, 4, 1);
        table1.setValueAt(account.getAccountCash() + loan, 5, 1);
    }

    public void goToFunctionPanel() {
        add(functionPanel);
        setNamePanel.setVisible(false);
        addStockPanel.setVisible(false);
        accountDetailsPanel.setVisible(false);
        equityPanel.setVisible(false);
        menuPanel.setVisible(false);
        functionPanel.setVisible(true);

    }

    public void calculate() {

        try {
            yahoofinance.Stock stock = YahooFinance.get(functionalityText1.getText());
            double x = stock.getQuote(true).getPrice().doubleValue();
            double shares = Math.floor(Double.parseDouble(functionalityText2.getText()) *
                    Double.parseDouble(functionalityText3.getText()) / x);
            functionalityLabel1.setText("Your buying power is: " + shares + " at " + x);
        } catch (IOException e) {
            System.out.println("Quote not found!");
        }

    }
}
