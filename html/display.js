//var socket = new WebSocket("ws://" + window.location.hostname + ":1899");
var socket = new WebSocket("ws://127.0.0.1:1899");
/*var socket = {
	send: function(string){
		console.log("fake socket message : " + JSON.stringify(string));
	}
}*/
var events = {};
function sendData(event){
	var name = $(this).attr("name");
	var value = $(this).val();
	var dataobj = {
		name:name,
		value:value
	};
	socket.send(JSON.stringify(dataobj));
}
socket.onmessage = function(message){
	console.log("recv: " + message.data);
	var eventList = [].concat(JSON.parse(message.data));
	
	for(var event of eventList){
		if(event.type == "message"){
			$("#debug").append($("<p>" + event.data + "</p>"));
			return;
		}
		var line = events[event.name] || $("<div></div>")
				.addClass("displayObject")
				.css(event.style || {});
		if((event.type || line.type) == "output"){
			line.type = "output";
			line.text(event.name + " : " + event.data);
		}else if((event.type||line.type) == "input"){
			line.text(event.name + " : ");
			line.append($("<input></input>")
						.prop("type","text")
						.val(event.data)
						.attr("name",event.name)
						.on("blur", sendData)
						.on("keyup", function(event){
								if(event.keyCode == 13)
									$(this).blur();
						}));
		}else{
			console.log(event);
		}
		events[event.name] = line;
		line.appendTo("body");
	}
	
}