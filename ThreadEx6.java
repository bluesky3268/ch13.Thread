import javax.swing.JOptionPane;

class ThreadEx6 {
    // 하나의 쓰레드로 입력을 받고 숫자를 출력.
    // -> 그래서 입력을 받기 전까지 화면에 출력되지 않음.
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("아무값이나 입력하세요.");
        System.out.println("입력하신 값은 " + input + "입니다.");
        for (int i = 10; i > 0; i--) {
            System.out.println(i);
            try {
                Thread.sleep(1000); // 1초간 지연.
            } catch (Exception e) {
            }
        }
    }

}
