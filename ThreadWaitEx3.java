import java.util.ArrayList;

class Customer3 implements Runnable {
    private Table3 table;
    private String food;

    Customer3(Table3 table, String food) {
        this.table = table;
        this.food = food;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            String name = Thread.currentThread().getName();
            table.remove(food);
            System.out.println(name + "ate a " + food);
        }
    }
}

class Cook3 implements Runnable {
    private Table3 table;

    Cook3(Table3 table) {
        this.table = table;
    }

    public void run() {
        while (true) {
            int idx = (int) (Math.random() * table.dishNum());
            table.add(table.dishNames[idx]);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }
}

class Table3 {
    String[] dishNames = { "donut", "donut", "burger" };
    final int MAX_FOOD = 6;
    private ArrayList<String> dishes = new ArrayList<>();

    public synchronized void add(String dish) {
        // 음식이 꽉 차면 락 반납하고 대기실로 이동
        while (dishes.size() >= MAX_FOOD) {
            String name = Thread.currentThread().getName();
            System.out.println(name + "is waiting.");
            try {
                wait();
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
        // 음식이 꽉 차지 않았으면 음식추가 후 CUST깨움
        dishes.add(dish);
        notify();
        System.out.println("Dishes : " + dishes.toString());
    } // End add()

    public void remove(String dishName) {
        synchronized (this) {
            String name = Thread.currentThread().getName();
            // 음식이 없으면 락을 반납 후 대기실로 이동.
            while (dishes.size() == 0) {
                System.out.println(name + "is waiting.");
                try {
                    wait(); // 락 반납
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
            // 음식을 먹고나서 cook호출
            while (true) {
                for (int i = 0; i < dishes.size(); i++) {
                    if (dishName.equals(dishes.get(i))) {
                        dishes.remove(i);
                        notify(); // 쉬고 있을 수도 있는 요리사에게 다시 만들어라고 깨움.
                        return;
                    }
                }
                // 원하는 음식이 없으면 락 반납하고 대기실로 이동
                try {
                    System.out.println(name + "is waiting.");
                    wait(); // 원하는 음식이 없는 CUST쓰레드 기다리게 하기
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
        } // End synchronized
    } // End remove()

    public int dishNum() {
        return dishNames.length;
    }
}

class ThreadWaitEx3 {
    public static void main(String[] args) throws Exception {
        Table3 table = new Table3();
        new Thread(new Cook3(table), "COOK1").start();
        new Thread(new Customer3(table, "donut"), "CUST1").start();
        new Thread(new Customer3(table, "burger"), "CUST2").start();

        Thread.sleep(2000);
        System.exit(0);
    }

}
