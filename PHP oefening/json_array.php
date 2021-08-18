<?php
$servername = "ID329653_ralphcuizon.db.webhosting.be";
$username = "ID329653_ralphcuizon";
$password = "Webmobile20";
$dbname = "ID329653_ralphcuizon";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

// prepare and bind
$stmt = $conn->prepare("SELECT * FROM Emp");
$stmt->execute();
$rs=$stmt->get_result();
$emps=array();
while($row=$rs->fetch_assoc())
{
    array_push($emps, $row);
}

echp json_encode($emps);

?>