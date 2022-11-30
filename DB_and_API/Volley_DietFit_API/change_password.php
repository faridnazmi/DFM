<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){

    $user_email = $_POST['user_email'];
    $user_pwd = $_POST['user_pwd'];

    //$user_profile_pic = $_POST['user_profile_pic'];

     $user_pwd = password_hash($user_pwd, PASSWORD_DEFAULT);

    include 'connect.php';

    $sql = "UPDATE user SET user_pwd='$user_pwd' WHERE user_email='$user_email' ";

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


