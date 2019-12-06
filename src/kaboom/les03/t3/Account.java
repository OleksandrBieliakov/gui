package kaboom.les03.t3;

public class Account {

    private long balance;
    private String owner;

    public Account(String o) {
        owner = o;
    }

    public synchronized void add(long sum) {
        balance += sum;
    }

    public synchronized void take(long sum) {
        balance -= sum;
    }

    public long getBal() {
        return balance;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || getClass() != other.getClass()) return false;
        Account acc = (Account) other;
        return owner.equals(acc.owner);
    }

}
