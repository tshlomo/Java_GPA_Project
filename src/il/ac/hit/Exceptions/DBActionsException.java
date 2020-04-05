package il.ac.hit.Exceptions;

import java.sql.SQLException;

public class DBActionsException extends SQLException {

    public DBActionsException() {
        super();
    }

    public DBActionsException(String message) {
        super(message);
    }

    public DBActionsException(String message, Throwable cause) {
        super(message, cause);
    }
    }