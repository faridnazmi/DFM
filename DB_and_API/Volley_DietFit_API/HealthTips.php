<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {
    
    $user_email = $_POST['user_email'];

    include 'connect.php';

    $sql = "SELECT * FROM health_tips";

    $response = mysqli_query($conn, $sql);

    $result = array();
    $result['read'] = array();

    if( mysqli_num_rows($response) === 1 ) {
        
        if ($row = mysqli_fetch_assoc($response)) {
 
            $h['tips_id']    = $row['tips_id'];
            $h['tips_name']    = $row['tips_name'];
            $h['tips_type'] = $row['tips_type'];
            $h['tips_desc']      = $row['tips_desc'];


           
 
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