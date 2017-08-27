package com.linxin.coding.exception;

/**
 * Class representing checkout processing exception.
 */
public class CheckoutProcessingException extends Exception {
    private static final long serialVersionUID = 1L;

    public CheckoutProcessingException() {
        super();
    }

    public CheckoutProcessingException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CheckoutProcessingException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CheckoutProcessingException(final String message) {
        super(message);
    }

    public CheckoutProcessingException(final Throwable cause) {
        super(cause);
    }
}
