<?php
require_once 'connection.php';

$username = $_GET['username'];
$query = "SELECT orders.idorder, orders.id, orders.idList, orders.alamat, orders.NoTelp, orders.statusrent ,listmobil.namamobil  
            FROM orders
            INNER JOIN listmobil 
            ON orders.idList = listmobil.idList
            JOIN users
            ON orders.id = users.id
            WHERE users.username = '$username'";
$result = mysqli_query($con,$query);
$response = array();

while ($row = mysqli_fetch_array($result)){
    array_push($response, array(
        "idorder"=>$row['idorder'],
        "id" => $row['idList'],
        "alamat" => $row['alamat'],
        "NoTelp" => $row['NoTelp'],
        "namamobil"=> $row['namamobil'],
        "status"=>$row['statusrent']
    ));
}

echo json_encode(array("server_response"=>$response));
mysqli_close($con);
?>