<?php

if ($_SERVER['REQUEST_METHOD'] =='POST'){

    $user_email = $_POST['user_email'];
    $user_bmi = $_POST['user_bmi'];

    include 'connect.php';

    $sql = "UPDATE user SET user_bmi='$user_bmi' WHERE user_email='$user_email' ";

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