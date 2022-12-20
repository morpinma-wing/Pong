import javax.swing.*;
import java.awt.*;

public class PaddleBall extends Rectangle {

    PaddleBall(int topLeftx, int topLefty, int width, int height) {
        super(topLeftx, topLefty, width, height);
    }

    public void moveL(){
        x = x - 25;
        y = y - 25;
    }

    public void moveT(){
        x = x + 25 ;
        y = y - 25 ;
    }

    public void moveB(){
        x = x - 25 ;
        y = y + 25 ;
    }

    public void moveR(){
        x = x + 25 ;
        y = y + 25 ;
    }

    public void draw(Graphics g){
        g.setColor(Color.lightGray);
        g.fillOval(x,y,width,height);
    }


}
