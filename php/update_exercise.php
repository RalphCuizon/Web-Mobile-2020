<?php
require "config.php";


// prepare and bind
$stmt = $conn->prepare("UPDATE exercises SET category=?, description=?, time=? WHERE id=?");
$stmt->bind_param("sssi", $_POST["category"], $_POST["description"], $_POST["time"], $_POST["id"]);
$stmt->execute();

?>