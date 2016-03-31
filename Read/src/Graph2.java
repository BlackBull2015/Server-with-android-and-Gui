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
public class Graph2 extends JPanel {
    protected ArrayList<Reading> DataRead = new ArrayList();
    File file;
    JButton jbtn = new JButton("Draw");
    public Graph2(){
        setLayout(new MigLayout("", "", ""));
        add(jbtn, "center, span ,pushx, wrap");


        jbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!DataRead.isEmpty()){
                    java.util.List<Integer> Xaxis = new ArrayList<Integer>();
                    java.util.List<Integer> Yaxis = new ArrayList<Integer>();
                    java.util.List<Integer> Zaxis = new ArrayList<Integer>();
                    java.util.List<Integer> Heading = new ArrayList<Integer>();
                    //Random random = new Random();
                    int maxDataPoints = DataRead.size();
                    for (int i = 0; i < maxDataPoints ; i++) {
                        Xaxis.add(DataRead.get(i).getMagXValue());
                        Yaxis.add(DataRead.get(i).getMagYValue());
                        Zaxis.add(DataRead.get(i).getMagZValue());
                        Heading.add(DataRead.get(i).getHeading());

                    }
                    Graph2Draw mainPanel = new Graph2Draw(Xaxis,Yaxis,Zaxis,Heading);

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
