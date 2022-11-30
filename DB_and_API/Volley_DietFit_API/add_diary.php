<?php

if ($_SERVER['REQUEST_METHOD'] =='POST'){
 
    $user_email = $_POST['user_email'];
    $meals_name = $_POST['meals_name'];
    $meals_cal_info = $_POST['meals_cal_info'];
    $ex_name = $_POST['ex_name'];
    $ex_cal_info = $_POST['ex_cal_info'];
    $meals_note = $_POST['meals_note'];
    $ex_note = $_POST['ex_note'];
    $ex_time_taken = $_POST['ex_time_taken'];
   
   /* $user_email = "farid@gmail.com";
    $meals_name = "nasi lemak";
    $ex_name = "jogging";
    $meals_new = "testing";
    $meals_note = "dummy";
    $ex_new = "dummy ex";
    $ex_note = "dummy note";
    $calories_goal = "90"; */

     include 'connect.php';
    

    $sql = "INSERT INTO diary (user_email, meals_name, meals_cal_info, ex_name, ex_cal_info,  meals_note, ex_note, ex_time_taken) 
    VALUES ('$user_email','$meals_name','$meals_cal_info', '$ex_name',  '$ex_cal_info', '$meals_note', '$ex_note','$ex_time_taken')";


    if ( mysqli_query($conn, $sql) ) {
        $result["success"] = "1";
        $result["message"] = "success";

        echo json_encode($result);
        mysqli_close($conn);

    } else {

        $result["success"] = "0";
        $result["message"] = "error";

        echo json_encode($result);
        mysqli_close($conn);
    }
}

?>