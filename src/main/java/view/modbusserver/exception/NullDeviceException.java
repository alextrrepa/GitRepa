package view.modbusserver.exception;

public class NullDeviceException extends ConfigException {
    public NullDeviceException() {
        super();
    }

    public NullDeviceException(String message) {
        super(message);
    }

    public NullDeviceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullDeviceException(Throwable cause) {
        super(cause);
    }
}
