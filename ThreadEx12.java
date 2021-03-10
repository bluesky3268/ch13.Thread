class ThreadEx12 {
    public static void main(String[] args) {
        ThreadEx12_1 th1 = new ThreadEx12_1();
        ThreadEx12_2 th2 = new ThreadEx12_2();

        th1.start();
        th2.start();

        try {
            th1.sleep(2000);
        } catch (InterruptedException e) {
        }
        System.out.println(" == MAIN 종료 == ");
    }
}

class ThreadEx12_1 extends Thread {
    public void run() {
        for (int i = 0; i < 300; i++)
            System.out.print("-");
        System.out.println("<< th1 종료 >>");
    }
}

class ThreadEx12_2 extends Thread {
    public void run() {
        for (int i = 0; i < 300; i++)
            System.out.print("|");
        System.out.println("<< th2 종료 >>");
    }
}

// sleep()은 항상 현재 실행중인 쓰레드에 대해서 작동하는 것이기 때문에 th1.sleep(2000) 이렇게 호출해도 실제로는
// main메소드를 실행하는 main쓰레드가 영향을 받는 것.
// -> 그래서 sleep()은 static으로 선언되어 있고, 참조변수를 이용하지 말고 Thread.sleep(2000) 이렇게 호출하는
// 것이 바람직함.