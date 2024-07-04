package net.atmacacode.backend.core.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class Result {
    private boolean status;
    private String message;
    private String code;
    private Map<String, String> errorList = new HashMap<>();

}
