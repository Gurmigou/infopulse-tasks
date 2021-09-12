package hometask_4.JsonParser;

public class InvalidJsonException extends Throwable {
    public InvalidJsonException() {
    }

    public InvalidJsonException(String message) {
        super(message);
    }
}
