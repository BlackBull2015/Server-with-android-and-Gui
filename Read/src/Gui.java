import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Gui extends JPanel {

    File fl;

    private static String OpenF = "OpenFile";
    private static String truefalse = "True/False";
    private static String mcb = "Multple Choice Basic";

    private JMenuBar jcomp1;

    public Gui() {





        //construct components
        JTabbedPane tabs = new JTabbedPane();
        //Setting layout to Mig Layout
        setLayout(new MigLayout("", "[]","[] []"));

        //construct preComponents of File menu
        JMenu fileMenu = new JMenu ("File");
        JMenuItem q1 = new JMenuItem (OpenF);
        JMenuItem q2 = new JMenuItem ("Reload File");
        JMenuItem q3 = new JMenuItem ("Exit");

        PrintVals tab1 = new PrintVals();
        Graph1 tab2 = new Graph1();
        Graph2 tab3 = new Graph2();

        //Action listiner for each menu bar
       q1.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               JFileChooser jfc = new JFileChooser();
               jfc.showOpenDialog(jfc);
               fl = jfc.getSelectedFile();
               tab1.SetFile(fl);
               tab2.SetFile(fl);
               tab3.SetFile(fl);

               tabs.setSelectedIndex(0);
           }
       });
        q2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabs.setSelectedIndex(1);
            }
        });
        q3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabs.setSelectedIndex(2);
            }
        });

        //Adding components into File Menu
        fileMenu.add(q1);
        fileMenu.add(q2);
        fileMenu.add(q3);

        //tabs.addTab(OpenF, new fill_Blanks_Tab());
        tabs.addTab("Read Values", tab1);
        tabs.addTab("Draw Graph", tab2);
        tabs.addTab("Terrain", tab3);

        //construct components
        jcomp1 = new JMenuBar();
        jcomp1.add(fileMenu);
        //Adding components to panel
        add(jcomp1," growx,wrap,top");
        add(tabs,"grow,push");
    }








    public static void main (String[] args) {
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch(Exception e){
            e.printStackTrace();
        }
        JFrame frame = new JFrame ("MyPanel");
        frame.setLayout(new MigLayout("", "[] "));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Gui(), "push,grow ");
        frame.pack();
        frame.setSize(new Dimension(700,700));
        frame.setVisible (true);
    }
}
