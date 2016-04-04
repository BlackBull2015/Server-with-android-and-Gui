import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

@SuppressWarnings("serial")
public class Graph3Draw extends JPanel {
    private static final Logger log= Logger.getLogger(Graph3Draw.class.getName());
    private static final int maxReading = 200;
    private static final int width = 6000;
    private static final int height = 400;
    private static final int borderGap = 30;
    private static final int pointSize = 4;
    private static final int spacerNumber = 11;
    int[][] DisplayPattern = new int[][]{
            { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 },
            { 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6 },
            { 3, 4, 5, 5, 5, 5, 5, 5, 5, 6, 7 },
            { 2, 3, 4, 5, 5, 5, 5, 5, 6, 7, 8 },
            { 1, 2, 3, 4, 5, 5, 5, 6, 7, 8, 9 },
            { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
    };
    protected ArrayList<Reading> DataRead = new ArrayList();
    ArrayList<Color> allColors;
    public Graph3Draw(ArrayList<Reading> data) {
        this.DataRead = data;
    }

    @Override
    protected void paintComponent(Graphics g) {
        allColors = DeclareAllColors();

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - 2 * borderGap) / (DataRead.size() - 1);
        double yScale = ((double) getHeight() - 2 * borderGap) / (maxReading - 1);

          //System.out.println(xScale + " xcl "+ yScale + " yxcl");

        List<Point> graphPoints = new ArrayList<Point>();
        List<Point> graphPointsY = new ArrayList<Point>();
        List<Point> graphPointsZ = new ArrayList<Point>();
        int xstr,ystr,zstr;
        xstr=ystr=zstr=0;
        for (int i = 0; i < DataRead.size(); i++) {
            int x1 = (int) (i * xScale + borderGap +1);
            int y1 = (int) ( 20 * yScale + borderGap)  ;
            int y1Y = (int) (70 * yScale + borderGap)  ;
            int y1Z = (int) (120 * yScale + borderGap)  ;
            xstr = y1; ystr = y1Y; zstr = y1Z;
            graphPoints.add(new Point(x1, y1));
            graphPointsY.add(new Point(x1, y1Y));
            graphPointsZ.add(new Point(x1, y1Z));
        }
        log.log(Level.INFO,"Points created");

        // create x and y axes
        g2.drawLine(borderGap, getHeight() - borderGap, borderGap, borderGap);
        g2.drawLine(borderGap, getHeight() - borderGap, getWidth() - borderGap, getHeight() - borderGap);

//        int scaleGap = maxReading / spacerNumber;//((spacerNumber-1)/2);

        // create hatch marks for y axis.
        for (int i = 0; i < spacerNumber; i++) {
            int x0 = borderGap;
            int x1 = pointSize + borderGap;
            int y0 = getHeight() - (((i + 1) * (getHeight() - borderGap * 2)) / spacerNumber + borderGap);
            int y1 = y0;
            g2.drawLine(x0, y0, x1, y1);

        }
        g2.drawString("X", 0, xstr);
        g2.drawString("Y", 0, ystr);
        g2.drawString("Z",0,zstr);
        // and for x axis
        for (int i = 0; i < DataRead.size() - 1; i++) {
            int x0 = (i + 1) * (getWidth() - borderGap * 2) / (DataRead.size() - 1) + borderGap;
            int x1 = x0;
            int y0 = getHeight() - borderGap;
            int y1 = y0 - pointSize;
            g2.drawLine(x0, y0, x1, y1);
        }

        log.log(Level.INFO,"Spacers created");
        //Draw legend for graph
        g2.setColor(Color.green);
        g2.fillRect(borderGap + 5, getHeight() - borderGap - 15, 20, 10);
        g2.setColor(Color.black); g2.drawString("Side is up", borderGap + 30, getHeight() - borderGap - 5);

        g2.setColor(Color.red);
        g2.fillRect(borderGap + 5, getHeight() - borderGap - 35, 20, 10);
        g2.setColor(Color.black); g2.drawString("Side is down", borderGap + 30, getHeight() - borderGap - 25);

        g2.setColor(Color.green);
        g2.fillRect(borderGap + 5, getHeight() - borderGap - 50, 20, 5);
        g2.setColor(Color.red);
        g2.fillRect(borderGap + 5, getHeight() - borderGap - 55, 20, 5);
        g2.setColor(Color.black);
        g2.drawString("Z axis Orientation UP", borderGap + 30, getHeight() - borderGap - 45);

        g2.setColor(Color.red);
        g2.fillRect(borderGap + 5, getHeight() - borderGap - 70, 20, 5);
        g2.setColor(Color.green);
        g2.fillRect(borderGap + 5, getHeight() - borderGap - 75, 20, 5);
        g2.setColor(Color.black);
        g2.drawString("Z axis Orientation UPSIDE DOWN", borderGap + 30, getHeight() - borderGap - 65);

        log.log(Level.INFO,"Legend created");


        /************************************************************************************************
         *                  Drawing for X axis
         *************************************************************************************************/
        int patternNum = 0;
        int Xreading;
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x; //- pointSize / 2;
            int y = graphPoints.get(i).y; //- pointSize / 2;;
            int ovalW = (int) (1*xScale) +1;
            int ovalH = pointSize;

            Xreading = DataRead.get(i).getAccXValue();

            patternNum = CalculateTreashold(Xreading);

            if(Xreading >= 0) {

                g2.setColor(allColors.get(DisplayPattern[patternNum][4]));                g2.fillRect(x, y + pointSize, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][3]));                g2.fillRect(x, y + pointSize * 2, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][2]));                g2.fillRect(x, y + pointSize * 3, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][1]));                g2.fillRect(x, y + pointSize * 4, ovalW, ovalH);

                g2.setColor(allColors.get(DisplayPattern[patternNum][0]));                g2.fillRect(x, y + pointSize * 5, ovalW, ovalH);

                g2.setColor(allColors.get(DisplayPattern[patternNum][5]));                g2.fillRect(x, y, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][6]));                g2.fillRect(x, y - pointSize, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][7]));                g2.fillRect(x, y - pointSize * 2, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][8]));                g2.fillRect(x, y - pointSize * 3, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][9]));                g2.fillRect(x, y - pointSize * 4, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][10]));               g2.fillRect(x, y - pointSize * 5, ovalW, ovalH);
            }else
            {
                g2.setColor(allColors.get(DisplayPattern[patternNum][6]));                g2.fillRect(x, y + pointSize, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][7]));                g2.fillRect(x, y + pointSize * 2, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][8]));                g2.fillRect(x, y + pointSize * 3, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][9]));                g2.fillRect(x, y + pointSize * 4, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][10]));                g2.fillRect(x, y + pointSize * 5, ovalW, ovalH);

                g2.setColor(allColors.get(DisplayPattern[patternNum][5]));                g2.fillRect(x, y, ovalW, ovalH);

                g2.setColor(allColors.get(DisplayPattern[patternNum][4]));                g2.fillRect(x, y - pointSize, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][3]));                g2.fillRect(x, y - pointSize * 2, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][2]));                g2.fillRect(x, y - pointSize * 3, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][1]));                g2.fillRect(x, y - pointSize * 4, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][0]));                g2.fillRect(x, y - pointSize * 5, ovalW, ovalH);
            }
        }
        log.log(Level.INFO,"X Axis created");
        /************************************************************************************************
         *                  Drawing for Y axis
         *************************************************************************************************/
        patternNum = 0;
        Xreading = 0;
        for (int i = 0; i < graphPointsY.size(); i++) {
            int x = graphPointsY.get(i).x; //- pointSize / 2;
            int y = graphPointsY.get(i).y; //- pointSize / 2;;
            int ovalW = (int) (1*xScale) +1;
            int ovalH = pointSize;

            Xreading = DataRead.get(i).getAccYValue();

            patternNum = CalculateTreashold(Xreading);

            if(Xreading >= 0) {

                g2.setColor(allColors.get(DisplayPattern[patternNum][4]));                g2.fillRect(x, y + pointSize, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][3]));                g2.fillRect(x, y + pointSize * 2, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][2]));                g2.fillRect(x, y + pointSize * 3, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][1]));                g2.fillRect(x, y + pointSize * 4, ovalW, ovalH);

                g2.setColor(allColors.get(DisplayPattern[patternNum][0]));                g2.fillRect(x, y + pointSize * 5, ovalW, ovalH);

                g2.setColor(allColors.get(DisplayPattern[patternNum][5]));                g2.fillRect(x, y, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][6]));                g2.fillRect(x, y - pointSize, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][7]));                g2.fillRect(x, y - pointSize * 2, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][8]));                g2.fillRect(x, y - pointSize * 3, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][9]));                g2.fillRect(x, y - pointSize * 4, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][10]));               g2.fillRect(x, y - pointSize * 5, ovalW, ovalH);
            }else
            {
                g2.setColor(allColors.get(DisplayPattern[patternNum][6]));                g2.fillRect(x, y + pointSize, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][7]));                g2.fillRect(x, y + pointSize * 2, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][8]));                g2.fillRect(x, y + pointSize * 3, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][9]));                g2.fillRect(x, y + pointSize * 4, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][10]));                g2.fillRect(x, y + pointSize * 5, ovalW, ovalH);

                g2.setColor(allColors.get(DisplayPattern[patternNum][5]));                g2.fillRect(x, y, ovalW, ovalH);

                g2.setColor(allColors.get(DisplayPattern[patternNum][4]));                g2.fillRect(x, y - pointSize, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][3]));                g2.fillRect(x, y - pointSize * 2, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][2]));                g2.fillRect(x, y - pointSize * 3, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][1]));                g2.fillRect(x, y - pointSize * 4, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][0]));                g2.fillRect(x, y - pointSize * 5, ovalW, ovalH);
            }
        }
        log.log(Level.INFO,"Y axis created");
        /************************************************************************************************
         *                  Drawing for Z axis
         *************************************************************************************************/
        patternNum = 0;
        Xreading = 0;
        for (int i = 0; i < graphPointsZ.size(); i++) {
            int x = graphPointsZ.get(i).x; //- pointSize / 2;
            int y = graphPointsZ.get(i).y; //- pointSize / 2;;
            int ovalW = (int) (1*xScale) +1;
            int ovalH = pointSize;

            Xreading = DataRead.get(i).getAccZValue();

            patternNum = CalculateTreashold(Xreading);

            if(Xreading >= 0) {

                g2.setColor(allColors.get(DisplayPattern[patternNum][4]));                g2.fillRect(x, y + pointSize, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][3]));                g2.fillRect(x, y + pointSize * 2, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][2]));                g2.fillRect(x, y + pointSize * 3, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][1]));                g2.fillRect(x, y + pointSize * 4, ovalW, ovalH);

                g2.setColor(allColors.get(DisplayPattern[patternNum][0]));                g2.fillRect(x, y + pointSize * 5, ovalW, ovalH);

                g2.setColor(allColors.get(DisplayPattern[patternNum][5]));                g2.fillRect(x, y, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][6]));                g2.fillRect(x, y - pointSize, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][7]));                g2.fillRect(x, y - pointSize * 2, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][8]));                g2.fillRect(x, y - pointSize * 3, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][9]));                g2.fillRect(x, y - pointSize * 4, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][10]));               g2.fillRect(x, y - pointSize * 5, ovalW, ovalH);
            }else
            {
                g2.setColor(allColors.get(DisplayPattern[patternNum][6]));                g2.fillRect(x, y + pointSize, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][7]));                g2.fillRect(x, y + pointSize * 2, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][8]));                g2.fillRect(x, y + pointSize * 3, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][9]));                g2.fillRect(x, y + pointSize * 4, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][10]));                g2.fillRect(x, y + pointSize * 5, ovalW, ovalH);

                g2.setColor(allColors.get(DisplayPattern[patternNum][5]));                g2.fillRect(x, y, ovalW, ovalH);

                g2.setColor(allColors.get(DisplayPattern[patternNum][4]));                g2.fillRect(x, y - pointSize, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][3]));                g2.fillRect(x, y - pointSize * 2, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][2]));                g2.fillRect(x, y - pointSize * 3, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][1]));                g2.fillRect(x, y - pointSize * 4, ovalW, ovalH);
                g2.setColor(allColors.get(DisplayPattern[patternNum][0]));                g2.fillRect(x, y - pointSize * 5, ovalW, ovalH);
            }
        }
        log.log(Level.INFO,"Z axis created");
    }



    public int CalculateTreashold(int num){
        int number = Math.abs(num);
        if(number > 1200)
            return 5;
        else if(number >  600)
            return 4;
        else if(number > 300)
            return 3;
        else if(number > 150)
            return 2;
        else if(number > 50)
            return 1;
        else
            return 0;
            /*
        Drawing patterns
1 = Y,Y,Y,Y,Y,Y,Y,Y,Y,Y;
2 = g1,Y,Y,Y,Y,Y,Y,Y,Y,r1;
3 = g2,g1,Y,Y,Y,Y,Y,Y,r1,r2;
4 = g3,g2,g1,Y,Y,Y,Y,r1,r2,r3;
5 = g4,g3,g2,g1,Y,Y,r1,r2,r3,r4;
6 = g5,g4,g3,g2,g1,r1,r2,r3,r4,r5;
 */
    }


    public ArrayList<Color> DeclareAllColors(){
        ArrayList<Color> cls = new ArrayList<>();

        cls.add(new Color(5, 255, 0));
        cls.add(new Color(55, 255, 0));
        cls.add(new Color(105, 255, 0));
        cls.add(new Color(155, 255, 0));
        cls.add(new Color(205, 255, 0));
        cls.add(new Color(255, 255, 0));
        cls.add(new Color(255, 205, 0));
        cls.add(new Color(255, 155, 0));
        cls.add(new Color(255, 105, 0));
        cls.add(new Color(255, 55, 0));
        cls.add(new Color(255, 5, 0));
     /* Color RGB settings
        5	255	0
        55	255	0
        105	255	0
        155	255	0
        205	255	0
        255	255	0
        255	205	0
        255	155	0
        255	105	0
        255	55	0
        255	5	0*/
        return cls;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
}