<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){

include 'connect.php';

    $diary_id = $_POST['diary_id'];
    $user_email = $_POST['user_email'];
    $meals_note = $_POST['meals_note'];
    $ex_cal_info = $_POST['ex_cal_info']; 
    $meals_cal_info = $_POST['meals_cal_info']; 
    $ex_time_taken = $_POST['ex_time_taken']; 
    $ex_note = $_POST['ex_note']; 
   

    /*$diary_id = "148";
    $user_email = "farid@gmail.com";
    $meals_new = "nasi ayam";
    $meals_note = "hahahahaha je";
    $ex_new = "senamrobik";
    $ex_note = "hahahahahah sejam"; */

$sql = "SELECT * FROM diary WHERE diary_id='$diary_id' AND user_email='$user_email'";

$check = mysqli_query($conn,$sql);

if(mysqli_num_rows($check)>0) {
    $result ="UPDATE diary SET  meals_cal_info='$meals_cal_info',ex_cal_info='$ex_cal_info',meals_note ='$meals_note',ex_note ='$ex_note', ex_time_taken='$ex_time_taken' WHERE diary_id='$diary_id' AND user_email='$user_email'";

        if(mysqli_query($conn,$result)) {
            echo "data edited successfully";
        }else{
            echo "some error";
        }
    }else{
        echo "haha";
    }
}

?>