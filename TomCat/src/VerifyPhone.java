import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by Eric on 4/10/2016.
 */
@WebServlet("/VerifyPhone")
public class VerifyPhone extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        System.out.println(request.getParameter("login"));

        String inputString = request.getParameter("login");
        PrintWriter out = response.getWriter();

        //Pre-Setup database
        PreparedStatement sqlUpdate;

        try {
            JSONObject json = new JSONObject(inputString);

            String login = json.getString("login");
            String pass = json.getString("pass");

            // load database driver class
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/readings", "root", "root");

                Statement statement = connection.createStatement();
                ResultSet resultSet =statement.executeQuery("SELECT * FROM Users WHERE login='" + login + "' AND password='" + pass + "';");

            if(resultSet.next()){
                out.print("true");
            }else{
                out.print("false");
            }

        }catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }


}
