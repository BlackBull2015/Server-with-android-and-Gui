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
<?php
//echo "X is: ".$accX."\n"."Y ".$accY."\n".$accZ."\n".$magX."\n".$magY."\n".$magZ."\n".$tmp."\n".$time."\n"."size id: ".$size;
//?>
