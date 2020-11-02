import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
    static MyMeniu meniu = new MyMeniu();
    static OnePlayerPanel  onePlayerPanel = new OnePlayerPanel();
    static TwoPlayersPanel twoPlayersPanel = new TwoPlayersPanel();

    private static void initUI() {
        JFrame f = new JFrame("Checkers");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setResizable(false);

        f.setSize(400, 400);
        f.remove(f.getContentPane());
        f.add(meniu);

        f.setSize(800, 800);

        f.remove(f.getContentPane());
        f.add(onePlayerPanel);

        f.setSize(800, 800);
        f.remove(f.getContentPane());
        f.add(twoPlayersPanel);


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::initUI);
    }
}
