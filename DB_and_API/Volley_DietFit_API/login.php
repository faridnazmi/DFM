<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {

    $user_email = $_POST['user_email'];
    $user_pwd = $_POST['user_pwd'];

    include 'connect.php';

    $sql = "SELECT * FROM user WHERE user_email ='$user_email'";

    $response = mysqli_query($conn, $sql);

    $result = array();
    $result['login'] = array();
    
    if ( mysqli_num_rows($response) === 1 ) {
        
        $row = mysqli_fetch_assoc($response);

        if ( password_verify($user_pwd, $row['user_pwd']) ) {
            
            $index['user_email'] = $row['user_email'];
            $index['username_user'] = $row['username_user'];
            $index['user_bmi'] = $row['user_bmi'];
            $index['user_age'] = $row['user_age'];
            $index['user_gender'] = $row['user_gender'];
           

            array_push($result['login'], $index);

            $result['success'] = "1";
            $result['message'] = "success";
            echo json_encode($result);

            mysqli_close($conn);

        } else {

            $result['success'] = "0";
            $result['message'] = "error";
            echo json_encode($result);

            mysqli_close($conn);

        }

    }

}

?>