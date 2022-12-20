import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Pong extends JPanel implements ActionListener, MouseListener {

    private static final int BALL_SIZE = 20;
    private static final int DELAY = 150;
    private static final int SCREEN_WIDTH = 900;
    private static final int SCREEN_HEIGHT = 800;
    int y;
    String name;
    int lives;

    PaddleBall pong;
    ComputerPaddle computerPaddle;
    UserPaddle userPaddle;
    char direction = 'L' ;

    public int playerScore = 0;
    public int computerScore = 0;
    int finalScore;
    boolean running = false;
    Timer timer;
    Image background;

    public Pong() {
        getPlayerName();

        this.setPreferredSize( new Dimension( SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);

        startGame();

    }

    public void startGame() {

        background = new ImageIcon("/Users/Wing/IdeaProjects/CIS 36B Final Project/src/pongBackground.png").getImage();

        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
        pong = new PaddleBall(SCREEN_WIDTH/2 , SCREEN_HEIGHT/2 , BALL_SIZE , BALL_SIZE);
        computerPaddle = new ComputerPaddle(SCREEN_WIDTH * 17 / 20, SCREEN_HEIGHT/5 , SCREEN_WIDTH * 1 / 25, SCREEN_HEIGHT * 1 / 6 );
        userPaddle = new UserPaddle(SCREEN_WIDTH * 1 /20, y , SCREEN_WIDTH * 1 / 25, SCREEN_HEIGHT * 1 / 6);
        lives = 5;

    }

    public void getPlayerName(){
        name = JOptionPane.showInputDialog(" What is your name? ", "Hi there");

        JOptionPane.showMessageDialog(null , "Hi " + name);

    }

    public void writeScore(){

        finalScore = playerScore;

        try {

            File file = new File("/Users/Wing/IdeaProjects/CIS 36B Final Project/src/pongScore.txt");

            FileWriter fw = new FileWriter( file,true);
            // initialize our BufferedWriter
            BufferedWriter bw = new BufferedWriter(fw);

            // write values
            bw.newLine();
            bw.write(name + "\t" + "\t" + finalScore );

            // close the BufferedWriter object to finish operation
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g ){

        if(running) {

            g.drawImage(background , 0 , 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);

            g.setColor(Color.RED);
            g.drawString("Player Score: "+ playerScore + " VS Computer Score " + computerScore, 250 , 50 );

            g.setColor(Color.ORANGE);
            g.drawString(" Lives: "+ lives ,  600 , 50 );

            pong.draw(g);

            computerPaddle.draw(g);

            userPaddle.draw(g);

        }

        else {
            gameOver(g);
            timer.stop();
            writeScore();

        }
    }

    public void gameOver(Graphics g) {
        //Score

        g.setColor(Color.red);
        g.setFont( new Font("Ink Free",Font.BOLD, 30));
        g.drawString("Player Score: "+ playerScore + " VS Computer Score " + computerScore, 250 , 50 );

        //Game Over text
        g.setColor(Color.red);
        g.setFont( new Font("Ink Free",Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
    }

    public void move(){

        PointerInfo pi = MouseInfo.getPointerInfo();

        // get the location of mouse
        Point p = pi.getLocation();

        userPaddle.move(p);

        computerPaddle.move(pong.y);

        switch(direction) {
            case 'L':
                pong.moveL();
                break;
            case 'T':
                pong.moveT();
                break;
            case 'B':
                pong.moveB();
                break;
            case 'R':
                pong.moveR();
                break;
        }

    }

    public void checkBorder(){
        if (pong.x < 0 + BALL_SIZE){
            pong.setLocation(SCREEN_WIDTH/2 , SCREEN_HEIGHT/2);
            computerScore ++;
            lives--;
        }

        if (pong.x > SCREEN_WIDTH - BALL_SIZE){
            pong.setLocation(SCREEN_WIDTH/2 , SCREEN_HEIGHT/2);
            playerScore ++;
        }

        if ( ( pong.y < 0 + BALL_SIZE ) && (direction == 'L' ) ){
            direction = 'B' ;
        }

        if ( ( pong.y < 0 + BALL_SIZE) && (direction == 'T' ) ){
            direction = 'R' ;
        }

        if ( ( pong.y > SCREEN_HEIGHT - BALL_SIZE) && (direction == 'B' ) ){
            direction = 'L' ;
        }

        if ( ( pong.y > SCREEN_HEIGHT - BALL_SIZE ) && (direction == 'R' ) ){
            direction = 'T' ;
        }

    }

    public void checkCollision(){
        if ( (pong.intersects(userPaddle)) && (direction == 'L' ) ){
            direction = 'T' ;
        }

        if ( (pong.intersects(userPaddle)) && (direction == 'B' ) ){
            direction = 'R' ;
        }

        if ( (pong.intersects(computerPaddle)) && (direction == 'T' ) ){
            direction = 'L' ;
        }

        if ( (pong.intersects(computerPaddle)) &&  (direction == 'R') ){
            direction = 'B' ;
        }

    }

    public void checkLives(){
        if (lives == 0){
            running = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (running){
            move();
            checkBorder();
            checkCollision();
            checkLives();
        }

        repaint();

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        timer.start();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        timer.stop();
    }

    
}