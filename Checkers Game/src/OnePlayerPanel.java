import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class OnePlayerPanel extends JPanel {
    JLabel title;

    public OnePlayerPanel() {
        MyBoard myBoard = new MyBoard();
        title = new JLabel("1");
        title.setBounds(71, 50, 250, 50);
        title.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 60));
        this.add(title);
        this.setLayout(null);
    }
}