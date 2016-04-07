import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
        String Type = request.getParameter("Type");
        String login = request.getParameter("Username");
        String pass = request.getParameter("Password");
        PrintWriter out = response.getWriter();

        //Pre-Setup database
        PreparedStatement sqlUpdate;
        System.out.println("Prepared");
        try {
            // load database driver class
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/readings", "root", "root");
            sqlUpdate = connection.prepareStatement("INSERT INTO users ( login, password ) " +"VALUES ( ? ,  ?)");


            if(Type.equalsIgnoreCase("Login")){
                Statement statement = connection.createStatement();
                ResultSet resultSet =statement.executeQuery("SELECT * FROM Users WHERE login='"+login+"' AND password='"+pass+"';");


                if(resultSet.next()){
                    getServletContext().getRequestDispatcher("/SelectGraph.html").forward(request, response);
                }else
                    getServletContext().getRequestDispatcher("/NoUser.html").forward(request, response);



            }else if(Type.equalsIgnoreCase("Register")){
                int result;
                sqlUpdate.setString(1,login);
                sqlUpdate.setString(2,pass);
                result = sqlUpdate.executeUpdate();
                // if insert fails, rollback and discontinue
                if (result == 0) {
                    connection.rollback(); // rollback insert
                    out.println("Registering in");

                }
                getServletContext().getRequestDispatcher("/LoginAfterReg.html").forward(request, response);

            } else {
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
