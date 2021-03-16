package GenBody;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class test implements ActionListener {
    private static JButton running;

    public static void main(String[] args) {
        new test();
    }

    public test() {
        JFrame frame = new JFrame();
        frame.setSize(2000, 2000);
        JPanel wlcmPanel = new JPanel();
        wlcmPanel.setBackground(Color.blue);
        JPanel planPanel = new JPanel();
        planPanel.setBackground(Color.gray);
        JTextField msg = new JTextField("Welcome to Launch simulation!");
        Font font = new Font("Sanserif", Font.BOLD, 22);
        msg.setHorizontalAlignment(SwingConstants.CENTER);
        msg.setFont(font);
        wlcmPanel.add(msg);
        wlcmPanel.setLayout(new BoxLayout(wlcmPanel, BoxLayout.PAGE_AXIS));
        running = new JButton("Click to run");
        //add action listener later!
        running.addActionListener(this);
        planPanel.add(running);

        frame.add(wlcmPanel, BorderLayout.NORTH);
        frame.add(planPanel, BorderLayout.CENTER);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Titan Landing!");
        frame.pack();
        frame.setVisible(true);

    }


    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == running) {

        }
    }
}
