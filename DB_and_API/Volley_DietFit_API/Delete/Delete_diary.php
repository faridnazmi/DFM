<?php
include 'connect.php'
$user_email = $_POST['user_email'];

$query = "SELECT * FROM diary WHERE user_email= '$user_email";
$check = mysqli_query($conn,$query);
$result = array();
if(mysqli_num_rows($check) == 1) {
	$sql = "DELETE FROM diary WHERE user_email='$user_email'";
	if(mysqli_query($conn,$sql)){
			$result['state'] = "delete";
			echo json_encode($result);
	}else {
		echo "diary already deleted"
	}else {
		echo "Invalid user diary"
	}
}
?>