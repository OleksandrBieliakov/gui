package werner.e07.p2v2;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

class Person implements Comparable<Person> {
    private static int nextId = 1;
    private int id;
    private String name;
    //note: use Set instead of List to avoid the duplication of equal message strings
    private Map<Topics, Set<String>> messages = new TreeMap<>();

    @SuppressWarnings("unused")
    Person(String name) {
        this.name = name;
        id = nextId++;
    }

    //note: explicitly supply id for demo purpose
    Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id;
    }

    //note: try to comment or delete this method and see the difference: you'll get two copies of John
    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public int compareTo(Person other) {
        return id - other.id;
    }

    void addTopic(Topics topic) {
        if (!isInterested(topic))
            messages.put(topic, new HashSet<>());
    }

    boolean isInterested(Topics topic) {
        return messages.keySet().contains(topic);
    }

    void addMessage(Topics topic, String message) {
        messages.get(topic).add(message);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Topics t : messages.keySet()) {
            Set<String> news = messages.get(t);
            sb.append("  ");
            sb.append(t);
            sb.append(": ");
            sb.append(news.isEmpty() ? "no" : news.size());
            sb.append(" news\n");
            for (String s : news) {
                sb.append("    ");
                sb.append(s);
                sb.append('\n');
            }
        }
        return name + " (id=" + id + ")\n" + sb.toString();
    }
}
