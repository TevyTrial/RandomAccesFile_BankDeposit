package randomaccesfile_bankdeposit;

/**
 *
 * @author s226440
 */
import java.io.IOException;
import java.util.Scanner;

public class BankDemo {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean flag = true;

        while (flag) {

            System.out.print("Enter bank account number: ");
            int accountNumber = in.nextInt();
            System.out.print("Enter amount to deposit: ");
            double depositAmount = in.nextDouble();

            try {
                BankData bankData = new BankData();
                bankData.open("bank.dat");

                int accountIndex = bankData.find(accountNumber);

                if (accountIndex != -1) {
                    // Account found
                    BankAccount account = bankData.read(accountIndex);
                    account.deposit(depositAmount);
                    bankData.write(accountIndex, account);
                    System.out.println("New balance: " + account.getBalance());
                } else {
                    // Account not found, create a new account
                    BankAccount account = new BankAccount(accountNumber, depositAmount);
                    bankData.write(bankData.size(), account);
                    System.out.println("New account created with balance: " + account.getBalance());
                }

                bankData.close();
            } catch (IOException e) {
                System.out.println("Error accessing bank data file.");
            }
            System.out.println("Done? (Y/N): ");
            String choice = in.next();           
                if (choice.equals("Y")) {
                    flag = false;
                }

        }
    }
}
