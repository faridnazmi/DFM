<?php

//if ($_SERVER['REQUEST_METHOD'] =='POST'){
 
    $amail = "admin@gmail.com";
    $apassword = "123456";
   

   /* $user_email = "farid@gmail.com";
    $meals_name = "nasi lemak";
    $ex_name = "jogging";
    $meals_new = "testing";
    $meals_note = "dummy";
    $ex_new = "dummy ex";
    $ex_note = "dummy note";
    $calories_goal = "90"; */

    $apassword = password_hash($apassword, PASSWORD_DEFAULT);

    include 'connect.php';
    

    $sql = "INSERT INTO admin_table (amail, apassword) 
    VALUES ('$amail', '$apassword')";


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
//}

?>