<?php 

include 'connect.php';

$type = $_GET['item_type'];

if (isset($_GET['a'])) {
    $key = $_GET["a"];
    if ($type == 'meals') {
        $query = "SELECT * FROM list_meals WHERE meals_name LIKE '%$a%'";
        $result = mysqli_query($conn, $query);
        $response = array();
        while( $row = mysqli_fetch_assoc($result) ){
            array_push($response, 
            array(
                'meals_name'=>$row['meals_name'], 
                'food_type'=>$row['food_type'], 
                'calories_info'=>$row['calories_info']) 
            );
        }
        echo json_encode($response);   
    }
} else {
    if ($type == 'meals') {
        $query = "SELECT * FROM list_meals";
        $result = mysqli_query($conn, $query);
        $response = array();
        while( $row = mysqli_fetch_assoc($result) ){
            array_push($response, 
            array(
                'meals_name'=>$row['meals_name'], 
                'food_type'=>$row['food_type'], 
                'calories_info'=>$row['calories_info']) 
            );
        }
        echo json_encode($response);   
    }
}

mysqli_close($conn);

?>