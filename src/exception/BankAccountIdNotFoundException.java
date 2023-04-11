package exception;

import bankaccount.BankAccount;

public class BankAccountIdNotFoundException extends Exception {

    public BankAccountIdNotFoundException(String message){
        super(message);
    }
}
