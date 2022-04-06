<?php
require_once 'connection.php';

if ($con){
    $id = $_POST['id'];
    $nama = $_POST['namamobil'];
    $harga = $_POST['harga'];
    $getdata = "SELECT * FROM listmobil WHERE idList='$id'";

    if($id != ""){
        $result =  mysqli_query($con, $getdata);
        $rows = mysqli_num_rows($result);
        $response = array();

        if($rows > 0){
            $update = "UPDATE listmobil SET namamobil = '$nama', 
                                        hargasewa = '$harga' 
                                    WHERE idList = '$id'";
            $exequery = mysqli_query($con, $update);

            if ($exequery){
                array_push($response, array(
                    'status' => 'OK'

                ));
            }else{
                array_push($response, array(
                    'status' => 'FAILED1'

                ));
            }

        }else{
            array_push($response, array(
                'status' => 'FAILED2'

            ));

        }

    }else{
        array_push($response, array(
            'status' => 'FAILED3'

        ));

    }   
    
}else{
    array_push($response, array(
        'status' => 'FAILED4'
    ));
}

echo json_encode(array("server_response"=>$response));
mysqli_close($con);
?>