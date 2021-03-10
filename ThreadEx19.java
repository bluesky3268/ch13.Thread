class ThreadEx19 {
    static long startTime = 0;

    public static void main(String[] args) {
        ThreadEx19_1 th1 = new ThreadEx19_1();
        ThreadEx19_2 th2 = new ThreadEx19_2();
        th1.start();
        th2.start();

        startTime = System.currentTimeMillis();

        try {
            th1.join();
            th2.join();
        } catch (InterruptedException e) {
        }
        System.out.println(Thread.currentThread() + "소요시간 : " + (System.currentTimeMillis() - ThreadEx19.startTime));
    } // End Main
}

// join()을 사용하여 main쓰레드가 바로 종료되지 않고 th1, th2가 작업을 마칠때 까지 기다리도록 함.

class ThreadEx19_1 extends Thread {
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print(new String("-"));
        }
    }
}

class ThreadEx19_2 extends Thread {
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print(new String("|"));
        }
    }
}