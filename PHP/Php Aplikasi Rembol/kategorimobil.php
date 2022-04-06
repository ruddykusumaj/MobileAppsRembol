<?php
require_once "conn.php";
$sql = "select * from mobil";
if(!$conn->query($sql)){
    echo "Error in connecting to Database.";
}else{
    $result = $conn->query($sql);
    if($result->num_rows > 0){
        $return_arr['mobil'] = array();
        while($row = $result->fetch_array()){
            array_push($return_arr['mobil'],array(
                'idMobil' =>$row['idMobil'],
                'jenismobil' =>$row['jenismobil']
            ));
        }
        echo json_encode($return_arr);
    }
}
?>
