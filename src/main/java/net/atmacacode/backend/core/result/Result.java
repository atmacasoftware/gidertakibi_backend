package net.atmacacode.backend.core.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Result {
    private boolean status;
    private String message;
    private String code;
    private Map<String, String> errorList = new HashMap<>();

}
