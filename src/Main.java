import gui.Environment;
import gui.UserInterface;
import java.awt.Graphics;

public class Main {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        Environment e = new Environment();
        ui.setEnvironment(e);
        ui.setVisible(true);
        ui.repaint();
    }
}
