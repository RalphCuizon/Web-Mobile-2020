<?php
require "config.php";

// prepare and bind
$stmt = $conn->prepare("SELECT * FROM exercises where user_email=?");
$stmt->bind_param("s", $_GET["user_email"]);
$stmt->execute();
$rs=$stmt->get_result();
$arr=array();
while($row=$rs->fetch_assoc())
{
    array_push($arr, $row);
}

echo json_encode($arr);

?>