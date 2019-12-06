package werner.e07.p2;

import java.util.Set;
import java.util.TreeSet;

class PressAgency {

    private Set<Person> subscribers = new TreeSet<>();

    void subscribe(Topics topic, Person person) {
        subscribers.add(person);
        person.addTopic(topic);
    }

    void broadcast(Topics topic, String message) {
        for (Person p : subscribers) {
            if (p.isInterested(topic)) {
                p.addMessage(topic, message);
            }
        }
    }

}
