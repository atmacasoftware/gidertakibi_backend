package net.atmacacode.backend.core.result;

import lombok.Getter;

import java.util.Map;

@Getter
public class ResultData<T> extends Result {

    private T data;

    public ResultData(boolean status, String message, String code, Map<String, String> validationErrors, T data) {
        super(status, message, code, validationErrors);
        this.data = data;
    }
}
