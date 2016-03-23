import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Eric on 3/23/2016.
 */
public class PrintVals extends JPanel {

    JTextArea area = new JTextArea();
    JButton jbtn = new JButton("Read");
    JScrollPane scroll = new JScrollPane (area,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    File file;

    public PrintVals(){

        setLayout(new MigLayout("", "[]","[] []"));
        add(jbtn, "wrap,center");
        add(scroll,"grow, push");

        jbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(file != null) {

                    try {
                        addToArea();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

    }


    public void addToArea() throws IOException {

        String str;
        BufferedReader br = new BufferedReader(new FileReader(file));

        while((str = br.readLine()) != null) {
            area.append(str);
            area.append("\n");
            //updateUI();
        }
    }

    public void SetFile(File f){
        file = f;
    }


}
