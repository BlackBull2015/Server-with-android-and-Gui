    <link rel="stylesheet" href="style.css" type="text/css">
    <script src="amcharts/amcharts.js" type="text/javascript"></script>
    <script src="amcharts/serial.js" type="text/javascript"></script>
    <script>
        <?php
      $servername = "localhost:3306";
      $username = "root";
      $password = "root";
      $dbname = "readings";

      $conn = new mysqli("localhost:3306", "root", "root",$dbname);
      if ($conn->connect_error) {
          die("Connection failed: " . $conn->connect_error);
      }

      $sql = "SELECT * FROM datareadings";
      $result = $conn->query($sql);

      if ($result->num_rows > 0) {
          // output data of each row

          $accX = "[";
          $accY = "[";
          $accZ = "[";
          $magX = "[";
          $magY = "[";
          $magZ = "[";
          $tmp = "[";
          $time = "[";
          $size = 0;
          while($row = $result->fetch_assoc()) {
              $accX .= $row["accX"].", ";
              $accY .= $row["accY"].", ";
              $accZ .= $row["accZ"].", ";
              $magX .= $row["magX"].", ";
              $magY .= $row["magY"].", ";
              $magZ .= $row["magZ"].", ";
              $tmp .= $row["tmp"].", ";
              $time .= "\"".$row["time"]."\", ";
              $size = $row["tableID"];
          }
          $accX .= "]";
          $accY .= "]";
          $accZ .= "]";
          $magX .= "]";
          $magY .= "]";
          $magZ .= "]";
          $tmp .= "]";
          $time .= "]";

      } else {
          echo "0 results";
      }
      $conn->close();
      ?>
        var chartData = [];
        var chartData2 = [];
        generateChartData();
        generateChartData2();


        var chart1 = AmCharts.makeChart("chart1div", {
            type: "serial",
            dataProvider: chartData,
            categoryField: "Location1",
            categoryAxis: {
                gridAlpha: 0.15,
                minorGridEnabled: true,
                axisColor: "#DADADA"
            },
            valueAxes: [{
                axisAlpha: 0.2,
                id: "v1"
            }],
            graphs: [{
                title: "red line",
                id: "g1",
                valueAxis: "v1",
                valueField: "Lin1",
                //  bullet: "round",
                bulletBorderColor: "#FFFFFF",
                bulletBorderAlpha: 1,
                lineThickness: 2,
                lineColor: "#b5030d",
                // negativeLineColor: "#0352b5",
                balloonText: "[[date]]<br><b><span style='font-size:14px;'>AccXAxis: [[value]]</span></b>"
            },{
                title: "blue line",
                id: "g2",
                valueAxis: "v1",
                valueField: "Lin2",
                temp:"Lin1",
                bullet: "round",
                bulletBorderColor: "#FFFFFF",
                bulletBorderAlpha: 1,
                lineThickness: 2,
                lineColor: "#0352b5",
                //  negativeLineColor: "#0352b5",
                balloonText: "[[date]]<br><b><span style='font-size:14px;'>AccYAxis: [[value]]</span></b><br><b><span style='font-size:14px;'>Temp: [[temp]]</span></b>"

            },{
                title: "blue line",
                id: "g3",
                valueAxis: "v1",
                valueField: "Lin3",
                bullet: "round",
                bulletBorderColor: "#FFFFFF",
                bulletBorderAlpha: 1,
                lineThickness: 2,
                lineColor: "#FFFF00",
                //  negativeLineColor: "#0352b5",
                balloonText: "[[date]]<br><b><span style='font-size:14px;'>AccZAxis: [[value]]</span></b>"

            }],
            chartCursor: {
                fullWidth:true,
                cursorAlpha:0.1
            },
            chartScrollbar: {
                scrollbarHeight: 40,
                color: "#FFFFFF",
                autoGridCount: true,
                graph: "g1",
                graph: "g2",
                graph: "g3",
            },

            mouseWheelZoomEnabled:true
        });

        var chart2 = AmCharts.makeChart("chart2div", {
            type: "serial",
            dataProvider: chartData2,
            categoryField: "Location2",
            categoryAxis: {
                gridAlpha: 0.15,
                minorGridEnabled: true,
                axisColor: "#DADADA"
            },
            valueAxes: [{
                axisAlpha: 0.2,
                id: "v1"
            }],
            graphs: [{
                title: "red line",
                id: "g1",
                valueAxis: "v1",
                valueField: "Lin1",
                //  bullet: "round",
                bulletBorderColor: "#FFFFFF",
                bulletBorderAlpha: 1,
                lineThickness: 2,
                lineColor: "#b5030d",
                // negativeLineColor: "#0352b5",
                balloonText: "[[date]]<br><b><span style='font-size:14px;'>MagXAxis: [[value]]</span></b>"
            },{
                title: "blue line",
                id: "g2",
                valueAxis: "v1",
                valueField: "Lin2",
                temp:"Lin1",
                bullet: "round",
                bulletBorderColor: "#FFFFFF",
                bulletBorderAlpha: 1,
                lineThickness: 2,
                lineColor: "#0352b5",
                //  negativeLineColor: "#0352b5",
                balloonText: "[[date]]<br><b><span style='font-size:14px;'>MagYAxis: [[value]]</span></b><br><b><span style='font-size:14px;'>Temp: [[temp]]</span></b>"

            },{
                title: "blue line",
                id: "g3",
                valueAxis: "v1",
                valueField: "Lin3",
                bullet: "round",
                bulletBorderColor: "#FFFFFF",
                bulletBorderAlpha: 1,
                lineThickness: 2,
                lineColor: "#FFFF00",
                //  negativeLineColor: "#0352b5",
                balloonText: "[[date]]<br><b><span style='font-size:14px;'>MagZAxis: [[value]]</span></b>"

            }],
            chartCursor: {
                fullWidth:true,
                cursorAlpha:0.1
            },
            chartScrollbar: {
                scrollbarHeight: 40,
                color: "#FFFFFF",
                autoGridCount: true,
                graph: "g1",
                graph: "g2",
                graph: "g3",
            },

            mouseWheelZoomEnabled:true
        });

        chart1.addListener("dataUpdated", zoomChart);
        chart2.addListener("dataUpdated", zoomChart);


        // generate some random data, quite different range
        function generateChartData() {

                var newDate= null;

                var line1 = 0;
                var line2 = 0;
                var line3 = 0;
                var tmp = 0;
                var Loc = 0;

                chartData.push({
                    date: newDate,
                    Lin1: line1,
                    Lin2: line2,
                    Lin3: line3,
                    temp: tmp,
                    Location1: Loc
                });
        }
        // generate some random data, quite different range
        function generateChartData2() {

                var newDate =  null;

                var line1 = 0;
                var line2 = 0;
                var line3 = 0;
                var tmp = 0;
                var Loc = 0;

                chartData2.push({
                    date: newDate,
                    Lin1: line1,
                    Lin2: line2,
                    Lin3: line3,
                    temp: tmp,
                    Location2: Loc
                });
        }
        // this method is called when chart is first inited as we listen for "dataUpdated" event
        function zoomChart() {
            // different zoom methods can be used - zoomToIndexes, zoomToDates, zoomToCategoryValues
            chart1.zoomToIndexes(chartData.length - 40, chartData.length - 1);
            chart2.zoomToIndexes(chartData2.length - 40, chartData2.length - 1);
        }

        // changes cursor mode from pan to select
        function setPanSelect() {
            var chartCursor = chart1.chartCursor;
            var chartCursor2 = chart2.chartCursor;

            if (document.getElementById("rb1").checked) {
                chartCursor.pan = false;
                chartCursor.zoomable = true;

            } else {
                chartCursor.pan = true;
            }
            if (document.getElementById("rb3").checked) {
                chartCursor2.pan = false;
                chartCursor2.zoomable = true;

            } else {
                chartCursor2.pan = true;
            }
            chart1.validateNow();
            chart2.validateNow();
        }

    </script>