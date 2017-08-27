package com.linxin.coding.exception;

public class CheckoutException extends Exception {
    private static final long serialVersionUID = 1L;

    public CheckoutException() {
        super();
    }

    public CheckoutException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CheckoutException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CheckoutException(final String message) {
        super(message);
    }

    public CheckoutException(final Throwable cause) {
        super(cause);
    }
}
