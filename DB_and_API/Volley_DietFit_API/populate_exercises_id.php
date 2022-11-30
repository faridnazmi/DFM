<?php
include "connect.php";
$sql = "SELECT * FROM list_exercise";
if(!$conn->query($sql)) {
	echo "error in connecting to Database";
}else{
	$result = $conn->query($sql);
	if($result->num_rows > 0){
		$return_arr['list_exercise'] = array();
		while ($row = $result->fetch_array()) {
			array_push($return_arr['list_exercise'], array(

			
				'ex_name' => $row['ex_name'],
				'ex_type' => $row['ex_type'],
				'calories_info' => $row['calories_info'],
				'ex_time_taken' => $row['ex_time_taken']

			));
		}
			echo json_encode($return_arr);
		}
	}

?>