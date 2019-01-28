function showLines() {
	var phoneId = document.getElementById('phone-id');
	id = phoneId.value;
	$.ajax({
		url: '/vendor/count/',
		type: 'POST',
		data: id,
		contentType: 'application/json',
		success: function(data) {
			for (var i = 1; i <= 6; i++) {
				$('#line' + i).addClass('collapse');
				//$('label.line' + i + '_params').addClass('hide-input');
			}

			for (var i = 1; i <= data; i++) {
				$('#line' + i).removeClass('collapse');
				//$('label.line' + i + '_params').removeClass('hide-input');
			}
		}
	})
}

$(document).ready(function() {
		
	showLines();
	

})