import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Eric on 3/23/2016.
 */
public class Graph1 extends JPanel {
    private static final Logger log= Logger.getLogger(Graph1.class.getName());
    protected ArrayList<Reading> DataRead = new ArrayList();
    File file;
    JButton jbtn = new JButton("Draw");


    public Graph1(){

        setLayout(new MigLayout("", "", ""));
        add(jbtn, "center, span ,pushx, wrap");



        jbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                log.log(Level.INFO,"Attempting to draw data");
                if(!DataRead.isEmpty()){
                    java.util.List<Integer> scores = new ArrayList<Integer>();
                    java.util.List<Integer> scoresY = new ArrayList<Integer>();
                    java.util.List<Integer> scoresZ = new ArrayList<Integer>();
                    //Random random = new Random();
                    int maxDataPoints = DataRead.size();
                    for (int i = 0; i < maxDataPoints ; i++) {
                        scores.add(DataRead.get(i).getAccXValue());
                        scoresY.add(DataRead.get(i).getAccYValue());
                        scoresZ.add(DataRead.get(i).getAccZValue());

                    }
                    Graph1Draw mainPanel = new Graph1Draw(scores,scoresY,scoresZ);
                    JScrollPane scroll = new JScrollPane (mainPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                  //  mainPanel.setSize(new Dimension(700,350));
                    add(scroll, "grow, push");
                    log.log(Level.INFO,"Data drawn");
                }else
                    log.log(Level.WARNING,"Unable to create, no points to draw from");



            }
        });
    }
    public void setData(ArrayList a) {DataRead = a;}
    public void SetFile(File f){
        file = f;
    }

}
