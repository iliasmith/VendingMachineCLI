package com.techelevator;

import com.techelevator.view.BasicUI;
import com.techelevator.view.MenuDrivenCLI;

import java.math.BigDecimal;

public class Application extends VendingMachine {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String MAIN_MENU_OPTION_SALES_REPORT = "Sales Report";
    private static final String SUB_MENU_OPTION_1 = "Feed Money";
    private static final String SUB_MENU_OPTION_2 = "Select Product";
    private static final String SUB_MENU_OPTION_3 = "Finish Transaction";
    private static final String[] SUB_MENU_OPTIONS = {SUB_MENU_OPTION_1, SUB_MENU_OPTION_2, SUB_MENU_OPTION_3};
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_SALES_REPORT};


    private final BasicUI ui;
    private static VendingMachine vendingMachine = new VendingMachine();

    public Application(BasicUI ui) {
        this.ui = ui;
    }

    public static void main(String[] args) {
        BasicUI cli = new MenuDrivenCLI();
        Application application = new Application(cli);
        application.run();
    }

    //MAIN MENU OPTIONS
    public void run() {
        boolean finished = false;
        while (!finished) {
            String selection = ui.promptForSelection(MAIN_MENU_OPTIONS);

            if (selection.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                System.out.print(vendingMachine.listInventory());
            } else if (selection.equals(MAIN_MENU_OPTION_PURCHASE)) {
                handleSubMenu();
            } else if (selection.equals(MAIN_MENU_OPTION_SALES_REPORT)) {
                vendingMachine.salesReport();

            } else if (selection.equals(MAIN_MENU_OPTION_EXIT)) {
                finished = true;
            }
        }
    }

    //SUB MENU OPTIONS
    public void handleSubMenu() {
        boolean finished = false;
        while (!finished) {
            String selection = ui.promptForSelection(SUB_MENU_OPTIONS);
            if (selection.equals(SUB_MENU_OPTION_1)) {
                int amount = ui.promptForInt("Enter money in whole dollar amount: $"); //feed money
                vendingMachine.depositMoney(amount);
                System.out.print(vendingMachine.getBalance());
            } else if (selection.equals(SUB_MENU_OPTION_2)) {
                String slotSelection = ui.promptForPosition("Please enter letter followed by number");
                if (vendingMachine.getBalance().compareTo(BigDecimal.ZERO) == 0) {
                    ui.output("Deposit money before making a selection");

                } else if (vendingMachine.getBalance().compareTo(vendingMachine.getPrice(slotSelection)) == -1) {
                    ui.output("Transaction denied");

                } else if (vendingMachine.getBalance().compareTo(vendingMachine.getPrice(slotSelection)) >= 0) {
                    String purchaseResult = vendingMachine.makePurchase(slotSelection); //method in vendingMachine
                    ui.output("New Balance: $" + vendingMachine.getBalance());
                    ui.output(purchaseResult);

                }
            } else if (selection.equals(SUB_MENU_OPTION_3)) {
                ui.output(vendingMachine.giveChange());


                finished = true;

            }
        }
    }

}
