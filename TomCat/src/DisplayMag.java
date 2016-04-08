import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import javax.xml.crypto.Data;


@WebServlet("/DisplayMag")
public class DisplayMag extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {





        try {
            PreparedStatement sqlUpdate;
            // load database driver class
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/readings", "root", "root");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users WHERE verification='" +request.getParameter("Ver")+ "';");


            if(resultSet.next()){


        ArrayList<Reading> DataGraph;
        DataGraph = CreatePointsFromDb();
        String arrayX = "[";
        String arrayY = "[";
        String arrayZ = "[";
        String arrayTmp = "[";
        String arrayTime = "[";



        for (int i = 0; i < DataGraph.size(); i++){
            arrayX+= DataGraph.get(i).getMagXValue() + ", ";
            arrayY+= DataGraph.get(i).getMagYValue() + ", ";
            arrayZ+= DataGraph.get(i).getMagZValue() + ", ";
            arrayTmp+= DataGraph.get(i).getTemperature() + ", ";
            arrayTime+= "\""+DataGraph.get(i).getTime() + "\", ";

        }
        arrayX+=" ]";
        arrayY+=" ]";
        arrayZ+=" ]";
        arrayTmp+=" ]";
        arrayTime+=" ]";

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">\n" +
                "<html>\n" +
                "\n" +
                "<head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "    <title>amCharts examples</title>\n" +
                "    <link rel=\"stylesheet\" href=\"style.css\" type=\"text/css\">\n" +
                "    <script src=\"amcharts/amcharts.js\" type=\"text/javascript\"></script>\n" +
                "    <script src=\"amcharts/serial.js\" type=\"text/javascript\"></script>\n" +
                "\n" +
                "    <script>\n" +
                "        var chartData = [];\n" +
                "        generateChartData();\n" +
                "\n" +
                "        var chart = AmCharts.makeChart(\"chartdiv\", {\n" +
                "            type: \"serial\",\n" +
                "            dataProvider: chartData,\n" +
                "            categoryField: \"location\",\n" +
                "            categoryAxis: {\n" +
                "                gridAlpha: 0.15,\n" +
                "                minorGridEnabled: true,\n" +
                "                axisColor: \"#DADADA\"\n" +
                "            },\n" +
                "            valueAxes: [{\n" +
                "                axisAlpha: 0.2,\n" +
                "                id: \"v1\"\n" +
                "            }],\n" +
                "            graphs: [{\n" +
                "                title: \"red line\",\n" +
                "                id: \"g1\",\n" +
                "                valueAxis: \"v1\",\n" +
                "                valueField: \"Lin1\",\n" +
                "              //  bullet: \"round\",\n" +
                "                bulletBorderColor: \"#FFFFFF\",\n" +
                "                bulletBorderAlpha: 1,\n" +
                "                lineThickness: 2,\n" +
                "                lineColor: \"#b5030d\",\n" +
                "               // negativeLineColor: \"#0352b5\",\n" +
                "                balloonText: \"[[date]]<br><b><span style='font-size:14px;'>MagXAxis: [[value]]</span></b>\"\n" +
                "            },{\n" +
                "                title: \"blue line\",\n" +
                "                id: \"g2\",\n" +
                "                valueAxis: \"v1\",\n" +
                "                valueField: \"Lin2\",\n" +
                "                temp:\"Lin1\",\n" +
                "                bullet: \"round\",\n" +
                "                bulletBorderColor: \"#FFFFFF\",\n" +
                "                bulletBorderAlpha: 1,\n" +
                "                lineThickness: 2,\n" +
                "                lineColor: \"#0352b5\",\n" +
                "                //  negativeLineColor: \"#0352b5\",\n" +
                "                balloonText: \"[[date]]<br><b><span style='font-size:14px;'>MagYAxis: [[value]]</span></b><br><b><span style='font-size:14px;'>Temp: [[temp]]</span></b>\"\n" +
                "\n" +
                "            },{\n" +
                "                    title: \"blue line\",\n" +
                "                    id: \"g3\",\n" +
                "                    valueAxis: \"v1\",\n" +
                "                    valueField: \"Lin3\",\n" +
                "                    bullet: \"round\",\n" +
                "                    bulletBorderColor: \"#FFFFFF\",\n" +
                "                    bulletBorderAlpha: 1,\n" +
                "                    lineThickness: 2,\n" +
                "                    lineColor: \"#FFFF00\",\n" +
                "                    //  negativeLineColor: \"#0352b5\",\n" +
                "                    balloonText: \"[[date]]<br><b><span style='font-size:14px;'>MagZAxis: [[value]]</span></b>\"\n" +
                "\n" +
                "                }],\n" +
                "            chartCursor: {\n" +
                "                fullWidth:true,\n" +
                "                cursorAlpha:0.1\n" +
                "            },\n" +
                "            chartScrollbar: {\n" +
                "                scrollbarHeight: 40,\n" +
                "                color: \"#FFFFFF\",\n" +
                "                autoGridCount: true,\n" +
                "                graph: \"g1\",\n" +
                "                graph: \"g2\",\n" +
                "                graph: \"g3\",\n" +
                "            },\n" +
                "\n" +
                "            mouseWheelZoomEnabled:true\n" +
                "        });\n" +
                "\n" +
                "        chart.addListener(\"dataUpdated\", zoomChart);\n" +
                "\n" +
                "\n" +
                "        // generate some random data, quite different range\n" +
                "        function generateChartData() {\n" +
                "\n" +
                "            var firstDate = new Date();\n" +
                "            var ArrayX = "+arrayX+";\n" +
                "            var ArrayY ="+arrayY+" ;\n" +
                "            var ArrayZ ="+arrayZ+" ;\n" +
                "            var Temp = "+arrayTmp+";\n" +
                "            var Time = "+arrayTime+";\n" +
                "\n" +
                "            for (var i = 0; i < "+DataGraph.size()+"; i++) {\n" +
                "                // we create date objects here. In your data, you can have date strings\n" +
                "                // and then set format of your dates using chart.dataDateFormat property,\n" +
                "                // however when possible, use date objects, as this will speed up chart rendering.\n" +
                "                var newDate =  AmCharts.stringToDate(Time[i], \"YYYY-MM-DD-JJ:NN:SS\");;\n" +
                "\n" +
                "                var line1 = ArrayX[i];\n" +
                "                var line2 = ArrayY[i];\n" +
                "                var line3 = ArrayZ[i];\n" +
                "                var tmp = Temp[i];\n" +
                "                var Loc = i;\n" +
                "\n" +
                "                chartData.push({\n" +
                "                    date: newDate,\n" +
                "                    Lin1: line1,\n" +
                "                    Lin2: line2,\n" +
                "                    Lin3: line3,\n" +
                "                    temp: tmp,\n" +
                "                    location: Loc\n" +
                "                });\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        // this method is called when chart is first inited as we listen for \"dataUpdated\" event\n" +
                "        function zoomChart() {\n" +
                "            // different zoom methods can be used - zoomToIndexes, zoomToDates, zoomToCategoryValues\n" +
                "            chart.zoomToIndexes(chartData.length - 40, chartData.length - 1);\n" +
                "        }\n" +
                "\n" +
                "        // changes cursor mode from pan to select\n" +
                "        function setPanSelect() {\n" +
                "            var chartCursor = chart.chartCursor;\n" +
                "\n" +
                "            if (document.getElementById(\"rb1\").checked) {\n" +
                "                chartCursor.pan = false;\n" +
                "                chartCursor.zoomable = true;\n" +
                "\n" +
                "            } else {\n" +
                "                chartCursor.pan = true;\n" +
                "            }\n" +
                "            chart.validateNow();\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "    </script>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<div id=\"chartdiv\" style=\"width: 100%; height: 400px;\"></div>\n" +
                "<div style=\"margin-left:35px;\">\n" +
                "    <input type=\"radio\" checked=\"true\" name=\"group\" id=\"rb1\" onclick=\"setPanSelect()\">Select\n" +
                "    <input type=\"radio\" name=\"group\" id=\"rb2\" onclick=\"setPanSelect()\">Pan\n" +
                "</div>\n" +
                "</body>\n" +
                "\n" +
                "</html>");
            }else{
                getServletContext().getRequestDispatcher("/UndefVer.html").forward(request, response);
            }

        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
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


    private ArrayList<Reading> CreatePointsFromDb(){
        try {
            ArrayList<Reading> DataGraph= new ArrayList<>();
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
                DataGraph.add(CreatePoint(ReadingFromDb));
                ReadingFromDb = "";
                // results.append("\n");
            }
            System.out.println("Operation done");
            // close statement and connection
            statement.close();
            connection.close();
            return DataGraph;
        } catch (Exception ee) {
            return null;
        }

    }


}


