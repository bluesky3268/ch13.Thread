public class ThreadEx22 {
    public static void main(String[] args) {
        Runnable r = new RunnableEx22();
        new Thread(r).start();
        new Thread(r).start(); // ThreadGroup에 의해 참조되므로 gc의 대상이 아님.
    }
}

class Account2 {
    private int balance = 1000;

    public int getBalance() {
        return balance;
    }

    public synchronized void withdraw2(int money) {
        if (balance >= money) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            balance -= money;
        }
    }
}
// 한 쓰레드에 의해 withdraw()가 호출되면 종료되서 lock이 반납될 때까지 다른 쓰레드들은 withdraw()릃 호출하더라도
// 대기상태에 머문다.

class RunnableEx22 implements Runnable {
    Account2 acc = new Account2();

    public void run() {
        while (acc.getBalance() > 0) {
            int money = (int) (Math.random() * 3 + 1) * 100;
            acc.withdraw2(money);
            System.out.println("money : " + money);
            System.out.println("balance : " + acc.getBalance());
        }
    }
}
