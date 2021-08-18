<?php
require "config.php";

// prepare and bind
$stmt_check = $conn->prepare("SELECT * FROM users WHERE email=?");
$stmt_check->bind_param("s", $_GET["email"]);
$stmt_check->execute();
$rs = $stmt_check->get_result();
if ($rs->num_rows==0)  { 
    $stmt = $conn->prepare("INSERT INTO users VALUES (?, ?, ?, ?)");
    $stmt->bind_param("ssss", $_GET["email"],$_GET["password"],$_GET["firstname"],$_GET["lastname"]);
    $stmt->execute(); 
    echo "Succesfully added a user";
}
else {
    echo "Email already been used";
} 

?>