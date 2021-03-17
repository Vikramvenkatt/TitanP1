package gui;

import javax.swing.*;

public class UserInterface extends JFrame {
    private JPanel drawPanel;
    private int width = 400;
    private int height = 500;

    public UserInterface()
    {
        add(drawPanel);
        setTitle("Flight to Titan");
        setSize(getWidth(),getHeight());
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
