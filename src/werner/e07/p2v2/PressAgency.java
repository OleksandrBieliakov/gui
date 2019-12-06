package werner.e07.p2v2;

import java.util.HashSet;
import java.util.Set;

class PressAgency {

    //note: use HashSet instead of TreeSet for demonstration of hashCode importance
    private Set<Person> subscribers = new HashSet<>();

    Set<Person> getSubscribers() {
        return subscribers;
    }

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
