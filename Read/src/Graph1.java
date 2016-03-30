import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Eric on 3/23/2016.
 */
public class Graph1 extends JPanel {
    protected ArrayList<Reading> DataRead = new ArrayList();
    File file;
    JButton jbtn = new JButton("Draw");
    JPanel draw = new JPanel();
    public Graph1(){

        draw.setSize(new Dimension(700,700));
        setLayout(new MigLayout("", "[]", "[] []"));
        add(jbtn, "wrap,center");
        add(draw,"grow, push");


        jbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



            }
        });





    }

    public void setData(ArrayList a) {DataRead = a;}
    public void SetFile(File f){
        file = f;
    }

}
