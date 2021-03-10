class ThreadEx5 {
    static long startTime = 0;

    public static void main(String[] args) {
        ThreadEx5_1 th1 = new ThreadEx5_1();
        th1.start();
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 300; i++) {
            System.out.printf("%s", new String("-"));
        }
        System.out.print("소요시간 : " + (System.currentTimeMillis() - ThreadEx5.startTime));
    }
}

class ThreadEx5_1 extends Thread {
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.printf("%s", new String("|"));
        }
        System.out.print("소요시간2 : " + (System.currentTimeMillis() - ThreadEx5.startTime));
    }
}
// 두개의 쓰레드로 작업했을 때(52+62) 싱글 쓰레드(46+62)보다 시간이 오래 걸린 이유는
// 첫번째는 번갈아가면서 작업을 하기 때문에 쓰레드간 작업전환 시간 때문이고
// 두번째는 한 쓰레드가 출력하고 있는 동안 다른 쓰레드는 출력이 끝나기를 기다려야 하는데 이 대기시간 때문이다.