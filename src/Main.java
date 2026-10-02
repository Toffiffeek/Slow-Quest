import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame("Slow Quest");

        GamePanel gamePanel = new GamePanel();

        window.add(gamePanel);

        //Game window config
        window.setSize(640, 480);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);


    }
}