import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JLabel;

class TwoPlayersPanel extends JPanel {
    JLabel title;

    public TwoPlayersPanel() {
        MyBoard myBoard = new MyBoard();
        title = new JLabel("2");
        title.setBounds(71, 50, 250, 50);
        title.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 60));
        this.add(title);
        this.setLayout(null);
    }
}
