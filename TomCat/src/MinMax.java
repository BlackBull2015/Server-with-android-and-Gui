import org.json.JSONObject;

import java.io.*;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/MinMax")
public class MinMax extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PreparedStatement sqlUpdate;
        String sts = "";
        String inputString = request.getParameter("data");
        try {


            System.out.println("String of creation is: "+inputString );
        //    System.out.println("\nPrepering to Create JSON object");
            JSONObject inputValues = new JSONObject(inputString);
            System.out.println("JSON object was created succesfull");


            String[] array = new String[12];

             array[0]= inputValues.getString("x");
             array[1] = inputValues.getString("y");
             array[2]= inputValues.getString("z");
             array[3]= inputValues.getString("mx");
             array[4]= inputValues.getString("my");
             array[5]= inputValues.getString("mz");
             array[6]= inputValues.getString("tmp");
            //***********************************************************//

//            System.out.print(array[0]+ " ");
//            System.out.print(array[1]+ " ");
//            System.out.print(array[2]+ " ");
//            System.out.print(array[3]+ " ");
//            System.out.print(array[4]+ " ");
//            System.out.print(array[5]+ " ");
//            System.out.println(array[6]+ " ");
            //***********************************************************//
            String StrToDb = "";
            try{
                File fl = new File("C:/temp/base.txt");
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fl,true)));
               String time;
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd-HH:mm:ss");
                time = sdf.format(cal.getTime());


                out.print(array[0]+ " ");
                StrToDb+=array[0]+ " ";
                out.print(array[1]+ " ");
                StrToDb+=array[0]+ " ";
                out.print(array[2]+ " ");
                StrToDb+=array[0]+ " ";
                out.print(array[3]+ " ");
                StrToDb+=array[0]+ " ";
                out.print(array[4]+ " ");
                StrToDb+=array[0]+ " ";
                out.print(array[5]+ " ");
                StrToDb+=array[0]+ " ";
                out.print(array[6]+ " ");
                StrToDb+=array[0]+ " ";
                out.println(time+ " ");
                StrToDb+=time+ " ";
                out.close();

            }catch (IOException e) {
                System.out.println("couldnt write to file");
                //exception handling left as an exercise for the reader
            }
            //***********************************************************//

            try {
                // load database driver class
                Class.forName("com.mysql.jdbc.Driver");

                // connect to database
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/readings", "root", "root");

                sqlUpdate = connection.prepareStatement(
                        "INSERT INTO datareadings ( accX, accY, accZ, magX, magY, magZ, tmp, time ) " +
                                "VALUES ( ? ,  ? , ? , ? , ? ,? ,? ,?)" );
                int result;
                String[] IntoSql;
                    IntoSql = StrToDb.split(" ");
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
                // close statement and connection
                connection.close();
                System.out.println("Database saved");
                }catch (Exception ee) {
                System.out.println("Unable to saved in database");
                }

        } catch (Exception e) {
            System.out.println("Servlet died");
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        out.println(sts);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
