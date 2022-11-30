<?php
include 'connect.php';

//$user_email = $_POST['user_email'];
$diary_id = $_POST['diary_id'];

$query = "SELECT * FROM diary WHERE diary_id ='$diary_id' ";
$check = mysqli_query($conn,$query);
$result = array();

if(mysqli_num_rows($check) === 1) {
	$sql = "DELETE FROM diary WHERE diary_id ='$diary_id' ";
	if(mysqli_query($conn,$sql))
	{
		$result['state'] = "delete";
		echo json_encode($result);

	}else{
		echo "Diary Already Delete";
	}
}else {
	echo "Invalid Diary";
}

?>