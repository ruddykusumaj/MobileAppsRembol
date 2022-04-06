<?php
require_once 'connection.php';

if ($con){
    $id = $_POST['id'];
    $namamobil = $_POST['namamobil'];
    $harga = $_POST['harga'];
    $stat = $_POST['status'];
    $insert = "INSERT INTO listmobil(idMobil, namamobil, hargasewa, status) VALUES('$id','$namamobil','$harga','$stat')";

    if ($id != ""){
        $result = mysqli_query($con, $insert);
        $response = array();
        if ($result){
            array_push($response, array(
                'status' => 'OK'
            ));
        }else{
            array_push($response, array(
                'status' => 'FAILED'
            ));

        }
    }else{
        array_push($response, array(
            'status' => 'FAILED'
        ));
    

    }
    
}else{
    array_push($response, array(
        'status' => 'FAILED'
    ));
}

echo json_encode(array("server_response"=>$response));
mysqli_close($con);
?>