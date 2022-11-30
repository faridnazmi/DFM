<?php
include "connect.php";
$sql = "SELECT * FROM diary WHERE user_email='faridnazmitest@gmail.com'";
$result = mysqli_query($conn,$sql);
$exercise = array();

while($row = mysqli_fetch_assoc($result)) {
	$index['diary_id'] = $row['diary_id'];
	$index['meals_name'] = $row['meals_name'];
	$index['meals_cal_info'] = $row['meals_cal_info'];
	$index['ex_cal_info'] = $row['ex_cal_info'];
	$index['ex_time_taken'] = $row['ex_time_taken'];
	$index['ex_name'] = $row['ex_name'];
	$index['meals_note'] = $row['meals_note'];
	$index['ex_note'] = $row['ex_note'];
	$index['diary_date'] = $row['diary_date'];
	array_push($exercise,$index);
}
echo json_encode($exercise);
?>