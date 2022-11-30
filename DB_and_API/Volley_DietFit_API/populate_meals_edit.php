<?php
include "connect.php";
$sql = "SELECT * FROM list_meals";
if(!$conn->query($sql)) {
	echo "error in connecting to Database";
}else{
	$result = $conn->query($sql);
	if($result->num_rows > 0){
		$return_arr['list_meals'] = array();
		while ($row = $result->fetch_array()) {
			array_push($return_arr['list_meals'], array(

				'meals_name' => $row['meals_name'],
				'food_type' => $row['food_type'],
				'calories_info' => $row['calories_info']

			));
		}
			echo json_encode($return_arr);
		}
	}

?>