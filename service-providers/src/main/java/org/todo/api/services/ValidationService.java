package org.todo.api.services;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class ValidationService {

    private static final String regex = "/^[a-zA-Z0-9_-]+(?:\s[a-zA-Z0-9_-]+)*$/";
    public boolean isValueValid(@NotNull String value, String type) throws Exception {
        String exception = null;
        String lValue = value.trim();
        if(lValue.length() == 1) {
            exception = "Value of type " + type + " should have more than one character.";
        } else if(!Pattern.compile(regex).matcher(lValue).matches()) {
            exception = "Value is invalid.";
        }

        if(!exception.isEmpty()) {
            throw new Exception(exception);
        }

        return true;
    }
}
