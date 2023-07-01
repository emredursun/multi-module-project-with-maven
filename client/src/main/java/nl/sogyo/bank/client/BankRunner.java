package nl.sogyo.bank.client;

import nl.sogyo.bank.domain.Bank;
import nl.sogyo.bank.domain.IBank;
import nl.sogyo.bank.domain.account.IBankAccount;
import nl.sogyo.bank.domain.client.IClient;
import nl.sogyo.bank.domain.exception.ClientNotFoundException;
import nl.sogyo.bank.domain.exception.InitializationParameterException;
import nl.sogyo.bank.domain.exception.UserInputException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BankRunner {

    private static final Pattern EXPRESSIONPATTERN = Pattern
            .compile("([^,]*)(,|$)");

    public static void main(String[] args) throws Exception {
        BankRunner bankRunner = new BankRunner();
        bankRunner.printMainMenu();
    }

    private final IBank bank = new Bank();

    private void printMainMenu() {
        boolean done = false;
        while (!done) {
            System.out.println("Java Bank - Main Menu");
            System.out.println();
            System.out.println("Select your choice:");
            System.out.println("1 - create a new client");
            System.out.println("2 - create a new account");
            System.out.println("3 - find a client");
            System.out.println("4 - find an account");
            System.out.println("9 - exit application");
            System.out.print("==> ");
            try {
                int response = Integer.parseInt(this.readUserInput());
                switch (response) {
                    case 1:
                        printCreateClientScreen();
                        break;
                    case 2:
                        printCreateAccountScreen();
                        break;
                    case 3:
                        printFindClientScreen();
                    case 4:
                        printFindAccountScreen();
                    case 9:
                        System.out.println("System exiting...");
                        done = true;
                        break;
                    default:
                        System.out
                                .println("Unknow menuitem seleced, please select an appropriate menuitem.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                this.printFatalErrorMsg();
                done = true;
            }
        }
        System.exit(0);
    }

    private void printCreateClientScreen() {

        boolean done = false;

        while (!done) {

            System.out.println("Java Bank - Create New Client");
            System.out.println();
            System.out.println("Enter name, adress, zipcode and city of client");
            System.out.println("(seperate items by using a comma \",\")");
            System.out.println("");
            System.out.print("==> ");

            try {
                handleCreateClient();
                this.readUserInput();
                done = true;
            } catch (IllegalArgumentException iae) {
                System.out.println("");
                System.out.println("Insufficient number of arguments.");
                System.out.println("Please try again.");
                System.out.println("");
            } catch (Exception e) {
                e.printStackTrace();
                this.printFatalErrorMsg();
                done = true;
            }
        }
    }

    private void handleCreateClient() throws Exception {

        String response = this.readUserInput();

        String clientName = null;
        String clientAdress = null;
        String clientZipCode = null;
        String clientCity = null;

        List<String> patternResult = new ArrayList<String>();
        Matcher matcher = EXPRESSIONPATTERN.matcher(response);

        while (matcher.find()) {
            if (matcher.group(0) != null) {
                patternResult.add(matcher.group(1));
            }
        }
        if (patternResult.size() < 3) {
            clientName = patternResult.get(0);
            clientAdress = patternResult.get(1);
        }
        if (patternResult.size() > 3) {
            clientName = patternResult.get(0);
            clientAdress = patternResult.get(1);
            clientZipCode = patternResult.get(2);
            clientCity = patternResult.get(3);
        }
        if (patternResult.size() >= 4) {
            clientCity = patternResult.get(3);
        }
        if (patternResult.size() < 1) {
            throw new IllegalArgumentException(
                    "Insufficient number of arguments to create a new client");
        }
        IClient client = bank.createNewClient(clientName, clientAdress, clientZipCode, clientCity);
        System.out.println();
        System.out.printf("New client with name \"%s\" created%n", client.getName());
        System.out.println("Press ENTER to return to main menu.");
    }

    private void printCreateAccountScreen() {
        boolean done = false;
        while (!done) {

            System.out.println("Java Bank - Create New Client");
            System.out.println();
            System.out.println("Enter the name of the client");
            System.out.println();
            System.out.print("==> ");

            try {
                handleCreateAccount();
                this.readUserInput();
                done = true;
            } catch (IllegalArgumentException iae) {
                System.out.println();
                System.out.println("Insufficient number of arguments.");
                System.out.println("Please try again.");
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
                this.printFatalErrorMsg();
                done = true;
            }

        }
    }

    private void handleCreateAccount() throws UserInputException, InitializationParameterException,
            ClientNotFoundException {
        String response = this.readUserInput();
        IBankAccount account = bank.createNewCheckingAccount(bank.getClient(response, null, null));

        System.out.println();
        System.out.printf("New account \"%s\" created%n", account.getAccountNumber());
        System.out.println("Press ENTER to return to main menu.");
    }

    private void printFindClientScreen() {

    }

    private void printFindAccountScreen() {

    }

    /**
     * Reads and returns the users input from the console.
     *
     * @return The input the user has entered.
     * @throws UserInputException
     */
    private String readUserInput() throws UserInputException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            String input = br.readLine();
            return input;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            throw new UserInputException();
        }
    }

    private void printFatalErrorMsg() {
        System.out
                .println("************************************************************");
        System.out
                .println("***** A fatal error occured. The system will shutdown! *****");
        System.out
                .println("*****                                                  *****");
        System.out
                .println("***** Please retry later                               *****");
        System.out
                .println("************************************************************");
    }

}