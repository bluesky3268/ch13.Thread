class ThreadEx20 {
    public static void main(String[] args) {
        ThreadEx20_1 gc = new ThreadEx20_1();
        gc.setDaemon(true);
        gc.start();
        int requiredMemory = 0;

        for (int i = 0; i < 20; i++) {
            requiredMemory = (int) (Math.random() * 10) * 20;
            System.out.println("        requiredMemory: " + requiredMemory);
            // 필요한 메모리가 사용할 수 있는 양보다 크거나 메모리의 60%이상을 사용하면 gc를 깨운다.
            if (gc.freeMemory() < requiredMemory || gc.freeMemory() < gc.totalMemory() * 0.4) {
                gc.interrupt(); // 자고 있는 gc()깨우기
            }
            gc.usedMemory += requiredMemory;
            System.out.println(i + "usedMemory : " + gc.usedMemory);
        }
    }
}

class ThreadEx20_1 extends Thread {
    final static int MAX_MEMORY = 1000;
    int usedMemory = 0;

    public void run() {
        while (true) {
            try {
                Thread.sleep(10 * 1000); // 10초마다 1번씩 가비지 컬렉션을 수행.
            } catch (InterruptedException e) {
                System.out.println("Awaken by interrupt().");
            }
            gc();
            System.out.println("Garbage Collected. Free Memory : " + freeMemory());
        }
    }

    public void gc() {
        usedMemory -= 300;
        if (usedMemory < 0)
            usedMemory = 0;
    }

    public int totalMemory() {
        return MAX_MEMORY;
    }

    public int freeMemory() {
        return MAX_MEMORY - usedMemory;
    }
}
