package fr.benco11.jlibecoledirecte.api.account;

import java.util.Arrays;
import java.util.Optional;

public enum AccountType {
    STUDENT("E"), FAMILY("1"), TEACHER("P"), STAFF("A");

    private final String code;

    AccountType(String code) {
        this.code = code;
    }

    public static Optional<AccountType> accountType(String code) {
        return Arrays.stream(values())
                .filter(t -> t.code.equals(code))
                .findAny();
    }

    public String getCode() {
        return code;
    }
}
