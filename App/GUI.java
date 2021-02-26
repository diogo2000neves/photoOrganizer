package App;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame{
    public GUI(){
        JPanel panel1 = new JPanel(new GridLayout(3, 3));
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();

        JButton button1 = new JButton();
        JButton button2 = new JButton();

        button1.setText("1");
        button2.setText("2");

        //panel configuration
        panel1.setBackground(Color.red);
        panel2.setBackground(Color.green);
        panel3.setBackground(Color.blue);
        panel4.setBackground(Color.yellow);
        panel5.setBackground(Color.black);

        panel1.setPreferredSize(new Dimension(100,100));
        panel2.setPreferredSize(new Dimension(100,40));
        panel3.setPreferredSize(new Dimension(100,40));
        panel4.setPreferredSize(new Dimension(40,100));
        panel5.setPreferredSize(new Dimension(40,100));

        GridBagConstraints gc = new GridBagConstraints();

        gc.gridx = 0;
        gc.gridy = 0;
        panel1.add(button1, gc);
        gc.gridx = 1;
        gc.gridy = 0;
        panel1.add(button2, gc);

        //initial configurations of the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(15, 15));
        this.setSize(new Dimension(700, 400));
        this.setVisible(true);
        this.setTitle("Program");

        //other configurations of the frame
        this.add(panel1, BorderLayout.CENTER);
        this.add(panel2, BorderLayout.NORTH);
        this.add(panel3, BorderLayout.SOUTH);
        this.add(panel4, BorderLayout.WEST);
        this.add(panel5, BorderLayout.EAST);
    }
}
