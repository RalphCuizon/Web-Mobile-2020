<?php
require "config.php";

header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: GET, PUT, POST, DELETE, OPTIONS');
header('Access-Control-Max-Age: 1000');
header('Access-Control-Allow-Headers: Content-Type, Authorization, X-Requested-With');
// prepare and bind
$stmt = $conn->prepare("SELECT * FROM users WHERE email=? AND password=?");
$stmt->bind_param("ss", $_GET["email"],$_GET["password"]);
$stmt->execute();
$rs = $stmt->get_result();
if ($rs->num_rows==0)  { 
    echo "0";
}
else {
    echo "1";
} 

?>