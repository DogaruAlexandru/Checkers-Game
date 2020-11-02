import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class Choose {
    static private JFrame Frame;
    static private MyMenu menu;
    static private OnePlayerPanel onePlayerPanel;
    static private TwoPlayersPanel twoPlayersPanel;
    static private JPanel panels;
    static private CardLayout cardLayout;

    Choose() {
        Frame = new JFrame("Checkers");
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setVisible(true);
        Frame.setResizable(false);

        menu = new MyMenu();
        onePlayerPanel = new OnePlayerPanel();
        twoPlayersPanel = new TwoPlayersPanel();

        panels = new JPanel();
        cardLayout = new CardLayout();
        panels.setLayout(cardLayout);
        panels.add(menu, "menu");
        panels.add(onePlayerPanel, "onePlayerPanel");
        panels.add(twoPlayersPanel, "twoPlayersPanel");
        Frame.add(panels);
    }

    static public void OpenMenu() {
        Frame.setSize(400, 400);
//        cardLayout = (CardLayout) panels.getLayout();
        cardLayout.show(panels, "menu");
    }

    static public void OpenOnePlayerPanel() {
        Frame.setSize(900, 700);
//        cardLayout = (CardLayout) panels.getLayout();
        cardLayout.show(panels, "onePlayerPanel");
    }

    static public void OpenTwoPlayersPanel() {
        Frame.setSize(700, 700);
//        cardLayout = (CardLayout) panels.getLayout();
        cardLayout.show(panels, "twoPlayersPanel");
    }
}