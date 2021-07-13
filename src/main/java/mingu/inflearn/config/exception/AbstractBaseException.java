package mingu.inflearn.config.exception;

import mingu.inflearn.config.http.BaseResponseCode;

public abstract class AbstractBaseException extends RuntimeException {

    private static final long serialVersionUID = 3120256848897713169L;

    protected BaseResponseCode responseCode;
    protected Object[] args;

    public AbstractBaseException() {
    }

    public AbstractBaseException(BaseResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public BaseResponseCode getResponseCode() {
        return responseCode;
    }

    public Object[] getArgs() {
        return args;
    }
}
