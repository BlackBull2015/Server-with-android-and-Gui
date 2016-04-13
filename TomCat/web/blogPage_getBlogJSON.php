<?php
//http://www.mustbebuilt.co.uk/php/select-statements-with-pdo/


$DBServer = 'localhost'; // e.g 'localhost' or '192.168.1.100'
$DBUser   = 'root';
$DBPass   = 'root';
$DBName   = 'readings';
$DBPort = '3306';

$pdo = new PDO('mysql:host='.$DBServer.';dbname='.$DBName,$DBUser,$DBPass);

$blogID = $_REQUEST['blogID'];

//Increment BlogHits by one
//$sql = "UPDATE `blogtable` SET `blogHits` = `blogHist`+1 WHERE `blogID` = :blogID";
//$stmt = $pdo->prepare($sql);
//$stmt->bindParam(':blogID', $blogID, PDO::PARAM_INT);
//$stmt->execute();

$sql= "SELECT * FROM datareadings";
$stmt = $pdo->prepare($sql);
$stmt->bindParam(':blogID', $blogID, PDO::PARAM_INT);
$stmt->execute();

if ($stmt->rowCount() > 0) {
	$row =$stmt->fetchObject();
	$blogText = $row->blogText;
	$blogTitle = $row->blogTitle;
	$blogSubTitle = $row->blogSubTitle;
} else {
   	$blogText = "<h2>Unfortunatly this is page is not available at this time</h2>";
	$blogTitle = "SidelineEye Blog";
	$blogSubTitle = "Welcome to the SidelineEye Blog";
}

$returnArray = array("blogText"=>$blogText,  "blogTitle"=>$blogTitle, "blogSubTitle"=>$blogSubTitle);
header('Content-Type: application/json');
echo json_encode($returnArray);

?>
