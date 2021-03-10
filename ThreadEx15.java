class ThreadEx15 {
    public static void main(String[] args) {
        RunImplEx15 r = new RunImplEx15();
        Thread th1 = new Thread(r, "*");
        Thread th2 = new Thread(r, "**");
        Thread th3 = new Thread(r, "***");
        th1.start();
        th2.start();
        th3.start();

        try {
            Thread.sleep(2000);
            th1.suspend(); // th1 잠시 중지
            Thread.sleep(2000);
            th2.suspend(); // th2 잠시 중지
            Thread.sleep(3000);
            th1.resume(); // th1 다시 재개
            Thread.sleep(3000);
            th1.stop(); // 강제종료
            th2.stop();
            Thread.sleep(2000);
            th3.stop();

        } catch (InterruptedException e) {
        }
    } // End Main
}

// sleep(2000) : 2초동안 쓰레드를 멈추게 함. 하지만 2초 후 바로 실행상태가 아닌 실행대기 상태가 됨.
// 복잡한 경우에는 suspend()와 stop을 사용하지 않는게 좋음.
class RunImplEx15 implements Runnable {
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}