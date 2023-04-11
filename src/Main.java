import accountmanager.AccountManager;
import bankaccount.BankAccount;
import exception.BankAccountIdNotFoundException;
import exception.BankAccountIsNotActiveException;
import exception.InsufficientFundsException;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InsufficientFundsException, BankAccountIsNotActiveException, BankAccountIdNotFoundException {

        AccountManager accountManager = new AccountManager();

        BankAccount account1 = new BankAccount(1424, "Georg", 1_000);
        BankAccount account2 = new BankAccount(7910, "Julia", 50_000);
        BankAccount account3 = new BankAccount(3481, "Viktor", 5_000);
        BankAccount account4 = new BankAccount(2133, "Sven", 0);
        BankAccount account5 = new BankAccount(9625, "Valera", 140_000);

        ////////////////////////////////// проверка переводов ///////////////////////////////////////

        // средства на аккаунтах до перевода
        System.out.println(account1.getAmount());
        System.out.println(account2.getAmount());
        // перевод
        accountManager.transaction(account1, account2, 1000);
        // средства на аккаунтах после перевода
        System.out.println(account1.getAmount());
        System.out.println(account2.getAmount());

        //////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////// проверка получения списка неактивных аккаунтов ////////////////////////

        account3.setActive(false);
        account4.setActive(false);
        account5.setActive(false);

        ArrayList<BankAccount> accounts = new ArrayList<>();

        accounts.add(account1);
        accounts.add(account2);
        accounts.add(account3);
        accounts.add(account4);
        accounts.add(account5);

        ArrayList<BankAccount> inactiveAccounts = accountManager.getInactiveAccounts(accounts);

        for (BankAccount account : inactiveAccounts) {
            System.out.println(account.toString());
        }

        //////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////// проверка получения аккаунта по id /////////////////////////////////

        System.out.println(accountManager.getAccount(accounts,9625).toString());

        //////////////////////////////////////////////////////////////////////////////////////////////
    }

}
