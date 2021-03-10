class ThreadEx10 implements Runnable {
    static boolean autoSave = false;

    public static void main(String[] args) {
        Thread t = new Thread(new ThreadEx10());
        // 데몬쓰레드는 다른 일반쓰레드의 작업을 돕는 보조역할. -> 일반쓰레드가 종료되면 데몬쓰레드도 종료됨.
        // 항상 start() 호출 전에 실행되어야 함.
        t.setDaemon(true);
        t.start();
        for (int i = 0; i <= 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println(i);
            if (i == 5) // 5초후 autoSave->true
                autoSave = true;
        }
        System.out.println("프로그램을 종료합니다.");
    }

    public void run() {
        while (true) {
            // while(true)에다가 반복문 내 빠져나올 수 있는 조건이 없기 때문에 무한루프를 돌게 되지만 일반쓰레드가 종료되면 종료됨.
            // 만약에 매개변수 t를 데몬쓰레도로 설정하지 않았다면 강제종료하지 않는 한 종료되지 않을 것.
            try { // 3초마다 autoSave()를 확인해서 true면 autoSave()를 실행
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
            }
            if (autoSave)
                autoSave();
        }
    }

    public void autoSave() {
        System.out.println("작업파일이 자동저장되었습니다.");
    }
}
