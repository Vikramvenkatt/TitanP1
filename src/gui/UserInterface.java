package gui;

import GenBody.AddZoomFactor;
import GenBody.Planets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface extends Canvas implements ActionListener, Runnable {
    private JPanel drawPanel;
    private int width = 1500;
    private int height = 1000;
    private Environment environment;
    private JPanel wlcmPanel;
    private JLabel label;
    private Font font;
    private JButton zoomIn;
    private JButton zoomOut;
    private Planets planetsList;
    private JFrame frame;
    private Thread thread;

    public UserInterface() {
        frame = new JFrame();

        wlcmPanel = new JPanel();
        label = new JLabel("Welcome to Launch simulation!");
        font = new Font("Sanserif", Font.BOLD, 22);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(font);
        wlcmPanel.add(label);
        wlcmPanel.setLayout(new BoxLayout(wlcmPanel, BoxLayout.LINE_AXIS));

        zoomIn = new JButton("+");
        zoomOut = new JButton("-");
        zoomIn.addActionListener(this);
        zoomOut.addActionListener(this);


        Box iconPanel = new Box(BoxLayout.Y_AXIS);
        iconPanel.add(zoomIn);
        iconPanel.add(zoomOut);
        iconPanel.setVisible(true);
        frame.add(iconPanel, BorderLayout.WEST);
        frame.add(wlcmPanel, BorderLayout.NORTH);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Titan Landing!");
        frame.pack();
        frame.setVisible(true);


        this.environment = new Environment();
        this.environment.setPreferredSize(new Dimension(1500, 1000));
        this.frame.add(environment);

        //setBackground(Color.BLACK);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Flight to Titan");
        frame.setResizable(false);
        frame.setVisible(true);
        this.frame.pack();
    }


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


        if (e.getSource() == zoomIn) {
            AddZoomFactor.addFactor();

        } else if (e.getSource() == zoomOut) {

           AddZoomFactor.decreaseFactor();

        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0/1000;
        double delta = 0;
        int frames = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) /ns;
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

