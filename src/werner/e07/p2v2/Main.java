package werner.e07.p2v2;

public class Main {

    public static void main(String[] args) {
        Person john = new Person(1,"John");
        //note: create the EQUAL person
        Person john2 = new Person(1,"John");
        Person kate = new Person(2,"Kate");
        Person bill = new Person(3, "Bill");


        PressAgency agency = new PressAgency();

        agency.subscribe(Topics.POLITICS, john);
        agency.subscribe(Topics.SPORT, john);

        agency.subscribe(Topics.POLITICS, john2);
        agency.subscribe(Topics.SPORT, john2);

        agency.subscribe(Topics.POLITICS, kate);
        agency.subscribe(Topics.FASHION, kate);
        agency.subscribe(Topics.CELEBRITIES, bill);
        agency.subscribe(Topics.SPORT, bill);
        agency.broadcast(Topics.POLITICS,
                "Obama's speech in Washington");
        //note: avoid duplication of equal news
        agency.broadcast(Topics.POLITICS,
                "Obama's speech in Washington");
        agency.broadcast(Topics.FASHION,
                "Skirts get shorter this season!");
        agency.broadcast(Topics.SPORT,
                "Real-Atletico 2:1");
        agency.broadcast(Topics.POLITICS,
                "British-French summit in Paris");
        agency.broadcast(Topics.SPORT,
                "Hamilton wins in Australia");
        agency.broadcast(Topics.FASHION,
                "New handbags from Versace!");

        //note: iterate over all subscribers instead of showing them directly one by one
        for (Person subscriber : agency.getSubscribers()) {
            System.out.println(subscriber);
        }
    }
}
