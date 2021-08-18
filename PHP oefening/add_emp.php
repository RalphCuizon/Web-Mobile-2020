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
$stmt = $conn->prepare("INSERT INTO Emp VALUES (?, ?, ?)");
$stmt->bind_param("isi", $_GET["id"],$_GET["name"],$_GET["salary"]);


$stmt->execute();


?>