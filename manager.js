var socket = new WebSocket("ws://" + window.location.hostname + ":1899");

var dictionary;
$().get('define.json' function(data, status){
	dictionary = JSON.parse(data);
});


socket.onMessage = function(data){
	data = JSON.parse(data.data);
	if(data.type == "error"){
		//deal with this later
		console.log(data);
	}else if(data.type == "delta"){
		for(var key in data){
			if(key == "type"){
				continue;
			}
			if(dictionary[key]){
				for(var pair in data[key]){
					if(!dictionary[key][pair] || !dictionary[key][pair]["server-writable"]){
						socket.send("{'type':'error','message':'You shouldn\\'nt be able to write to that'}");
						return;
					}
				}
			}else{
				socket.send("{'type':'error','message':'You shouldn\\'nt be able to write to that'}");
			}
		}
		for(var key in data){
			if(key == "type"){
				continue;
			}
			for(var pair in key)
				dictionary[key][pair] = data[key][pair];
		}
		data.type = "confirm";
		socket.send(JSON.stringify(data));
	}else if(data.type == "confirm"){
		for(var key in data){
			for(var key in data){
			if(key == "type"){
				continue;
			}
			if(dictionary[key]){
				for(var pair in data[key]){
					if(!dictionary[key][pair] || dictionary[key][pair]["server-writable"]){
						socket.send("{'type':'error','message':'I never sent that'}");
						return;
					}
				}
			}else{
				socket.send("{'type':'error','message':'I never sent that'}");
			}
		}
		for(var key in data){
			if(key == "type"){
				continue;
			}
			for(var pair in key)
				dictionary[key][pair] = data[key][pair];
		}
		}
	}
}