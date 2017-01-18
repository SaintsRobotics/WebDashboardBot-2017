
<motors>
<div class="flex-container">
<h2> motors </h2>
	<div  class="flex-item" id="motoritem" each={ motorValues }>
		<p class="wait">

		<form>
		<h3 id="motorH3">{ title }</h3>
			<input type="text" name="valueEdit1" id="textInput" placeholder="{ value }" oninput="rangeInput.value = textInput.value;submitter.style.display= 'initial'"></input>
			<input type="button" id="submitter" name="sub1" class="submitVal" value="submit" onclick={ parent.getVal }></input>
		</form>
		</p>
	</div>
	</div>

    <script type="text/javascript" src="./define.json"></script>
	 <script>
	this.motorValues = [];
	
this.on('update', function() {
  if(!this.values) return;
  this.motorValues = [];
    for(var value in this.values.server){
        console.log(value + " has " + this.values.server[value]);
        this.motorValues.push({
          "title": value,
          "value": this.values.server[value]
        });
    }
    console.log(this.motorValues);
  });
	  
	getVal(event) {
			console.log("start getVal");
			var item = event.item;
			var index = this.motorValues.indexOf(item);
			//this.motors[index].value = event.srcElement.previousElementSibling.value;
			this.motorValues[index].value = event.srcElement.previousElementSibling.value;
			var obj={};
			obj[this.motorValues[index]["title"]] = {
					"min": this.motorValues[index]["min"],
					"max": this.motorValues[index]["max"],
					"value": this.motorValues[index]["value"]
			}
			/*console.log("obj");
			console.log(obj);
			console.log("Changing value: " + this.motorValues[index]["title"] + " to " + this.motorValues[index]["value"]);
			console.log("Before sent: ");
			console.log(this.values.server);*/
			this.sendChange(obj);
			/*console.log("Change sent. Server values: ");
			console.log(this.values.server);*/
			//event.srcElement.style.display= "none";
	  }
	  


  </script>
</motors>