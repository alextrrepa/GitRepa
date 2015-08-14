package modbusserver.exception;

public class NullTagException extends ConfigException {
    public NullTagException() {
        super();
    }

    public NullTagException(String message) {
        super(message);
    }

    public NullTagException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullTagException(Throwable cause) {
        super(cause);
    }
}
