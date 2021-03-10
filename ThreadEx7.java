import javax.swing.JOptionPane;

class ThreadEx7 {
    public static void main(String[] args) {
        ThreadEx7_1 th1 = new ThreadEx7_1();
        th1.start();

        String input = JOptionPane.showInputDialog("아무값이나 입력하세요.");
        System.out.println("입력하신 값은 " + input + "입니다.");
    }
}
// 쓰레드를 2개로 나누어서 처리했기때문에 사용자가 입력을 마치지 않아도 카운트는 시작됨.

class ThreadEx7_1 extends Thread {
    public void run() {
        for (int i = 10; i > 0; i--) {
            System.out.println(i);
            try {
                sleep(1000);
            } catch (Exception e) {
            }
        }
    }// run
}
