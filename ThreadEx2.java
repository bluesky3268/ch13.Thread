public class ThreadEx2 {
    public static void main(String[] args) {
        ThreadEx2_1 t1 = new ThreadEx2_1();
        t1.start();
    }

}

class ThreadEx2_1 extends Thread {
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
// 호출스택 run -> throwException()인 이유는 main()는 이미 종료됨.
// 한 쓰레드에서 예외가 발생되어 종료되어도 다른 쓰레드의 실행에는 영향을 주지 않음을 보여줌.