
<inputs>
	<div  class="flex-item" each={ inputValues }>
		<p class="wait">

		<form>
		<h3>{ title }</h3>
			<input type="range" name="valueEdit1" id="rangeInput" min="{ min }" max="{ max }" value ="{ value }"  oninput="textInput.value = rangeInput.value;submitter.style.display= 'initial'" class="slider"></input>
			<input type="text" name="valueEdit1" id="textInput" placeholder="{ value }" oninput="rangeInput.value = textInput.value;submitter.style.display= 'initial'"></input>
			<input type="button" id="submitter" name="sub1" class="submitVal" value="submit" onclick={ parent.getVal }></input>
		</form>
		</p>
	</div>

    <script type="text/javascript" src="./define.json"></script>
	 <script>
	this.inputValues = [];
	
	this.on('update', function() {
		console.log("update");
		if(!this.values) return;
		this.inputValues = [];
	    for(var value in this.values.server){
    	    console.log(value + " has " + this.values.server[value]);
        	this.inputValues.push({
        		"title": value,
        		"max": this.values.server[value]["max"],
        		"min": this.values.server[value]["min"],
        		"value": this.values.server[value]["value"]
        	});
    	}
    	console.log(this.inputValues);
	});
	  
	getVal(event) {
			console.log("start getVal");
			var item = event.item;
			var index = this.inputValues.indexOf(item);
			//this.inputs[index].value = event.srcElement.previousElementSibling.value;
			for(var value in this.values.server){
        		console.log(value + " is " + this.values.server[value]);
        		if(value = item.title){
        			this.values.server[value]["value"] = event.srcElement.previousElementSibling.value;
        		}
			}
			console.log(this.values.server);
			//event.srcElement.style.display= "none";
	  }
	  


  </script>
</inputs>