package submission_2.exceptions;

/**
 * Thrown when attempting to perform an operation on an empty queue.
 */
public class EmptyQueueException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new EmptyQueueException with the default detail message.
     */
    public EmptyQueueException() {
        super("Queue is empty");
    }

    /**
     * Constructs a new EmptyQueueException with the specified detail message.
     *
     * @param message the detail message.
     */
    public EmptyQueueException(String message) {
        super(message);
    }
}
