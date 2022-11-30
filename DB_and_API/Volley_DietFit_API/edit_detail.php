<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){

    $user_email = $_POST['user_email'];
    $username_user = $_POST['username_user'];
    $user_bmi = $_POST['user_bmi'];
    $user_age = $_POST['user_age'];
    $user_act_lvl = $_POST['user_act_lvl'];
    $user_gender = $_POST['user_gender'];
    //$user_profile_pic = $_POST['user_profile_pic'];


    include 'connect.php';

    $sql = "UPDATE user SET username_user='$username_user', user_bmi='$user_bmi',user_age='$user_age',user_act_lvl='$user_act_lvl',user_gender='$user_gender' WHERE user_email='$user_email' ";

    if(mysqli_query($conn, $sql)) {

          $result["success"] = "1";
          $result["message"] = "success";

          echo json_encode($result);
          mysqli_close($conn);
      }
  }

else{

   $result["success"] = "0";
   $result["message"] = "error!";
   echo json_encode($result);

   mysqli_close($conn);
}

?>


