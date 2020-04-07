package il.ac.hit.Exceptions;

import java.sql.SQLException;

/**
 * This is the exception class for the project
 * handles projects thrown exceptions
 * most of the exceptions thrown are SQL based exceptions.
 */
public class DBActionsException extends SQLException {

    /**
     * DBActionsException constructor using the super constructor
     *
     */
    public DBActionsException() {
        super();
    }

    /**
     * DBActionsException constructor gets the cause written message and act accordingly
     *
     * @param message  message that was passed from the throw and the
     */
    public DBActionsException(String message) {
        super(message);
    }

    /**
     * DBActionsException constructor gets the cause written message and the root cause and acts accordingly
     *
     * @param message message that was passed from the throw and the
     * @param cause root cause
     */
    public DBActionsException(String message, Throwable cause) {
        super(message, cause);
    }
    }
