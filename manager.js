var socket = new WebSocket("ws://" + window.location.hostname + ":1899");

var dictionary;
$().get('define.json', function(data, status){
	dictionary = JSON.parse(data);
	console.log("dict loaded");
}, true);

socket.onmessage = function(data){
	data = JSON.parse(data.data);
	if(data.type == "error"){
		//deal with this later
		console.log(data);
		return;
	}
	var delta;
	if(data.type=="delta"){
		delta=true;
	}else if(data.type="confirm"){
		delta=false;
	}else{
		error("malformed!");
		return;
	}
	for(var key in data){
		if(key == "type"){
			continue;
		}
		if(dictionary[key]){
			for(var pair in data[key]){
				if(!dictionary[key][pair] || (delta!=dictionary[key][pair]["server-writable"])){
					error("wrong permissions or doesn't exist!");
					return;
				}
			}
		}else{
			error("wrong permissions or doesn't exist!");
			return;
		}
	}
	if(delta){
		data.type = "confirm";
		socket.send(JSON.stringify(data));
	}
	for(var key in data){
		if(key == "type"){
			continue;
		}
		for(var pair in data[key])
			dictionary[key][pair].value = data[key][pair];
	}
}
function testData(message){
	socket.onmessage({data:JSON.stringify(message)});
}
function error(message){
	console.log("Error: " + message);
	socket.send(JSON.stringify({type:"error",message:message}));
}