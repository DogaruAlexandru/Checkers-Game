import java.awt.*;

import javax.swing.*;

public class OnePlayerPanel extends JPanel {
    JLabel title;
    JLabel Player1;
    JLabel Computer;
    JLabel Score;
    JButton Reset;
    JButton Back;
    public OnePlayerPanel() {
        MyBoard myBoard = new MyBoard();
        title = new JLabel("Checkers");
        title.setBounds(385, 5, 250, 50);
        title.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 60));
        title.setForeground(Color.BLACK);
        //title.setBackground(Color.BLACK);
        title.setOpaque(true);
        Player1 = new JLabel("Player1");
        Player1.setBounds(730, 150, 180, 50);
        Player1.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 40));
        Player1.setForeground(Color.BLACK);
        //Player1.setBackground(Color.BLACK);
        Player1.setOpaque(true);
        Player1.setHorizontalTextPosition(SwingConstants.CENTER);
       // Player1.set
        Score= new JLabel("0-0");
        Score.setBounds(770, 230, 55, 50);
        Score.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 40));
        Score.setForeground(Color.BLACK);
        //Score.setBackground(Color.BLACK);
        Score.setOpaque(true);
        Computer= new JLabel("Computer");
        Computer.setBounds(730, 300, 180, 50);
        Computer.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 40));
        Computer.setForeground(Color.BLACK);
        //Computer.setBackground(Color.BLACK);
        Computer.setOpaque(true);
        Reset=new JButton("Reset");
        Reset.setBounds(730,510,150,50);
        Reset.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 40));
        Reset.setForeground(Color.BLACK);
        //Reset.setBackground(Color.BLACK);
        Reset.setOpaque(true);
        Back=new JButton("Back");
        Back.setBounds(730,460,150,50);
        Back.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 40));
        Back.setForeground(Color.BLACK);
        //Back.setBackground(Color.BLACK);
        Back.setOpaque(true);
        this.add(title);
        this.add(Player1);
        this.add(Computer);
        this.add(Score);
        this.add(Reset);
        this.add(Back);
        this.setLayout(null);
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(70,60,620,620);
        g.drawRect(100,90,560,560);
       for(int index=100;index<=630;index+=70)
           for(int index1=90;index1<=630;index1+=70)
               g.drawRect(index,index1,70,70);
    }
}