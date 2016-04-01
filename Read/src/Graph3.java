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
public class Graph3 extends JPanel {
    protected ArrayList<Reading> DataRead = new ArrayList();
    File file;
    JButton jbtn = new JButton("Draw");
    public Graph3(){
        setLayout(new MigLayout("", "", ""));
        add(jbtn, "center, span ,pushx, wrap");


        jbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!DataRead.isEmpty()){
                    System.out.println("Clicked");
                    Graph3Draw mainPanel = new Graph3Draw(DataRead);

                    JScrollPane scroll = new JScrollPane (mainPanel,
                            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    //   mainPanel.setSize(new Dimension(700,350));


                    add(scroll, "grow, push");
                }
            }
        });



    }

    public void setData(ArrayList a) {DataRead = a;}
    public void SetFile(File f){
        file = f;
    }
}
