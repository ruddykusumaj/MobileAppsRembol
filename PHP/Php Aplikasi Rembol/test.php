<?php
require "conn.php";
if(isset($_GET['namamobil'])){
    $sql="SELECT idList, namamobil, hargasewa from listmobil where idList=(select idList from listMobil where namamobil='".$_GET['namamobil']."') ";
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

