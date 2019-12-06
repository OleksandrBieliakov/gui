package werner.e07.p2;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Person implements Comparable<Person> {
    private static int nextId = 1;
    private int id;
    private String name;
    private Map<Topics, ArrayList<String>> messages = new TreeMap<>();

    Person(String name) {
        this.name = name;
        id = nextId++;
    }

    @Override
    public int compareTo(Person other) {
        return id - other.id;
    }

    void addTopic(Topics topic) {
        if (!messages.keySet().contains(topic))
            messages.put(topic, new ArrayList<>());
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
            ArrayList<String> news = messages.get(t);
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
