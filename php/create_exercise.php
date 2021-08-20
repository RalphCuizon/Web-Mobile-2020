<?php
require "config.php";

// prepare and bind
$stmt = $conn->prepare("INSERT INTO exercises VALUES (?,?, ?, ?, ?)");
$stmt->bind_param("issss", $_GET["id"], $_GET["user_email"], $_GET["category"], $_GET["description"], $_GET["time"]);
$stmt->execute();

?>