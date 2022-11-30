<?php

include 'connect.php';
	$user_email = $_POST['user_email'];
   	$meals_name = $_POST['meals_name'];
    $ex_name = $_POST['ex_name'];
    $meals_new = $_POST['meals_new'];
    $meals_note = $_POST['meals_note'];
    $ex_new = $_POST['ex_new'];
    $ex_note = $_POST['ex_note'];

    $sql = "SELECT * FROM diary where user_email='$user_email'";
    $check = mysqli_query($conn,$sql);
    if(mysqli_num_rows($check)>0){
    	$result ="UPDATE diary SET meals_name='$meals_name', ex_name='$ex_name', meals_note = '$meals_note',ex_name = '$ex_name',ex_note = '$ex_note'";
    	if(mysqli_query($conn,$result)) {
    		echo "data edited successfully";
    	}else{
    		echo "some error";
    	}
    }else {
    	echo "unauthorized diary"
    }

?>