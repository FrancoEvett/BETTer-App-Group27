<?php 

require "conn.php";



// Created a function 
Function sendPushNotification($to ='', $data = array()){

//getting key from firebase webserver
$apiKey = 'AIzaSyCAzpp_5mY0Y2Mp3fNKS3dkxM9yXwmNtC0';
//make an array [Json format style, for the sending to user and messgage]
$fields = array('to' => $to, 'notification' => $data);

//Authrisation to the webserver 
$headers = array ('Authorization: key ='.$apiKey, 'Content-Type: application/json');

//location of webserver [Google FireBase]
$url = 'https://fcm.googleapis.com/fcm/send';

$ch = curl_init();
curl_setopt($ch, CURLOPT_URL, $url);
curl_setopt($ch, CURLOPT_POST, true);
curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
$result = curl_exec($ch);
curl_close($ch);

//Decoding/reformarring the json data
return json_decode($result, true);


}



//Getting the data from the timetable and then checking for the StartTime and getting the lecture type;
$currentDate = date("d-m-yy");
$currentTime = date("H") + 1; //Increasing the time by an hour to find the next lecture/Lab/Activity
$lecture = "";
$startTime = "";
$myResult = mysqli_query($conn, "SELECT * FROM timetable WHERE Date = '$currentDate' AND Start LIKE '$currentTime%'" );

//checking if there is any results return from the databaase;
if ($myResult->num_rows > 0) {
    // output data of each row using while loop if more than 1
    while($row = $myResult->fetch_assoc()) {
        echo "Activity: " . $row["Activity"]."<br>";
        $lecture = $row["Activity"];
        $startTime = $row["Start"];
    }
}else{
 die(); //if nothing is found then stop the script here, which in while not send the notififcation
    
}


//Giving user the time left to their activity in mintues// for their activity
   $strStart = date("H:i");
   $dteStart = new DateTime($strStart);
   $dteEnd   = new DateTime($startTime);
   $dteDiff  = $dteStart->diff($dteEnd);
   $differenceTime = $dteDiff->format("%I");

//This is they body of the notification showing the message
$data = array(
    'body' => 'You Have ' .$lecture. ' in ' .$differenceTime. ' Minutes');


//sql query to get all the tokens from the table 
$result = mysqli_query($conn, "SELECT * FROM userLogin");

//Go through all the users/tokens one by one sending notfiications one at a user
while ($row_users = mysqli_fetch_assoc($result)) {
    $token = $row_users["userToken"];
    $to = $token;
    (sendPushNotification($to, $data));
}



?>