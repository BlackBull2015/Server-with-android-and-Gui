import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;

/**
 * Created by Eric on 3/23/2016.
 */
public class Graph1 extends JPanel {
    protected ArrayList<Reading> DataRead = new ArrayList();
    File file;
    JButton jbtn = new JButton("Draw");
    JPanel draw = new JPanel();


    public Graph1(){

        draw.setSize(new Dimension(700, 700));
        setLayout(new MigLayout("", "[]", "[] []"));
        add(jbtn, "wrap,center");
        draw.setBackground(Color.black);
       // add(draw, "grow, push");





        jbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!DataRead.isEmpty()){
                    java.util.List<Integer> scores = new ArrayList<Integer>();
                    //Random random = new Random();
                    int maxDataPoints = DataRead.size();
                    for (int i = 0; i < maxDataPoints ; i++) {
                        scores.add(DataRead.get(i).getAccXValue());
                    }
                    Graph1Draw mainPanel = new Graph1Draw(scores);

                    JScrollPane scroll = new JScrollPane (mainPanel,
                            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                 //   mainPanel.setSize(new Dimension(700,350));


                    add(scroll, "grow, push");
                }


            }
        });





    }

    public void drawLineGraph(Graphics g)
    {
        int i, x1, y1, x2, y2, largestNumber, xIncrement, yIncrement;
        //Compute the x and y increments
        largestNumber = 20;
        xIncrement = 5;

        //Set the initial origin point
        x1 = 0;
        y1 = 0;

        //Compute and plot the data points
        for(i=0; i < 10; i++)
        {
            x2 = 5;
            y2 = 7;
            g.fillOval(x2, y2, 5, 5);
            g.drawLine(x1, y1, x2, y2);
            x1 = x2;
            y1 = y2;

            x2 = x2 +2;
            y2 = y2 +3;
        }
    }

 //   private JPanel createAndShowGui() {
//        java.util.List<Integer> scores = new ArrayList<Integer>();
//        Random random = new Random();
//        int maxDataPoints = 16;
//        int maxScore = 20;
//        for (int i = 0; i < maxDataPoints ; i++) {
//            scores.add(random.nextInt(maxScore));
//        }
//        Graph1Draw mainPanel = new Graph1Draw(scores);
//
//        jpl.add(mainPanel);
//
//        jpl.setVisible(true);
//        jpl
//        return jpl;
  //  }

    public void setData(ArrayList a) {DataRead = a;}
    public void SetFile(File f){
        file = f;
    }

}
