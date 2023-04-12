
package accountmanager;

import bankaccount.BankAccount;
import exception.BankAccountIdNotFoundException;
import exception.BankAccountIsNotActiveException;
import exception.InsufficientFundsException;

import java.util.ArrayList;

public class AccountManager {

    // перевести деньги с одного аккаунта на другой
    public void transaction(BankAccount sender, BankAccount receiver, int transferAmount)
            throws BankAccountIsNotActiveException, InsufficientFundsException {

        checkAccountIsActive(sender, receiver);
        checkTransactionIsPossible(sender, transferAmount);

        sender.setAmount(sender.getAmount() - transferAmount);
        receiver.setAmount(receiver.getAmount() + transferAmount);

    }


    // вернуть список НЕактивных счетов
    public ArrayList<BankAccount> getInactiveAccounts(ArrayList<BankAccount> accounts) {

        ArrayList<BankAccount> inactiveAccounts = new ArrayList<>();

        for (BankAccount bankAccount : accounts) {
            if (!bankAccount.isActive()) {
                inactiveAccounts.add(bankAccount);
            }
        }

        return inactiveAccounts;
    }

    // получить счёт по id
    public BankAccount getAccount(ArrayList<BankAccount> accounts, int id) throws BankAccountIdNotFoundException {

        BankAccount account = null;

        for (BankAccount bankAccount : accounts) {
            if (bankAccount.getId() == id) {
                account = bankAccount;
                break;
            }
        }

        if (account == null) throw new BankAccountIdNotFoundException("Счёта с id: '"
                + id + "' не существует");

        return account;
    }

    // проверка, активны ли аккаунты
    private void checkAccountIsActive(BankAccount sender, BankAccount receiver) throws BankAccountIsNotActiveException {
        if (!sender.isActive()) {
            throw new BankAccountIsNotActiveException("Счёт отправителя неактивен");
        } else if (!receiver.isActive()) {
            throw new BankAccountIsNotActiveException("Счёт получателя неактивен");
        }
    }

    // проверка, достаточно ли средств для перевода
    private void checkTransactionIsPossible(BankAccount sender, int transferAmount) throws InsufficientFundsException {
        if (sender.getAmount() < transferAmount) {
            throw new InsufficientFundsException("Сумма перевода превышает доступную сумму средств на счету");
        }
    }

}
