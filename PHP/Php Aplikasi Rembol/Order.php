

<?php
require_once 'connection.php';

if ($con) {
    $iduser = $_POST['iduser'];
    $idList = $_POST['idlist'];
    $alamat = $_POST['alamat'];
    $telp = $_POST['telp'];
    $mobil = $_POST['mobil'];
    $tanggal = $_POST['tanggal'];
    $harga = $_POST['harga'];
    $book = "Booked";
    $insert = "INSERT INTO orders(idList,id,alamat,NoTelp,Tanggal) VALUES('$idList','$iduser','$alamat','$telp','$tanggal')";


    if ($iduser != "") {
        $result = mysqli_query($con, $insert);
        $response = array();
        if ($result) {
            $update = "UPDATE listmobil SET status = '$book' WHERE idList = '$idList'";

            if ($idList != "") {
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