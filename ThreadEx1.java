// Thread를 구현하는 방법
// 1. Thread클래스를 상속
// 2. Runnable인터페이스를 구현
//      -> 재사용성이 높고 코드의 일관성을 유지할 수 있음. -> 보다 객체지향적인 방법.
class ThreadEx1 {

    public static void main(String[] args) {
        ThreadEx1_1 t1 = new ThreadEx1_1();
        Runnable r = new ThreadEx1_2();
        Thread t2 = new Thread(r); // 생성자 Thread(Runnable target)

        t1.start();
        t2.start();
    }
}

class ThreadEx1_1 extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(getName());
        }
    }
}

class ThreadEx1_2 implements Runnable {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName());
            // 상속받은 게 아니기 때문에 이런식으로 다 호출해야함.
        }
    }
}
