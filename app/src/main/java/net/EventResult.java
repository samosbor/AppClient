package net;

import tree.Event;

/**
 * Created by samosbor on 5/18/18.
 */

public class EventResult {
    /**
     * An array of event objects to return
     */
    Event[] data;

    String message;

    /**
     * The constructor for the event result object
     *
     * @param data
     */
    public EventResult(Event[] data) {
        this.data = data;
    }

    public EventResult(String message) {
        this.message = message;
    }

    public Event[] getData() {
        return data;
    }

    public void setData(Event[] data) {
        this.data = data;
    }
}
