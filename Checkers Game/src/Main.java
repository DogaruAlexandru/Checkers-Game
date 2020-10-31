import javax.swing.*;

public class Main {

    private static void initUI() {
        JFrame f = new JFrame("Checkers");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new MyPanel());
        f.setSize(800, 800);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        //pornesc firul de executie grafic
        //fie prin implementarea interfetei Runnable, fie printr-un ob al clasei Thread
        //new Thread()
        SwingUtilities.invokeLater(Main::initUI);
    }
}
