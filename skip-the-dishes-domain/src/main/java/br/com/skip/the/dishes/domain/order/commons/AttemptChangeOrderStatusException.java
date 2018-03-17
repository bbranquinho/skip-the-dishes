package br.com.skip.the.dishes.domain.order.commons;

public class AttemptChangeOrderStatusException extends RuntimeException {

    public AttemptChangeOrderStatusException() {
    }

    public AttemptChangeOrderStatusException(String message) {
        super(message);
    }

    public AttemptChangeOrderStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public AttemptChangeOrderStatusException(Throwable cause) {
        super(cause);
    }

    public AttemptChangeOrderStatusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
