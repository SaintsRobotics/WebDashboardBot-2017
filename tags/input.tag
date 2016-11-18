<inputs>
	<div  class="flex-item" each={ inputs }>
		<p class="wait">

		<form>
		<h3>{ title }</h3>
			<input type="range" name="valueEdit1" id="rangeInput" min="{ min }" max="{ max }" value ="{ value }"  oninput="textInput.value = rangeInput.value;submitter.style.display= 'initial'" class="slider"></input>
			<input type="text" name="valueEdit1" id="textInput" placeholder="{ value }" oninput="rangeInput.value = textInput.value;submitter.style.display= 'initial'"></input>
			<input type="button" id="submitter" name="sub1" class="submitVal" value="submit" onclick={ parent.getVal }></input>
		</form>
		</p>
	</div>

	 <script>
    this.inputs = [
    { title: 'Speed', min: 0, max: 10, value: 5 },
    { title: 'Shoulder', min: 0, max:360, value:90},
    { title: 'Elbow', min: 0, max: 10, value:5 },
    { title: 'Leg', min: 0, max: 100, value:60 },
    { title: 'Beauty', min: 0, max:20, value:10 }
    ]


		getVal(event) {
			var item = event.item;
			var index = this.inputs.indexOf(item);
			this.inputs[index].value = event.srcElement.previousElementSibling.value;
			event.srcElement.style.display= "none";

	  }

  </script>
</inputs>
