package modbusserver.exception;

public class NullConfigParamsException extends ConfigException {
    public NullConfigParamsException() {
        super();
    }

    public NullConfigParamsException(String message) {
        super(message);
    }

    public NullConfigParamsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullConfigParamsException(Throwable cause) {
        super(cause);
    }
}
