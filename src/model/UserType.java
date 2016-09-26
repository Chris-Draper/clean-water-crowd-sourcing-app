package model;

/**
 * Created by sbuck on 9/22/2016.
 */
public enum UserType {
    User ("Users"),
    Worker ("Workers"),
    Manager ("Managers"),
    Administrator ("Adminstrators");

    UserType(String value) {
        this.value = value;
    }

    private final String value;

    public String getValue() { return value; }
};
