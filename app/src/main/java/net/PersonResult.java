package net;

import tree.Person;

/**
 * Created by samosbor on 5/18/18.
 */

public class PersonResult {
    /**
     * An array of person objects to return
     */
    Person[] data;

    String message;

    /**
     * The constructor for the person result object
     *
     * @param data
     */
    public PersonResult(Person[] data) {
        this.data = data;
    }

    public PersonResult(String message) {
        this.message = message;
    }

    public Person[] getData() {
        return data;
    }

    public void setData(Person[] data) {
        this.data = data;
    }
}
