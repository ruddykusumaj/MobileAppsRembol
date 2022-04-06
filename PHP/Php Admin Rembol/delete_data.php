<?php
require_once 'connection.php';

if ($con){
    $id = $_POST['id'];
    $getdata = "SELECT * FROM listmobil";

    if($id != ""){
        $result =  mysqli_query($con, $getdata);
        $rows = mysqli_num_rows($result);
        $response = array();

        if($result){
            $delord = "DELETE FROM orders WHERE idList='$id'";
            $deleteord =  mysqli_query($con, $delord);
            if($result){
                $delete = "DELETE FROM listmobil WHERE idList = '$id'";
                $exequery = mysqli_query($con, $delete);

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