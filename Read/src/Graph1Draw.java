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
import javax.swing.*;

@SuppressWarnings("serial")
public class Graph1Draw extends JPanel {
    private static final int maxReading = 8000;
    private static final int width = 6000;
    private static final int height = 400;
    private static final int borderGap = 30;
    private static final Color XgraphColour = Color.green;
    private static final Color YgraphColour = Color.red;
    private static final Color ZgraphColour = Color.blue;
    private static final Color GRAPH_POINT_COLOR = new Color(150, 50, 50, 180);
    private static final Stroke stroke = new BasicStroke(2f);
    private static final int pointSize = 5;
    private static final int spacerNumber = 11;
    private static final int bringToPositive = 4000;
    private List<Integer> scores;
    private List<Integer> scoresY;
    private List<Integer> scoresZ;

    public Graph1Draw(List<Integer> scores, List<Integer> scoresY, List<Integer> scoresZ) {
        this.scores = scores;
        this.scoresY = scoresY;
        this.scoresZ = scoresZ;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - 2 * borderGap) / (scores.size() - 1);
        double yScale = ((double) getHeight() - 2 * borderGap) / (maxReading - 1);

      //  System.out.println(xScale + " xcl "+ yScale + " yxcl");

        List<Point> graphPoints = new ArrayList<Point>();
        List<Point> graphPointsY = new ArrayList<Point>();
        List<Point> graphPointsZ = new ArrayList<Point>();
        for (int i = 0; i < scores.size(); i++) {
            int x1 = (int) (i * xScale + borderGap);
            int y1 = (int) ((maxReading - scores.get(i)) * yScale + borderGap)  ;
            int y1Y = (int) ((maxReading - scoresY.get(i)) * yScale + borderGap)  ;
            int y1Z = (int) ((maxReading - scoresZ.get(i)) * yScale + borderGap)  ;
            graphPoints.add(new Point(x1, y1- (int) (bringToPositive*yScale)));
            graphPointsY.add(new Point(x1, y1Y- (int) (bringToPositive*yScale)));
            graphPointsZ.add(new Point(x1, y1Z- (int) (bringToPositive*yScale)));
        }

        // create x and y axes
        g2.drawLine(borderGap, getHeight() - borderGap, borderGap, borderGap);
        g2.drawLine(borderGap, getHeight() - borderGap, getWidth() - borderGap, getHeight() - borderGap);

        int scaleGap = maxReading / spacerNumber;//((spacerNumber-1)/2);
        System.out.println(scaleGap);
        // create hatch marks for y axis.
        for (int i = 0; i < spacerNumber; i++) {
            int x0 = borderGap;
            int x1 = pointSize + borderGap;
            int y0 = getHeight() - (((i + 1) * (getHeight() - borderGap * 2)) / spacerNumber + borderGap);
            int y1 = y0;
            g2.drawLine(x0, y0, x1, y1);
            if(i < 5)
                g2.drawString(""+(bringToPositive - (scaleGap*(spacerNumber-i))),x0-borderGap,y0);
            else if(i == 5)
                g2.drawString("0",x0-borderGap,y0);
            else if (i > 5)
                g2.drawString(""+(bringToPositive - (scaleGap*(spacerNumber-i-1))),x0-borderGap,y0);
        }

        // and for x axis
        for (int i = 0; i < scores.size() - 1; i++) {
            int x0 = (i + 1) * (getWidth() - borderGap * 2) / (scores.size() - 1) + borderGap;
            int x1 = x0;
            int y0 = getHeight() - borderGap;
            int y1 = y0 - pointSize;
            g2.drawLine(x0, y0, x1, y1);
        }
        //Draw legend for graph
        g2.setColor(ZgraphColour);
        g2.fillRect(borderGap + 5, getHeight() - borderGap - 15, 20, 10);
        g2.drawString("Accelerometer Z reading", borderGap + 30, getHeight() - borderGap -5);

        g2.setColor(YgraphColour);
        g2.fillRect(borderGap + 5, getHeight() - borderGap - 35, 20, 10);
        g2.drawString("Accelerometer Y reading", borderGap + 30, getHeight() - borderGap - 25);

        g2.setColor(XgraphColour);
        g2.fillRect(borderGap + 5, getHeight()-borderGap-55,20,10);
        g2.drawString("Accelerometer X reading", borderGap + 30, getHeight()-borderGap-45);


        //Draw lines for X axis
        Stroke oldStroke = g2.getStroke();
        g2.setColor(XgraphColour);
        g2.setStroke(stroke);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        //Draw lines for Y axis
        g2.setColor(YgraphColour);
        for (int i = 0; i < graphPointsY.size() - 1; i++) {
            int x1 = graphPointsY.get(i).x;
            int y1 = graphPointsY.get(i).y;
            int x2 = graphPointsY.get(i + 1).x;
            int y2 = graphPointsY.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        //Draw lines for Z axis
        g2.setColor(ZgraphColour);
        for (int i = 0; i < graphPointsZ.size() - 1; i++) {
            int x1 = graphPointsZ.get(i).x;
            int y1 = graphPointsZ.get(i).y;
            int x2 = graphPointsZ.get(i + 1).x;
            int y2 = graphPointsZ.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }


//        g2.setStroke(oldStroke);
//        g2.setColor(GRAPH_POINT_COLOR);
//        for (int i = 0; i < graphPoints.size(); i++) {
//            int x = graphPoints.get(i).x - pointSize / 2;
//            int y = graphPoints.get(i).y - pointSize / 2;;
//            int ovalW = pointSize;
//            int ovalH = pointSize;
//            g2.fillOval(x, y, ovalW, ovalH);
//        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
//
//    private static void createAndShowGui() {
//        List<Integer> scores = new ArrayList<Integer>();
//        Random random = new Random();
//        int maxDataPoints = 16;
//        int maxScore = 20;
//        for (int i = 0; i < maxDataPoints ; i++) {
//            scores.add(random.nextInt(maxScore));
//        }
//        Graph1Draw mainPanel = new Graph1Draw(scores);
//
//        JFrame frame = new JFrame("DrawGraph");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add(mainPanel);
//        frame.pack();
//        frame.setLocationByPlatform(true);
//        frame.setVisible(true);
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGui();
//            }
//        });
//    }
}