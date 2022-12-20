import javax.swing.*;
import java.awt.*;

public class UserPaddle extends Rectangle {

    UserPaddle(int topLeftx, int topLefty, int width, int height) {
        super(topLeftx, topLefty, width, height);
    }

    public void move(Point point){
        y = point.y;
    }



    public void draw(Graphics g){
        g.setColor(Color.CYAN);
        g.fillRect(x,y,width,height);
    }


}
