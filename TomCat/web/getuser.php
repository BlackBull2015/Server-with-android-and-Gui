<!DOCTYPE html>
<html>
<head>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }

        table, td, th {
            border: 1px solid black;
            padding: 5px;
        }

        th {text-align: left;}
    </style>
</head>
<body>

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

echo "<table>
<tr>
<th>Firstname</th>
<th>Lastname</th>
<th>Age</th>
<th>Hometown</th>
<th>Job</th>
</tr>";
while($row = mysqli_fetch_array($result)) {
    echo "<tr>";
    echo "<td>" . $row['accX'] . "</td>";
    echo "<td>" . $row['accY'] . "</td>";
    echo "<td>" . $row['accZ'] . "</td>";
    echo "<td>" . $row['magX'] . "</td>";
    echo "<td>" . $row['magY'] . "</td>";
    echo "</tr>";
}
echo "</table>";
?>
</body>
</html>