<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {
    
    $user_email = $_POST['user_email'];

    include 'connect.php';

    $sql = "SELECT * FROM user WHERE user_email ='$user_email' ";

    $response = mysqli_query($conn, $sql);

    $result = array();
    $result['read'] = array();

    if( mysqli_num_rows($response) === 1 ) {
        
        if ($row = mysqli_fetch_assoc($response)) {
 
            $h['user_email']    = $row['user_email'];
            $h['calories_goal']    = $row['calories_goal'];

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