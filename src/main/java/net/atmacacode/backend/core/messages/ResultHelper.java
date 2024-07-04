package net.atmacacode.backend.core.messages;

import net.atmacacode.backend.core.result.Result;
import net.atmacacode.backend.core.result.ResultData;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collectors;

public class ResultHelper {
    public static <T> ResultData<T> userCreated(T data) {
        return new ResultData<>(true, Msg.USER_CREATED, "201", null, data);
    }

    public static Result bodyMissing() {
        return new Result(false, Msg.BODY_MISSING, "400", null);
    }

    public static Result notValid(Map<String, String> errors) {
        return new Result(false, Msg.BODY_MISSING, "400", errors);
    }
}
