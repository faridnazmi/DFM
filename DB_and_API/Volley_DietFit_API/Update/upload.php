<?php

if($_SERVER['REQUEST_METHOD'] == 'POST') {

    $user_email = $_POST['user_email'];
    $user_profile_pic = $_POST['user_profile_pic'];

    $path = "profile_image/$user_email.jpeg";
    $finalPath = "http://192.168.100.196/Volley_Dietfit/".$path;

    include 'connect.php';

    $sql = "UPDATE user SET user_profile_pic='$finalPath' WHERE user_email='$user_email' ";

    if (mysqli_query($conn, $sql)) {
        
        if ( file_put_contents( $path, base64_decode($user_profile_pic) ) ) {
            
            $result['success'] = "1";
            $result['message'] = "success";

            echo json_encode($result);
            mysqli_close($conn);

        }

    }

}

?>