package exception;

public class BankAccountIsNotActiveException extends Exception{

    public BankAccountIsNotActiveException(String message){
        super(message);
    }
}
