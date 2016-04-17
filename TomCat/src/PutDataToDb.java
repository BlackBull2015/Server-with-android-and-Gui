import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import javax.xml.crypto.Data;

@WebServlet("/PutDataToDb")
public class PutDataToDb extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PreparedStatement sqlUpdate;
        System.out.println("Prepared");
        try{
        // load database driver class
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("driver");
            // connect to database
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/readings", "root", "root");
            System.out.println("Hots");
            sqlUpdate = connection.prepareStatement(
                    "INSERT INTO data ( firstName, lastName ) " +
                            "VALUES ( ? ,  ?)" );
            System.out.println("Pre stat");
            int result;
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");


            for(int i = 0; i < 300; i++) {
                System.out.print("Start"+ sdf.format(cal.getTime()) );
                sqlUpdate.setString(1, "number: " + i);
                sqlUpdate.setString(2, "Surname: " + i);
                System.out.println(" End: "+sdf.format(cal.getTime()));
                result = sqlUpdate.executeUpdate();
                // if insert fails, rollback and discontinue
                if (result == 0) {
                    connection.rollback(); // rollback insert
                    System.out.println("did rallack");
                }
            }


        // close statement and connection
        connection.close();
        System.out.println("Database saved");
    } catch (Exception ee) {
            System.out.println("Failed");

    }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
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
}


