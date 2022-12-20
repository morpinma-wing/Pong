import javax.swing.*;
import java.awt.*;

public class ComputerPaddle extends Rectangle {

    ComputerPaddle(int topLeftx, int topLefty, int width, int height) {
        super(topLeftx, topLefty, width, height);
    }

    public void move(int pongY){
        y = pongY;
    }

    public void draw(Graphics g){
        g.setColor(Color.PINK);
        g.fillRect(x,y,width,height);
    }


}
