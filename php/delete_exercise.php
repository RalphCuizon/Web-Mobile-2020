<?php
require "config.php";

//prepare and bind
$stmt = $conn->prepare("DELETE FROM exercises  WHERE id=?");
$stmt->bind_param("i", $_POST["id"]);
$stmt->execute();

/*$id = $_POST['id'];

$query = "DELETE FROM exercises WHERE id=$id";

$result = mysqli_query($connection,$query);
if($result){
    echo json_encode("Data deleted");
}else{
    echo json_encode("error occured");
} 
id = $_POST['id'];
$sql = "DELETE FROM exercises WHERE id=$id";

if ($conn->query($sql) === TRUE) {
  echo "Record deleted successfully";
} else {
  echo "Error deleting record: " . $conn->error;
}*/

?>