package model;

/**
 * Determines the type of a user account
 */
public enum UserType {
    User ("Users", 'U'),
    Worker ("Workers", 'W'),
    Manager ("Managers", 'M'),
    Administrator ("Administrators", 'A');

    UserType(String value, char code) {

        this.value = value;
        this.code = code;
    }

    private final String value;
    private final char code;

    /**
     * @return the value of the UserType
     */
    public String getValue() { return value; }

    /**
     * @return returns the code character that represents the UserType
     */
    public char getCode() { return code; }
}
