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


echo " <script id=\"magdynamic\" type=\"text/javascript\">";
echo " function generateChartDataonGoMag() {
        var firstDate = new Date();
        var ArrayX = ".$magX.";
        var ArrayY =".$magY.";
        var ArrayZ =".$magZ.";
        var Temp = ".$tmp .";
        var Time = ".$time.";
        for (var i = 0; i < ".$size."; i++) {
            // we create date objects here. In your data, you can have date strings
            // and then set format of your dates using chart.dataDateFormat property,
            // however when possible, use date objects, as this will speed up chart rendering.
            var newDate =  AmCharts.stringToDate(Time[i], \"YYYY-MM-DD-JJ:NN:SS\");;

            var line1 = ArrayX[i];
            var line2 = ArrayY[i];
            var line3 = ArrayZ[i];
            var tmp = Temp[i];
            var Loc = i;

            chartData2.push({
                date: newDate,
                Lin1: line1,
                Lin2: line2,
                Lin3: line3,
                temp: tmp,
                Location2: Loc
            });
        }
    }";
echo " </script>";

?>