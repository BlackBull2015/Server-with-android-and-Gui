<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
sadsfdg
<?php
echo "In php";
$servername = "localhost:3306";
$username = "root";
$password = "root";
$dbname = "readings";
echo "Created vars";
// Create connection
echo "Con To dbbefore";

if (!function_exists('mysqli_init') && !extension_loaded('mysqli')) {
    echo 'We don\'t have mysqli!!!';
} else {
    echo 'Phew we have it!';
}



$conn = new mysqli("localhost:3306", "root", "root",$dbname);
echo "Con To db";
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql = "SELECT * FROM datareadings";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        echo "id: " . $row["tableID"]. " - Name: " . $row["accX"]. " " . $row["accY"]. "<br>";
    }
} else {
    echo "0 results";
}
$conn->close();
?>
</body>
</html>


