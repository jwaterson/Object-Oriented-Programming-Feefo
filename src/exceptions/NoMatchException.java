package exceptions;

import java.io.Serial;

public class NoMatchException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 4L;

    public NoMatchException() {
        super();
    }

    public NoMatchException(String msg) {
        super(msg);
    }
}
