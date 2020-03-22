<?php
require "conn.php";

//POST METHOD TO GET DATA when the user tried to register for first time
$studentID = $_POST["studentID"];
$userName = $_POST["userName"];
$userEmail = $_POST["userEmail"];
$userPassword = $_POST["userPassword"];
$userToken = $_POST["userToken"];



// First checking if the user already exist in the database by using their studentID and userEmail
$mysql_qry = "SELECT * FROM userLogin WHERE studentID like '$studentID' AND userEmail = '$userEmail';";

$result = mysqli_query($conn, $mysql_qry);

if (mysqli_num_rows($result) > 0) {
    echo "User Exist"; //User already therefore exit 
} else {
    //If user doesn't exist then added their details to the database
    $mysql_reg_query = "INSERT INTO userLogin (studentID, userName, userEmail, userPassword, userToken) VALUES ('$studentID', '$userName', '$userEmail', '$userPassword', '$userToken')";

//Info the user if there data has been added
    if ($conn->query($mysql_reg_query) === TRUE) {
        echo "User Added"; //Data added to the databases
    } else {
        echo "Error";
    }
}

//CLOSING CONNECTION
$conn->close();
