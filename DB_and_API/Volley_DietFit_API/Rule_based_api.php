<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {
    
    $user_email = $_POST['user_email'];

    include 'connect.php';

    $sql = "SELECT meals_name FROM list_meals";

    $response = mysqli_query($conn, $sql);

    $result = array();
    $result['read'] = array();

    if( mysqli_num_rows($response) === 1 ) {
        
        if ($row = mysqli_fetch_assoc($response)) {
 
            $h['user_email']    = $row['user_email'];
            $h['user_pwd']    = $row['user_pwd'];
            $h['username_user'] = $row['username_user'];
            $h['user_bmi']      = $row['user_bmi'];
            $h['user_age']      = $row['user_age'];
            $h['user_gender']   = $row['user_gender'];
            $h['user_act_lvl']   = $row['user_act_lvl'];


           
 
             array_push($result["read"], $h);
 
             $result["success"] = "1";
             echo json_encode($result);
        }
 
   }
 
 }else {
 
     $result["success"] = "0";
     $result["message"] = "Error!";
     echo json_encode($result);
 
     mysqli_close($conn);
 }
 
 ?>