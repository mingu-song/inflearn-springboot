package mingu.inflearn.config.advice;

import lombok.RequiredArgsConstructor;
import mingu.inflearn.config.exception.BaseException;
import mingu.inflearn.config.http.BaseResponse;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RequiredArgsConstructor
public class BaseControllerAdvice {
    private final MessageSource messageSource;

    @ExceptionHandler(value = { BaseException.class })
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    private BaseResponse<?> handleBaseException(BaseException e, WebRequest request) {
        return new BaseResponse<>(
                e.getResponseCode(),
                messageSource.getMessage(e.getResponseCode().name(), e.getArgs(), null));
    }
}
