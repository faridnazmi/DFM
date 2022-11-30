<?php


include "connect.php";
$sql = "SELECT * FROM health_tips";
$result = mysqli_query($conn,$sql);
$tips = array();

while($row = mysqli_fetch_assoc($result)) {
    $index['tips_id'] = $row['tips_id'];
    $index['tips_name'] = $row['tips_name'];
    $index['tips_type'] = $row['tips_type'];
    $index['tips_desc'] = $row['tips_desc'];
    $index['tips_reference'] = $row['tips_reference'];

    array_push($tips,$index);
}
echo json_encode($tips);
?>