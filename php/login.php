<?php
require "config.php";

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