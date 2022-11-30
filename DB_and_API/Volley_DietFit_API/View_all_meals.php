<?php


include "connect.php";
$sql = "SELECT * FROM list_meals";
$result = mysqli_query($conn,$sql);
$meals = array();

while($row = mysqli_fetch_assoc($result)) {
	$index['meals_name'] = $row['meals_name'];
	$index['food_type'] = $row['food_type'];
	$index['calories_info'] = $row['calories_info'];

	array_push($meals,$index);
}
echo json_encode($meals);
?>