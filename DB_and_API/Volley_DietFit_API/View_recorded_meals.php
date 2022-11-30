<?php


include "connect.php";
$sql = "SELECT * FROM diary WHERE user_email='faridnazmitest@gmail.com'";
$result = mysqli_query($conn,$sql);
$meals = array();

while($row = mysqli_fetch_assoc($result)) {
	$index['diary_id'] = $row['diary_id'];
	$index['meals_name'] = $row['meals_name'];
	$index['meals_cal_info'] = $row['meals_cal_info'];
	$index['meals_note'] = $row['meals_note'];
	$index['diary_date'] = $row['diary_date'];

	array_push($meals,$index);
}
echo json_encode($meals);
?>