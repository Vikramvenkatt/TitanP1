package GenBody;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;


public class GUI extends JFrame{
    /*
    I will be creating the class global variables here, since apparently its better to declare them
    as I should have learnt from cs2
     */
    private JPanel mainPanel;
    private int sLength=1000;
    private JButton pause;

    public GUI(List bodies){
        //now i'll be setting the panels and stuff in the constructor
        mainPanel=new JPanel(){
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                drawBody((Graphics2D) g);

            }
        };
        mainPanel.setPreferredSize(new Dimension(sLength,sLength));
        mainPanel.setBackground(Color.BLACK);
        JFrame frame=new JFrame("Welcome to the simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setContentPane(mainPanel);

        pause=new JButton("Resume Simulation!");
        /*pause.addActionListener(e ->{
            //add condition here
        };

         */
        frame.add(pause);
        frame.setVisible(true);
    }
    //main testing method



    //creating a new method that will draw the bodies
    private void drawBody(Graphics2D g){
        int pWidth=mainPanel.getWidth();
        int pHeight=mainPanel.getHeight();

        g.setColor(Color.WHITE);//cos it'll be easier to see lol

    }


}
