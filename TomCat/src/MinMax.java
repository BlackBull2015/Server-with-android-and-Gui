import org.json.JSONObject;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/MinMax")
public class MinMax extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sts = "";
        String inputString = request.getParameter("num");
        try {


            System.out.println("String of creation is: "+inputString );
            System.out.println("\nPrepering to Create JSON object");
            JSONObject inputValues = new JSONObject(inputString);
            System.out.println("JSON object was created succesfull");


            double[] array = new double[5];

             array[0]= inputValues.getDouble("one");
             array[1] = inputValues.getDouble("two");
             array[2]= inputValues.getDouble("three");
             array[3]= inputValues.getDouble("four");
             array[4]= inputValues.getDouble("five");
            //***********************************************************//

            System.out.println(array[0]);
            System.out.println(array[1]);
            System.out.println(array[2]);
            System.out.println(array[3]);
            System.out.println(array[4]);
            //***********************************************************//
            
            try{
                File fl = new File("C:/temp/base.txt");
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fl,true)));

                out.print(array[0] + ", ");
                out.print(array[1] + ", ");
                out.print(array[2]+ ", ");
                out.print(array[3]+ ", ");
                out.println(array[4]+ ", ");
                out.close();

            }catch (IOException e) {
                System.out.println("couldnt write to file");
                //exception handling left as an exercise for the reader
            }
            //***********************************************************//


            Arrays.sort(array);

            double max = array[0];
            double min = array[array.length-1];
            double sum = 0;


            for (int i = 0; i <array.length; i++){
                sum += array[i];
            }


            JSONObject jsn = new JSONObject();
            jsn.put("max",max);
            jsn.put("min",min);
            jsn.put("sum",sum);

            System.out.println("Max is: "+ max);
            System.out.println("Min is: "+ min);
            System.out.println("Sum is: "+ sum);

            PrintWriter out = response.getWriter();
            out.println(jsn);


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
