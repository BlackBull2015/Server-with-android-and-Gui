import net.miginfocom.swing.MigLayout;

import javax.sound.midi.MidiDevice;
import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gui extends JPanel {
    private static final Logger log= Logger.getLogger(Gui.class.getName());
    private PreparedStatement sqlUpdate;
   // protected ArrayList<Reading> Data = new ArrayList();
   protected ArrayList<Reading> DataReadMain = new ArrayList();

    File fl;

    private static String OpenF = "OpenFile";
    private static String Craft = "CreatePoints";
    private static String ReadFromDb = "Database Read";
    private static String SaveInDb = "Database Save";
    private static String exit = "Exit";
    private static String ClearDb = "ClearDb";
    private static String ClearFl = "ClearFl";

    private JMenuBar jcomp1;

    public Gui() {

        //construct components
        JTabbedPane tabs = new JTabbedPane();
        //Setting layout to Mig Layout
        setLayout(new MigLayout("", "[]","[] []"));

        //construct preComponents of File menu
        JMenu fileMenu = new JMenu ("File");
        JMenu Database = new JMenu("Operate");

        JMenuItem q1 = new JMenuItem (OpenF);
        JMenuItem q2 = new JMenuItem (Craft);
        JMenuItem q4 = new JMenuItem(ReadFromDb);
        JMenuItem q5 = new JMenuItem(SaveInDb);
        JMenuItem q3 = new JMenuItem (exit);
        JMenuItem d1 = new JMenuItem (ClearDb);
        JMenuItem d2 = new JMenuItem (ClearFl);
        log.log(Level.INFO,"Menu created");

        PrintVals tab1 = new PrintVals();
        Graph1 tab2 = new Graph1();
        Graph2 tab3 = new Graph2();
        Graph3 tab4 = new Graph3();
        log.log(Level.INFO,"Tabs Created");

        //Action listiner for each menu bar
        //Select file
        q1.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               JFileChooser jfc = new JFileChooser();
               jfc.showOpenDialog(jfc);
               fl = jfc.getSelectedFile();
               log.log(Level.INFO, "File Opened");
               tab1.SetFile(fl);
               tab2.SetFile(fl);
               tab3.SetFile(fl);
               tab4.SetFile(fl);
               log.log(Level.INFO, "File shared to others");
             //  tabs.setSelectedIndex(0);

           }
       });
        //Create data from points
        q2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

              //  DataReadMain = tab1.DataRead;
                try {
                    DataReadMain = CreateData(fl);
                    log.log(Level.INFO,"Points Created");
                    tab1.setData(DataReadMain);
                    tab2.setData(DataReadMain);
                    tab3.setData(DataReadMain);
                    tab4.setData(DataReadMain);
                    log.log(Level.INFO, "Data shared");
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
        //Exits program
        q3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log.log(Level.INFO,"Good Bye");
                System.exit(1);
            }
        });
        //Create data points from Database
        q4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreatePointsFromDb();
                log.log(Level.INFO, "File created from database");
                tab1.setData(DataReadMain);
                tab2.setData(DataReadMain);
                tab3.setData(DataReadMain);
                tab4.setData(DataReadMain);
                log.log(Level.INFO, "points shared with others");
            }
        });
        //Saves all points in the database
        q5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveInDb();

                log.log(Level.INFO, "Saved in database");
            }
        });
        //Resets all database
        d1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResetDatabase();
                log.log(Level.INFO, "Database Restarted");
            }
        });
        //Clear the selected file
        d2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fl!= null){
                    try {
                        ResetDataFile(fl);
                        log.log(Level.INFO, "File restarted");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        //Adding components into File Menu
        fileMenu.add(q1);
        fileMenu.add(q2);
        fileMenu.add(q5);
        fileMenu.add(q4);
        fileMenu.add(q3);

        Database.add(d1);
        Database.add(d2);
        log.log(Level.INFO, "Menu items added");
        //tabs.addTab(OpenF, new fill_Blanks_Tab());
        tabs.addTab("Read Values", tab1);
        tabs.addTab("Accu Graph", tab2);
        tabs.addTab("Mag Graph", tab3);
        tabs.addTab("Terrain", tab4);
        log.log(Level.INFO, "Tabs Added");

        //construct components
        jcomp1 = new JMenuBar();
        jcomp1.add(fileMenu);
        jcomp1.add(Database);
        //Adding components to panel
        add(jcomp1," growx,wrap,top");
        add(tabs,"grow,push");
        log.log(Level.INFO, "Jpanel created");
    }

    protected ArrayList CreateData(File f) throws IOException {

        ArrayList<Reading> DataRead = new ArrayList<>();
        String str;
        BufferedReader br = new BufferedReader(new FileReader(f));
        String[] Informaions;

        while((str = br.readLine()) != null) {
            if (str.length() > 0) {
                Reading Rd = new Reading();
                try {
                    Informaions = str.split(" ");
                    Rd.setAccXValue(Integer.parseInt(Informaions[0]));
                    Rd.setAccYValue(Integer.parseInt(Informaions[1]));
                    Rd.setAccZValue(Integer.parseInt(Informaions[2]));

                    Rd.setMagXValue(Integer.parseInt(Informaions[3]));
                    Rd.setMagYValue(Integer.parseInt(Informaions[4]));
                    Rd.setMagZValue(Integer.parseInt(Informaions[5]));

                    Rd.setTemperature(Integer.parseInt(Informaions[6]));

                    System.out.println(Informaions.length);

                    if(Informaions.length > 7)
                      Rd.setTime(Informaions[7]);
                    else
                      Rd.setTime("N/A");

                    DataRead.add(Rd);
                }catch (Exception e){
                    System.out.println("Line not compatible");
                }


            }
        }

        if(!DataRead.isEmpty()){
            return DataRead;
        }else
            return null;
    }

    private Reading CreatePoint (String str) throws IOException {

        String[] Informaions;
        Reading Rd = new Reading();

            if (str.length() > 0) {

                try {
                    Informaions = str.split(" ");
                    Rd.setAccXValue(Integer.parseInt(Informaions[0]));
                    Rd.setAccYValue(Integer.parseInt(Informaions[1]));
                    Rd.setAccZValue(Integer.parseInt(Informaions[2]));

                    Rd.setMagXValue(Integer.parseInt(Informaions[3]));
                    Rd.setMagYValue(Integer.parseInt(Informaions[4]));
                    Rd.setMagZValue(Integer.parseInt(Informaions[5]));

                    Rd.setTemperature(Integer.parseInt(Informaions[6]));

                    if(Informaions.length > 6)
                        Rd.setTime(Informaions[7]);
                    else
                        Rd.setTime("N/A");

                    Rd.computeHeading();

                }catch (Exception e){
                    System.out.println("Failure in String structure");
                }


            }
        return Rd;
        }

    private void saveInDb(){

        new Thread(){
            public void run(){
                try {
                    // load database driver class
                    Class.forName("com.mysql.jdbc.Driver");

                    // connect to database
                    Connection connection = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/readings", "root", "root");

                    sqlUpdate = connection.prepareStatement(
                            "INSERT INTO datareadings ( accX, accY, accZ, magX, magY, magZ, tmp, time ) " +
                                    "VALUES ( ? ,  ? , ? , ? , ? ,? ,? ,?)" );

                    int size, count = 0, result;
                    String temp;
                    String[] IntoSql;
                    size = DataReadMain.size();
                    System.out.println("Size is inside database: " + size);
                    while (count < size) {
                        temp = DataReadMain.get(count).toStringDb();
                        //  System.out.println(temp);
                        IntoSql = temp.split(" ");
                        sqlUpdate.clearParameters();
                        sqlUpdate.setInt(1, Integer.parseInt(IntoSql[0]));
                        sqlUpdate.setInt(2, Integer.parseInt(IntoSql[1]));
                        sqlUpdate.setInt(3, Integer.parseInt(IntoSql[2]));
                        sqlUpdate.setInt(4, Integer.parseInt(IntoSql[3]));
                        sqlUpdate.setInt(5, Integer.parseInt(IntoSql[4]));
                        sqlUpdate.setInt(6, Integer.parseInt(IntoSql[5]));
                        sqlUpdate.setInt(7, Integer.parseInt(IntoSql[6]));
                        sqlUpdate.setString(8, IntoSql[7]);
                        result = sqlUpdate.executeUpdate();
                        // if insert fails, rollback and discontinue
                        if ( result == 0 ) {
                            connection.rollback(); // rollback insert
                            System.out.println("did rallack");
                        }

                        count++;
                    }
                    // close statement and connection
                    connection.close();
                    System.out.println("Database saved");
                } catch (Exception ee) {

                }
            }
        }.start();

    }

    private void CreatePointsFromDb(){
        try {

            if (DataReadMain.isEmpty()){

                // load database driver class
                Class.forName("com.mysql.jdbc.Driver");

                // connect to database
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/readings", "root", "root");

                // create Statement to query database
                Statement statement = connection.createStatement();

                // query database
                ResultSet resultSet =
                        statement.executeQuery("SELECT * FROM datareadings");

                // process query results
                StringBuffer results = new StringBuffer();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int numberOfColumns = metaData.getColumnCount();
                String ReadingFromDb = "";
                for (int i = 1; i <= numberOfColumns; i++) {
                    //  results.append(metaData.getColumnName(i)
                    //        + "\t");
                }

                //results.append("\n");

                while (resultSet.next()) {

                    for (int i = 2; i <= numberOfColumns; i++) {
                        ReadingFromDb+=resultSet.getObject(i)+" ";
                        //results.append(resultSet.getObject(i));
                    }
                    System.out.println(ReadingFromDb);
                    DataReadMain.add(CreatePoint(ReadingFromDb));
                    ReadingFromDb = "";
                    // results.append("\n");
                }
                System.out.println("Operation done");
                // close statement and connection
                statement.close();
                connection.close();
            }
        } catch (Exception ee) {

        }
    }

    private void ResetDatabase(){

        try {

                // load database driver class
                Class.forName("com.mysql.jdbc.Driver");

                // connect to database
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/readings", "root", "root");

                // create Statement to query database
                Statement statement = connection.createStatement();

                // query database
            statement.executeUpdate("drop TABLE IF EXISTS datareadings ");
                    //    statement.executeQuery("drop TABLE datareadings");

                        statement.executeUpdate("create table datareadings (\n" +
                                "  tableID int  AUTO_INCREMENT,\n" +
                                "  accX int (10) NOT NULL,\n" +
                                "  accY int (10) NOT NULL,\n" +
                                "  accZ int (10) NOT NULL,\n" +
                                "  magX int (10) NOT NULL,\n" +
                                "  magY int (10) NOT NULL,\n" +
                                "  magZ int (10) NOT NULL,\n" +
                                "  tmp int (10) NOT NULL,\n" +
                                "  time VARCHAR (50) NOT NULL,\n" +
                                "\n" +
                                "  constraint pk_table primary key (tableID)\n" +
                                ")");

                System.out.println("Database Reseted");
                // close statement and connection
                statement.close();
                connection.close();
        } catch (Exception ee) {
            System.out.println("Failure");
        }
    }

    private void ResetDataFile(File f) throws IOException {

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fl,false)));
        out.print("");
        out.flush();
        out.close();
    }


    public static void main (String[] args) {
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch(Exception e){
            e.printStackTrace();
        }
        log.log(Level.INFO,"Starting Program");
        JFrame frame = new JFrame ("MyPanel");
        frame.setLayout(new MigLayout("", "[] "));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Gui(), "push,grow ");
        frame.pack();
        frame.setSize(new Dimension(700, 700));
        frame.setVisible(true);
        log.log(Level.INFO, "Program running");
    }
}
