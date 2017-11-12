import javax.swing.*;
import java.awt.*;

public class Form  extends JFrame {

    private JPanel mainPanel;

    private Form() {

        //code for loading

        adjustDisplay();
    }


    private void adjustDisplay() {

        setContentPane(mainPanel);
        setSize(new Dimension(600, 600));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Title");
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) throws InterruptedException {
        new Form();
    }
}
