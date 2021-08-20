<?php
require "config.php";

// prepare and bind
$stmt = $conn->prepare("DELETE FROM exercises  WHERE id=?");
$stmt->bind_param("i", $_GET["id"]);
$stmt->execute();



?>