import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Choose {
    static private JFrame Frame;
    static private MyMenu menu;
    static private OnePlayerPanel onePlayerPanel;
    static private TwoPlayersPanel twoPlayersPanel;
    static private JPanel panels;
    static private CardLayout cardLayout;
    static public Color myWhite;
    static public Color myBlack;
    static public Color myBrown;
    static public Image background;
    static public Image redPiece;
    static public Image redKingedPiece;
    static public Image blackPiece;
    static public Image blackKingedPiece;

    Choose() {
        try {
            background = ImageIO.read(new File("Checkers Game\\resources\\background.png"));
            redPiece = ImageIO.read(new File("Checkers Game\\resources\\red.png"));
            redKingedPiece = ImageIO.read(new File("Checkers Game\\resources\\redKinged.png"));
            blackPiece = ImageIO.read(new File("Checkers Game\\resources\\black.png"));
            blackKingedPiece = ImageIO.read(new File("Checkers Game\\resources\\blackKinged.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

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

        myWhite = new Color(255, 255, 214);
        myBlack = new Color(46, 46, 38);
        myBrown = new Color(138, 108, 70);
    }

    static public void OpenMenu() {
        Frame.setSize(400, 400);
        cardLayout.show(panels, "menu");
    }

    static public void OpenOnePlayerPanel() {
        Frame.setSize(1000, 700);
        cardLayout.show(panels, "onePlayerPanel");
    }

    static public void OpenTwoPlayersPanel() {
        Frame.setSize(1000, 700);
        cardLayout.show(panels, "twoPlayersPanel");
    }
}