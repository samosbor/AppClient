package tree;

import java.util.ArrayList;

class Family {
    private static Family ourInstance;

    static Family getInstance() {
        if (ourInstance == null) {
            ourInstance = new Family();
        }
        return ourInstance;
    }

    private Family() {
    }

    private ArrayList<Person> personList;
    private ArrayList<Event> eventList;

}
