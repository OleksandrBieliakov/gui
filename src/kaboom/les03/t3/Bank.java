package kaboom.les03.t3;

import java.util.ArrayList;

public class Bank {

    private ArrayList<Account> accounts = new ArrayList<>();
    private int threadsRunning = 0;

    public void addAcc(String owner, long balance) {
        Account newAcc = new Account(owner);
        newAcc.add(balance);
        accounts.add(newAcc);
    }

    public void addAcc(String owner) {
        accounts.add(new Account(owner));
    }

    private Account getAcc(String owner) {
        return accounts.get(accounts.indexOf(new Account(owner)));
    }

    public void cashInput(String to, long sum) {
        plusT();
        new Thread(() -> {
            getAcc(to).add(sum);
            System.err.println("To " + to + " - " + sum);
            minusT();
        }).start();
    }

    public void cashWithdraw(String from, long sum) {
        plusT();
        new Thread(() -> {
            getAcc(from).take(sum);
            System.err.println("From " + from + " - " + sum);
            minusT();
        }).start();
    }

    private synchronized void plusT() {
        threadsRunning++;
    }

    private synchronized void minusT() {
        threadsRunning--;
    }

    public void tranfer(String from, String to, long sum) {
        plusT();
        new Thread(() -> {
            getAcc(from).take(sum);
            System.err.println("(from)From " + from + " to " + to + " - " + sum);
            getAcc(to).add(sum);
            System.err.println("(to)From " + from + " to " + to + " - " + sum);
            minusT();
        }).start();
    }

    public long total() {
        while (threadsRunning > 0) {
            //System.err.println("threadsRunning: " + threadsRunning);
            try {
                Thread.sleep(50);
            } catch (InterruptedException ignored) {
            }
            //System.err.println("threadsRunning: " + threadsRunning);
        }
        long sum = 0;
        for (Account acc : accounts) {
            sum += acc.getBal();
        }
        return sum;
    }

}
