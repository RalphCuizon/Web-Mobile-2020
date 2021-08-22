<?php
require "config.php";

header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: GET, PUT, POST, DELETE, OPTIONS');
header('Access-Control-Max-Age: 1000');
header('Access-Control-Allow-Headers: Content-Type, Authorization, X-Requested-With');
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