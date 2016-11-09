package model;

/**
 * Determines the type of a user account
 */
public enum UserType {
    User ("Users", 'U'),
    Worker ("Workers", 'W'),
    Manager ("Managers", 'M'),
    Administrator ("Adminstrators", 'A');

    UserType(String value, char code) {

        this.value = value;
        this.code = code;
    }

    private final String value;
    private final char code;

    public String getValue() { return value; }

    public char getCode() { return code; }
}
