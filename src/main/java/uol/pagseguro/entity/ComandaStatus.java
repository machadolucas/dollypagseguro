package uol.pagseguro.entity;

import lombok.AllArgsConstructor;

/**
 * Created by machadolucas on 02/11/16.
 */
@AllArgsConstructor
public enum ComandaStatus {

    BLANK(0, "blank"), //
    OPEN(1, "open"), //
    CLOSING(2, "closing"), //
    PAYING(3, "paying"), //
    PAID(4, "paid"), //
    CLOSED(5, "closed"), //
    REFUSED(6, "refused");

    private int code;
    private String name;

    @Override
    public String toString() {
        return this.name.toUpperCase();
    }
}
