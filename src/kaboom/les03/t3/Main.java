package kaboom.les03.t3;

public class Main {

    public static void main(String[] args) {

        Bank bank = new Bank();

        bank.addAcc("Ann", 10000L);
        bank.addAcc("Bob");

        System.err.println("Bank total1: " + bank.total());

        //Threads
        bank.cashInput("Bob", 10000L);
        bank.tranfer("Ann", "Bob", 1000L);
        bank.tranfer("Ann", "Bob", 2000L);
        bank.tranfer("Bob", "Ann", 1000L);
        bank.tranfer("Ann", "Bob", 500L);
        bank.tranfer("Bob", "Ann", 3000L);
        bank.tranfer("Bob", "Ann", 1000L);
        bank.tranfer("Ann", "Bob", 5000L);
        bank.cashWithdraw("Bob", 10000L);

        System.err.println("Bank total2: " + bank.total());

    }

}
