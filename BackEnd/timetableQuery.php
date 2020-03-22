<?php 

//Waiting until the the date has been post to the website 
include("conn.php");
$date = $_POST["date"];

$result = mysqli_query($conn, "SELECT * FROM timetable WHERE Date = '$date'");

//Making a json array to store the event's activity into
$resultArray = array();
while($row = mysqli_fetch_array($result)){
    array_push($resultArray, array(
        'Date' =>$row['Date'],
        'Activity' => $row['Activity'],
       'Description' => $row['Description'],
       'Start' => $row['Start'],
       'End' => $row['End'],
       'Room' => $row['Room'],
       'Staff' => $row['Staff'],
        )
    ); 
}

//Displaying the data
echo json_encode($resultArray);




$conn ->close();



?>