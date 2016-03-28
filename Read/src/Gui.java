import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Gui extends JPanel {
   // protected ArrayList<Reading> Data = new ArrayList();
   protected ArrayList<Reading> DataReadMain = new ArrayList();

    File fl;

    private static String OpenF = "OpenFile";
    private static String Craft = "CreatePoints";
    private static String exit = "Exit";

    private JMenuBar jcomp1;

    public Gui() {





        //construct components
        JTabbedPane tabs = new JTabbedPane();
        //Setting layout to Mig Layout
        setLayout(new MigLayout("", "[]","[] []"));

        //construct preComponents of File menu
        JMenu fileMenu = new JMenu ("File");
        JMenuItem q1 = new JMenuItem (OpenF);
        JMenuItem q2 = new JMenuItem (Craft);
        JMenuItem q3 = new JMenuItem (exit);

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

              //  DataReadMain = tab1.DataRead;
                try {
                    DataReadMain = CreateData(fl);
                    tab1.setData(DataReadMain);
                    tab2.setData(DataReadMain);
                    tab3.setData(DataReadMain);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                //Test Code
//                if(!DataReadMain.isEmpty()) {
//                    int size, count = 0;
//                    size = DataReadMain.size();
//                    System.out.println("Size is: "+size);
//                    while (count < size) {
//                        System.out.println(DataReadMain.get(count) + "countis: "+count);
//                        count++;
//                    }
//                }
             //   tabs.setSelectedIndex(1);
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

    public ArrayList CreateData(File f) throws IOException {

        ArrayList<Reading> DataRead = new ArrayList<>();
        String str;
        BufferedReader br = new BufferedReader(new FileReader(f));
        String[] Informaions;

        while((str = br.readLine()) != null) {
            if (str.length() > 0) {
                Reading Rd = new Reading();
                Informaions = str.split(" ");
                Rd.setAccXValue(Integer.parseInt(Informaions[0]));
                Rd.setAccYValue(Integer.parseInt(Informaions[1]));
                Rd.setAccZValue(Integer.parseInt(Informaions[2]));

                Rd.setMagXValue(Integer.parseInt(Informaions[3]));
                Rd.setMagYValue(Integer.parseInt(Informaions[4]));
                Rd.setMagZValue(Integer.parseInt(Informaions[5]));

                Rd.setTemperature(Integer.parseInt(Informaions[6]));

                Rd.setTime("N/A");

                DataRead.add(Rd);
            }
        }

        if(!DataRead.isEmpty()){
            return DataRead;
        }else
            return null;
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
