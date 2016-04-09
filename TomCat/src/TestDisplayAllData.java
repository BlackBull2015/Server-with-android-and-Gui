import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import javax.xml.crypto.Data;

@WebServlet("/TestDisplayAllData")
public class TestDisplayAllData extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        //PreSetup
        String inputString = request.getParameter("login");
        String Type = request.getParameter("Type");
        String login = request.getParameter("Username");
        String pass = request.getParameter("Password");
        PrintWriter out = response.getWriter();

        //Pre-Setup database
        PreparedStatement sqlUpdate;

        try {
            // load database driver class
                      Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/readings", "root", "root");
            sqlUpdate = connection.prepareStatement("INSERT INTO users ( login, password,verification ) " +"VALUES ( ? ,  ?,?)");





            if(Type.equalsIgnoreCase("Login")){
                Statement statement = connection.createStatement();
                ResultSet resultSet =statement.executeQuery("SELECT * FROM Users WHERE login='"+login+"' AND password='"+pass+"';");


                if(resultSet.next()){
                    System.out.println(request.getParameter("1"));
                    out.println("<!DOCTYPE html>\n" +
                            "<html >\n" +
                            "<head>\n" +
                            "    <meta charset=\"UTF-8\">\n" +
                            "    <title>Select Graph</title>\n" +
                            "    <link rel=\"stylesheet\" href=\"css/style.css\">\n" +
                            "</head>\n" +
                            "\n" +
                            "<body>\n" +
                            "\n" +
                            "<div class=\"vid-container\">\n" +
                            "    <video id=\"Video1\" class=\"bgvid back\" autoplay=\"false\" muted=\"muted\" preload=\"auto\" loop>\n" +
                            "        <source src=\"http://shortcodelic1.manuelmasiacsasi.netdna-cdn.com/themes/geode/wp-content/uploads/2014/04/milky-way-river-1280hd.mp4.mp4\" type=\"video/mp4\">\n" +
                            "    </video>\n" +
                            "    <div class=\"inner-container\">\n" +
                            "        </div>\n" +
                            "        <div class=\"box\">\n" +
                            "            <h1>Accelerometer Graph</h1>\n" +
                            "            <form action=\"DisplayAcc\" method=\"post\">\n" +
                            "                <input type=\"image\" src=\"image/graph.png\" style=\"width:200px;height:150px\"; value=\"submit\"/>\n" +
                            "                <input type=\"hidden\" name=\"Ver\" value=\"" + resultSet.getString(4) + "\"/>\n" +
                            "            </form>\n" +
                            "            <h1>Magnetometer Graph</h1>\n" +
                            "            <form action=\"DisplayMag\" method=\"post\" >\n" +
                            "                <input type=\"image\" src=\"image/graph.png\" style=\"width:200px;height:150px\"; value=\"submit\"/>\n" +
                            "                <input type=\"hidden\" name=\"Ver\" value=\"" + resultSet.getString(4) + "\"/>\n" +
                            "            </form>\n" +
                            "        </div>\n" +
                            "\n" +
                            "    </div>\n" +
                            "\n" +
                            "</div>\n" +
                            "<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>\n" +
                            "\n" +
                            "<script src=\"js/index.js\"></script>\n" +
                            "</body>\n" +
                            "</html>\n" +
                            "\n");
                }else
                    getServletContext().getRequestDispatcher("/NoUser.html").forward(request, response);



            }else if(Type.equalsIgnoreCase("Register")){
                System.out.println(request.getParameter("2"));
                int result;
                sqlUpdate.setString(1,login);
                sqlUpdate.setString(2,pass);

                Random rand = new Random();
                String VerificationString= Long.toString(rand.nextLong() >>> 1);
                sqlUpdate.setString(3,VerificationString);
                result = sqlUpdate.executeUpdate();
                // if insert fails, rollback and discontinue
                if (result == 0) {
                    connection.rollback(); // rollback insert
                    out.println("Registering in");

                }
                getServletContext().getRequestDispatcher("/LoginAfterReg.html").forward(request, response);

            }else {
                out.println("Invalid response");
            }


        }catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }




    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
