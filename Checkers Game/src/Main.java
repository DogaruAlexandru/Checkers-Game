import javax.swing.*;

public class Main {

    private static void initUI() {
        JFrame f = new JFrame("Checkers");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new MyPanel());
        f.setSize(400, 400);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::initUI);
    }
}
