<?php

require "conn.php";
//Getting the user ID when user tries to log into the app
$studentID = $_POST["studentID"];

//Search for the specific user using there studentID
$result = mysqli_query($conn, "SELECT * FROM userLogin WHERE studentID = '$studentID'");

//If user is found then make a json array and store their info into appropriate variables
if (mysqli_num_rows($result) > 0) {
    $resultArray = array();
    while ($row = mysqli_fetch_array($result)) {
        array_push(
            $resultArray,
            array(
                'studentID' => $row['studentID'],
                'userName' => $row['userName'],
                'userEmail' => $row['userEmail'],
                'userPassword' => $row['userPassword'],
                'token' => $row['token'],

            )
        );
    }
    //echo the data
    echo json_encode($resultArray);
} else {
    echo "User Doesn't Exist";
}
//Closing the connection from database
$conn->close();

?>
