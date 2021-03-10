class ThreadEx21 {
    public static void main(String[] args) {
        Runnable r = new RunnableEx21();
        new Thread(r).start();
        new Thread(r).start(); // ThreadGroup에 의해 참조되므로 gc의 대상이 아님.
    }
}

class Account {
    private int balance = 1000;

    public int getBalance() {
        return balance;
    }

    public void withdraw(int money) {
        if (balance >= money) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            balance -= money;
        }
    }
}

class RunnableEx21 implements Runnable {
    Account acc = new Account();

    public void run() {
        while (acc.getBalance() > 0) {
            int money = (int) (Math.random() * 3 + 1) * 100;
            acc.withdraw(money);
            System.out.println("money : " + money);
            System.out.println("balance : " + acc.getBalance());
        }
    }
}
// if문(balance >= money)을 통과하자마자 Thread.sleep()을 통해 다른쓰레드에게 제어권을 넘김.
// -> 조건식을 통과 한 후 출금하기 직전에 다른 쓰레드가 끼어들어서 먼저 출금을 해서 잔액이 마이너스가 나올 수 있음.
// -> if문과 출금하는 문장은 하나의 임계영역으로 묶여져야 함. -> withdraw()에 synchronized만 붙이면 동기화 됨.