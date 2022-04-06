

<?php
require_once 'connection.php';

if ($con) {
    $id = $_POST['idmobils'];

    $idorder = $_POST['idorder'];

    $insert = "UPDATE listmobil SET status = 'available' WHERE idList='$id'";


    if ($id != "") {
        $result = mysqli_query($con, $insert);
        $response = array();
        if ($result) {
            $update = "UPDATE orders SET statusrent = 'selesai' WHERE idorder = '$idorder'";

            if ($idorder != "") {
                $result2 = mysqli_query($con, $update);
                $response = array();
                if ($result2) {
                    array_push($response, array(
                        'status' => 'OK'
                    ));
                } else {
                    array_push($response, array(
                        'status' => 'FAILED'

                    ));
                }
            } else {
                array_push($response, array(
                    'status' => 'FAILED0'
                ));
            }
        } else {
            array_push($response, array(
                'status' => 'FAILED1'
            ));
        }
    } else {
        array_push($response, array(
            'status' => 'FAILED2'
        ));
    }
} else {
    array_push($response, array(
        'status' => 'FAILED3'
    ));
}

echo json_encode(array("server_response" => $response));
mysqli_close($con);
?>