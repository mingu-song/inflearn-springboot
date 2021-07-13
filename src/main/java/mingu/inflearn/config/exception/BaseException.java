package mingu.inflearn.config.exception;

import mingu.inflearn.config.http.BaseResponseCode;

public class BaseException extends AbstractBaseException {
    private static final long serialVersionUID = -4950104757170872475L;

    public BaseException() {
    }

    public BaseException(BaseResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public BaseException(BaseResponseCode responseCode, String[] args) {
        this.responseCode = responseCode;
        this.args = args;
    }
}
