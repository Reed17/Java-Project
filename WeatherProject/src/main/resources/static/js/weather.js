

$(document).ready(function() {
  
	 $("#search-form").submit(function (e) {
		 e.preventDefault();
		 var city = document.getElementById('city').value;
		 $.ajax({
	            url : '/weather/' + city,
	             success : function(data) {
	                $('#result').html(data.city + " " + data.country + "<br>" +
	                data.temperature + "°С temperature from " + data.temperatureMin + " to " + data.temperatureMax
	                + " °С. wind " + data.windSpeed + "m/s, clouds " + data.humidity + "% <br/>" 
	                + "Geo coords [" + data.longitud + "," + data.latitud + "]");
	            },
	           error : function(e)
		 		{
		 			 $('#result').html("city not found")
		 		}
	        });

	    });
	
});
