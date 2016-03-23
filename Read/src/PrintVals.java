import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Eric on 3/23/2016.
 */
public class PrintVals extends JPanel {

    JTextArea area = new JTextArea();

    public PrintVals(){

        add(area);

    }


    public void addToArea(File fl) throws IOException {
        String str;
        BufferedReader br = new BufferedReader(new FileReader(fl));

        while((str = br.readLine()) != null) {
            area.append(str);
            area.append("\n");
            //updateUI();
        }
    }


}
