package gui;

import GenBody.Planets;
import GenBody.Spaceship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;


public class UserInterface extends Canvas implements ActionListener, Runnable {
    private JPanel drawPanel;
    private int width = 1500;
    private int height = 1000;
    private Environment environment;
    private JButton running;
    private JPanel wlcmPanel;
    private JLabel msg;
    private Font font;
    private JPanel planPanel;
    private JButton zoomIn;
    private JButton zoomOut;
    private Planets planetsList;
    private Spaceship ship;
    private JFrame frame;
    private Thread thread;


    public UserInterface() {
        frame = new JFrame();

        //frame.setSize(getWidth(), getHeight());

//        wlcmPanel = new JPanel();
//        wlcmPanel.setBackground(Color.white);
//        wlcmPanel.setLayout(new FlowLayout());
//        planPanel = new JPanel();
//        planPanel.setBackground(Color.BLACK);
//        msg = new JLabel("Welcome to Launch simulation!");
//        msg.setHorizontalAlignment(JLabel.CENTER);
//        font = new Font("Sanserif", Font.BOLD, 22);
//        msg.setFont(font);
//        wlcmPanel.add(msg);
//
//        running = new JButton("Click to run");
//        //add action listener later!
//        running.addActionListener(this);
//        planPanel.add(running);
//
//        zoomIn = new JButton("+");
//        zoomIn.addActionListener(this);
//        planPanel.add(zoomIn);
//
//        zoomOut = new JButton("-");
//        zoomOut.addActionListener(this);
//        planPanel.add(zoomOut);

        this.environment = new Environment();
        this.environment.setPreferredSize(new Dimension(1500, 1000));
        this.frame.add(environment);

        //add(wlcmPanel, BorderLayout.NORTH);
        //add(planPanel, BorderLayout.CENTER);

        //setBackground(Color.BLACK);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Flight to Titan");
        frame.setResizable(false);
        frame.setVisible(true);
        this.frame.pack();
    }


//    @Override
//    public void paintComponents(Graphics g) {
//        //Adds all our planets and calls every individual draw from each planet
//        planetsList.addPlanets();
//        if (planetsList != null) {
//            for (int i = 0; i < planetsList.getPlanets().size(); i++) {
//                planetsList.getPlanets().get(i).draw(g);
//
//            }
//        }
//        //draw the ship at the initial position
//        ship.draw(g, (int) ship.getPosition().getX(), (int) ship.getPosition().getY());
//    }


    public void setEnvironment(Environment e) {
        this.environment = e;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == running) {
            if (running.getText().equals("Click to run")) {
                running.setText("Pause");
            } else if (running.getText().equals("Pause")) {
                running.setText("Click to run");
            }

        } else if (e.getSource() == zoomIn) {

        } else if (e.getSource() == zoomOut) {

        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.;
        double delta = 0;
        int frames = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) ;
            lastTime = now;
            while (delta >= 1) {
                render(frames);
                update();
                delta--;
                frames++;
            }
        }
    }

    private void render(int frames) {
        this.environment.run(frames);
    }

    private void update() {
    }

    public synchronized void start() {
        this.thread = new Thread(this, "Display");
        this.thread.start();
    }

}


