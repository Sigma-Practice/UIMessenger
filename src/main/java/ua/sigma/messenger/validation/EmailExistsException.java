package ua.sigma.messenger.validation;

/**
 * Created by Maks on 02.02.2015.
 */
@SuppressWarnings("serial")
public class EmailExistsException extends Throwable {

    public EmailExistsException(String message) {
        super(message);
    }
}