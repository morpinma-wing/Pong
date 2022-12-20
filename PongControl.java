
import javax.swing.*;


public class PongControl extends JFrame {

    PongControl(){
        this.setSize(800,800);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.add(new Pong());
        this.setTitle("Pong Game");
        this.setResizable(false);
        this.pack();
    }

    public static void main(String[] args) {
        new PongControl();
    }

}