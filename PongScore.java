import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PongScore {

    ArrayList<String> score;

    PongScore(){

        try {

            Scanner s = new Scanner(new File("/Users/Wing/IdeaProjects/CIS 36B Final Project/src/pongScore.txt"));

            score = new ArrayList<>();
            // Skip column headings.

            // Read each line, ensuring correct format.
            while (s.hasNextLine())
            {
                score.add(s.nextLine());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("file not found");
        }

    }

    public void printScore(){

        String output1 = "";

        for (int i = 0; i < score.size(); i++) {
            output1 += score.get(i) + "\n";
        }

        JOptionPane.showMessageDialog(null, output1 , "Pong Score" , JOptionPane.PLAIN_MESSAGE);

    }

}
