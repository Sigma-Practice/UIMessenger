package ua.sigma.messenger.validation;

/**
 * Created by Maks on 09.02.2015.
 */
public class LoginExistsException extends Throwable {
    public LoginExistsException(String message) {
        super(message);
    }
}
