<?php


include "connect.php";
$sql = "SELECT * FROM list_exercise";
$result = mysqli_query($conn,$sql);
$meals = array();

while($row = mysqli_fetch_assoc($result)) {
	$index['ex_name'] = $row['ex_name'];
	$index['ex_type'] = $row['ex_type'];
	$index['calories_info'] = $row['calories_info'];
	$index['ex_time_taken'] = $row['ex_time_taken'];

	array_push($meals,$index);
}
echo json_encode($meals);
?>