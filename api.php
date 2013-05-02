<?php




//
// return status code '2' (user not found) if no creds are provided
//


$payload = array(
	'game_token' => 'nikeplusaccelerator',
	'target_game_token' => 1,
	'user_name' => $_REQUEST['user_name'],
	'user_pw' => $_REQUEST['user_pw'],
	'function' => 'get',
	'par' => 'steps'
);

$ch = curl_init("https://geopalz.com/games/request.php");
	curl_setopt($ch, CURLOPT_POST, true);
	curl_setopt($ch, CURLOPT_POSTFIELDS, $payload);
	curl_setopt($ch, CURLOPT_HEADER, false);
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

$response = curl_exec($ch);

$result = json_decode($response,true);


$free_time = -1;

if(isset($result['time_left']))
	$free_time = $result['time_left'];


echo $free_time;

?>
