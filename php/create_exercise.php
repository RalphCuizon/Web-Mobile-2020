<?php
require "config.php";

// prepare and bind
$stmt_check = $conn->prepare("INSERT INTO exercises VALUES (?, ?, ?, ?)");
$stmt_check->bind_param("ssss", $_GET["user_email"], $_GET["category"], $_GET["description"], $_GET["time"]);
$stmt_check->execute();

?>