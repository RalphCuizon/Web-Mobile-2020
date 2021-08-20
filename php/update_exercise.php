<?php
require "config.php";


// prepare and bind
$stmt = $conn->prepare("UPDATE exercises SET category=?, description=?, time=? WHERE id=?");
$stmt->bind_param("sssi", $_GET["category"], $_GET["description"], $_GET["time"], $_GET["id"]);
$stmt->execute();

?>