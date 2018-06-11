package tree;

import java.util.ArrayList;

public class Family {
    private static Family ourInstance;

    public static Family getInstance() {
        if (ourInstance == null) {
            ourInstance = new Family();
        }
        return ourInstance;
    }

    private Family() {
    }

    private ArrayList<Person> personList;
    private ArrayList<Event> eventList;

    public ArrayList<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(ArrayList<Person> personList) {
        this.personList = personList;
    }

    public ArrayList<Event> getEventList() {
        return eventList;
    }

    public void setEventList(ArrayList<Event> eventList) {
        this.eventList = eventList;
    }
}
