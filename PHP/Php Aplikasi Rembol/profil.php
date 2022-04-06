<?php
require "conn.php";
if(isset($_GET['username'])){
    $sql="select * from users where id=(select id from users where username='".$_GET['username']."')";
    if(!$conn->query($sql)){
        echo "Error in executing query.";
    }else{
        $result = $conn->query($sql);
        if($result->num_rows > 0){
            $return_arr['users'] = array();
            while($row = $result->fetch_array()){
                array_push($return_arr['users'],array(
                    'full'=>$row['fullname'],
                    'username'=>$row['username'],
                    'email'=>$row['email'],
                    'iduser'=>$row['id']
                ));
            }
            echo json_encode($return_arr);
        }
    }
}
?>
