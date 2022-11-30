<?php

if ($_SERVER['REQUEST_METHOD'] =='POST'){

    $user_email = $_POST['user_email'];
    $username_user = $_POST['username_user'];
    $user_pwd = $_POST['user_pwd'];
    $user_bmi = $_POST['user_bmi'];
    $user_age = $_POST['user_age'];
    $user_act_lvl = $_POST['user_act_lvl'];
    $user_gender = $_POST['user_gender'];
    $malaysian_type = $_POST['malaysian_type'];
    $calories_goal = $_POST['calories_goal'];
    

    $user_pwd = password_hash($user_pwd, PASSWORD_DEFAULT);

    include 'connect.php';

    $sql = "INSERT INTO user (user_email, username_user, user_pwd, user_bmi, user_age, user_act_lvl, user_gender,malaysian_type,calories_goal) 
    VALUES ('$user_email','$username_user', '$user_pwd','$user_bmi','$user_age','$user_act_lvl','$user_gender','$malaysian_type','$calories_goal')";

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