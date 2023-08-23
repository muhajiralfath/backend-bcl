package com.xfour.busniesscapitalloan.constant;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public enum ERole {
    ROLE_ADMIN,
    ROLE_DEBTOR;

    public static ERole get(String value) {
        for (ERole eRole : ERole.values()) {
            if (eRole.name().equalsIgnoreCase(value)) return eRole;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "role not found");
    }

    public static ERole getByIndex(String index) {
        try {
            int idx = Integer.parseInt(index);
            for (ERole value : values()) {
                if (value.ordinal() == (idx - 1)) return value;
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "role not found");
        } catch (NumberFormatException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "can not parse: index must be a number");
        }
    }
}
