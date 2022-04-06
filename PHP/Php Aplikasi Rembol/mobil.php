<?php
require "conn.php";
if(isset($_GET['jenismobil'])){
    $sql="select idList, namamobil, hargasewa from listmobil where status = 'available' AND idMobil=(select idMobil from mobil where jenismobil='".$_GET['jenismobil']."')";
    if(!$conn->query($sql)){
        echo "Error in executing query.";
    }else{
        $result = $conn->query($sql);
        if($result->num_rows > 0){
            $return_arr['listmobil'] = array();
            while($row = $result->fetch_array()){
                array_push($return_arr['listmobil'],array(
                    'idList'=>$row['idList'],
                    'namamobil'=>$row['namamobil'],
                    'hargasewa'=>$row['hargasewa']
                ));
            }
            echo json_encode($return_arr);
        }
    }
}

