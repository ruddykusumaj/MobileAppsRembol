<?php
require_once 'connection.php';

$query = "SELECT listmobil.idList, listmobil.idMobil, listmobil.namamobil, listmobil.hargasewa, listmobil.status, mobil.jenismobil 
FROM listmobil
INNER JOIN mobil
ON listmobil.idMobil = mobil.idMobil
ORDER BY listmobil.idMobil";
$result = mysqli_query($con,$query);
$response = array();

while ($row = mysqli_fetch_array($result)){
    array_push($response, array(
        "id" => $row['idList'],
        "idmobil" => $row['idMobil'],
        "namamobil" => $row['namamobil'],
        "hargasewa" => $row['hargasewa'],
        "statusmobil" => $row['status'],
        "kategori" => $row['jenismobil']
    ));
}

echo json_encode(array("server_response"=>$response));
mysqli_close($con);
?>