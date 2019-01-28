function showLines() {
	var id;
	var sel = document.getElementById('select-phone');
	id = sel.value;
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

function showIpSettings() {
	var isDhcp;
	var mode = document.getElementById('select-dhcp');	
	isDhcp = mode.value;

	if (isDhcp === 'false') {
		$('.staticIp').removeClass('collapse');
	}
	else if (isDhcp === 'true') {
		$('.staticIp').addClass('collapse');
		$('.staticIp').val('');
	}
}

$(document).ready(function() {
	
//	var sel = document.getElementById('select-phone');
	var mode = document.getElementById('select-dhcp');
	
//	showLines();
		
	showIpSettings();

//	var sel = document.getElementById('select-phone');
//	sel.onchange = function() {
		
//		showLines();
	
//	var mode = document.getElementById('select-dhcp');	
		
//	}
	
	mode.onchange = function() {
		
		showIpSettings();
	}
	


})