import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import javax.xml.crypto.Data;









/*

!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
AmCharts.stringToDate("01-10-2014", "DD-MM-YYYY")
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

Use o set time in the graphs

 */
@WebServlet("/DisplayAcc")
public class DisplayAcc extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<Reading> DataGraph;
        DataGraph = CreateData(new File("C://temp/base.txt"));
        String arrayX = "[";
        String arrayY = "[";
        String arrayZ = "[";
        String arrayTmp = "[";



        for (int i = 0; i < DataGraph.size(); i++){
            arrayX+= DataGraph.get(i).getAccXValue() + ", ";
            arrayY+= DataGraph.get(i).getAccYValue() + ", ";
            arrayZ+= DataGraph.get(i).getAccZValue() + ", ";
            arrayTmp+= DataGraph.get(i).getTemperature() + ", ";

        }
        arrayX+=" ]";
        arrayY+=" ]";
        arrayZ+=" ]";
        arrayTmp+=" ]";

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
                "            categoryField: \"date\",\n" +
                "            categoryAxis: {\n" +
                "                parseDates: true,\n" +
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
                "                balloonText: \"[[category]]<br><b><span style='font-size:14px;'>XAxis: [[value]]</span></b>\"\n" +
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
                "                balloonText: \"[[category]]<br><b><span style='font-size:14px;'>YAxis: [[value]]</span></b><br><b><span style='font-size:14px;'>Temp: [[temp]]</span></b>\"\n" +
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
                "                    balloonText: \"[[category]]<br><b><span style='font-size:14px;'>ZAxis: [[value]]</span></b>\"\n" +
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
                "            firstDate.setDate(firstDate.getDate() - "+DataGraph.size()+");\n" +
                "\n" +
                "            for (var i = 0; i < "+DataGraph.size()+"; i++) {\n" +
                "                // we create date objects here. In your data, you can have date strings\n" +
                "                // and then set format of your dates using chart.dataDateFormat property,\n" +
                "                // however when possible, use date objects, as this will speed up chart rendering.\n" +
                "                var newDate = new Date(firstDate);\n" +
                "                newDate.setDate(newDate.getDate() + i);\n" +
                "\n" +
                "                var line1 = ArrayX[i];\n" +
                "                var line2 = ArrayY[i];\n" +
                "                var line3 = ArrayZ[i];\n" +
                "                var tmp = Temp[i];\n" +
                "\n" +
                "                chartData.push({\n" +
                "                    date: newDate,\n" +
                "                    Lin1: line1,\n" +
                "                    Lin2: line2,\n" +
                "                    Lin3: line3,\n" +
                "                    temp: tmp\n" +
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


