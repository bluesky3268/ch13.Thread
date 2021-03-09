public class ThreadEx3 {
    public static void main(String[] args) {
        ThreadEx3_1 t1 = new ThreadEx3_1();
        t1.run();
    }
}

class ThreadEx3_1 extends Thread {
    public void run() {
        throwException();
    }

    public void throwException() {
        try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
// 이번 예제는 새로운 쓰레드가 생성된 것이 아니라
// 그냥 ThreadEx3_1의 run()가 호출된 것.
// 그렇기 때문에 예외가 발생한 당시 호출스택을 보면
// main, run, throwException이 있음.