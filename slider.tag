<slider>
	<div class="flex-item" each={ items }>
		<p class="wait">
		
		<form>
		<h3>{ title }</h3>
			<input type="range" name="valueEdit1" id="rangeInput" min="{ min }" max="{ max }" value ="{ value }" oninput="textInput.value = rangeInput.value"  class="slider">
			<input type="text" name="valueEdit1" id="textInput" placeholder="{ value }" oninput="rangeInput.value = textInput.value">
			<input type="submit" name="sub1" class="submitVal" value="submit">
		</form>
		</p>
	</div>
	
	 <script>
    this.items = [
    { title: 'Speed', min: 0, max: 10, value: 5 },
    { title: 'Arm', min: 0, max: 10, value:5 },
    { title: 'Leg', min: 0, max: 100, value:60 }
    ]
    
  </script>
</slider>


