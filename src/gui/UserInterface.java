package gui;

import javax.swing.*;
import java.awt.*;

public class UserInterface extends JFrame {
    private JPanel drawPanel;
    private int width = 400;
    private int height = 500;
    private Environment environment;

    public UserInterface()
    {
        add(drawPanel);
        setTitle("Flight to Titan");
        setSize(getWidth(),getHeight());
    }

    @Override
    public void paintComponents(Graphics g) {
        //Draw our universe on a black background
        g.setColor(Color.black);
        g.fillRect(0,0,getWidth(),getHeight());
        environment.paintComponent(g);
    }

    public void setEnvironment(Environment e)
    {
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
}
