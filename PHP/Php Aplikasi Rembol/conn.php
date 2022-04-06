<?php
$server="localhost";
$username="root";
$password="";
$database = "rembol";
$conn = mysqli_connect($server,$username,$password,$database);
if(!$conn)
    die("Connection Failed".mysqli_connect_errno);
?>