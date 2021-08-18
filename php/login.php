<?php
require "config.php";

// prepare and bind
$stmt_check = $conn->prepare("SELECT * FROM users WHERE email=? AND password=?");
$stmt_check->bind_param("ss", $_GET["email"],$_GET["password"]);
$stmt_check->execute();
$rs = $stmt_check->get_result();
if ($rs->num_rows==0)  { 
    echo "Unsuccesfully logged in";
}
else {
    echo "Succesfully logged in";
} 

?>