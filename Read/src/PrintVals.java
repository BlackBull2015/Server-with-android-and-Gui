import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Eric on 3/23/2016.
 */
public class PrintVals extends JPanel {
    private static final Logger log= Logger.getLogger(PrintVals.class.getName());

    protected ArrayList<Reading> DataRead = new ArrayList();


    JTextArea area = new JTextArea();
    JButton jbtn = new JButton("Read");
    JScrollPane scroll = new JScrollPane (area,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    File file;

    public PrintVals(){


        setLayout(new MigLayout("", "",""));
        add(jbtn, "wrap,center");
        add(scroll,"grow, push");

        jbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    try {
                        addToArea();
                        log.log(Level.INFO,"Data have been printed");
                    } catch (IOException e1) {
                        log.log(Level.WARNING,"Unable to display data.");
                        e1.printStackTrace();
                    }
                }
        });

    }


    public void addToArea() throws IOException {
        int size, count = 0;

        if(!DataRead.isEmpty()) {
            size = DataRead.size();
            System.out.println("Size is: " + size);
            area.setText("");
            while (count < size) {
                area.append(count + ":  " + DataRead.get(count).toString());
                area.append("\n");
                count++;
            }
        }
        else
            log.log(Level.WARNING,"Data points was empty");
    }

    public void setData(ArrayList a) {DataRead = a;}

    public void SetFile(File f){
        file = f;
    }


}
