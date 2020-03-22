<?php 

//Making basic connection to the database
$db_name = "timetable";
$mysql_username = "root";
$mysql_pass = "password";
$server_name = "localhost";

//Making a connection
$conn = mysqli_connect($server_name, $mysql_username, $mysql_pass, $db_name);
?>
