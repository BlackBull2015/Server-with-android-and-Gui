<?php
//http://www.mustbebuilt.co.uk/php/select-statements-with-pdo/

$DBServer = 'localhost'; // e.g 'localhost' or '192.168.1.100'
$DBUser   = 'root';
$DBPass   = 'root';
$DBName   = 'sidelineeyeblog';
$DBPort = '3306';

$pdo = new PDO('mysql:host='.$DBServer.';dbname='.$DBName,$DBUser,$DBPass);

$blogID = $_REQUEST['blogID'];

$sql= "SELECT * FROM blogpicstable WHERE blogID = :blogID";
$stmt = $pdo->prepare($sql);
$stmt->bindParam(':blogID', $blogID, PDO::PARAM_INT);
$stmt->execute();
/*Use this to return all data from row, ready to json_encode
$row =$stmt->fetchAll( PDO::FETCH_ASSOC ); 
*/
$returnArray=array();

while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
        $objArr["src"] = $row['blogPicURL'];
        $objArr["thumb"] = $row['blogPicURL'];
        $objArr["subHtml"] = "<h4>".$row['blogPicCaption']."</h4>";
        $returnArray[]=$objArr;
}

/*LightGallery expecting data in following format
dynamicEl: [
            {
              'src': 'https://www.dropbox.com/s/o679a8rpmevj70o/Dublin%20Shooting%20Stats.png?dl=1',
              'thumb': 'https://www.dropbox.com/s/o679a8rpmevj70o/Dublin%20Shooting%20Stats.png?dl=1',
              'subHtml': "<h4>Dublin Shooting Stats</h4>"
          },{
              'src': 'https://www.dropbox.com/s/c5txae2h2f1gk46/Kerry%20Attacks%202nd%20Half%20Map.png?dl=1',
              'thumb': 'https://www.dropbox.com/s/c5txae2h2f1gk46/Kerry%20Attacks%202nd%20Half%20Map.png?dl=1',
              'subHtml': "<h4>Kerry 2nd Half Attacks Map</h4>"
          }]
*/

header('Content-Type: application/json');
echo json_encode($returnArray);

?>
 