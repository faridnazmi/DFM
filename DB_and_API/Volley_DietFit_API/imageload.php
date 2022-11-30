<?php

include 'connect.php';


$user_email  = $_GET['user_email'];
 
$sql = "SELECT user_profile_pic FROM user WHERE user_email = '$user_email'";
 
$res = mysqli_query($conn,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,array('user_profile_pic'=>$row[1]));
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($con);
 
?>
