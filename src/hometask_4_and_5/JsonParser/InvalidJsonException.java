package hometask_4_and_5.JsonParser;

public class InvalidJsonException extends Throwable {
    public InvalidJsonException() {
    }

    public InvalidJsonException(String message) {
        super(message);
    }
}
