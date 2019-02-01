$("[name='inputPassword'], [name='confirm_password']").on('keyup', function () {
  if ($("[name='inputPassword']").val() == $("[name='confirm_password']").val()) {
	  document.getElementById("1").className = "form-control is-valid";
	  document.getElementById("2").className = "form-control is-valid";
	  $(':input[type="submit"]').prop('disabled', false);

  } else {
	  document.getElementById("1").className = "form-control is-invalid";
  	document.getElementById("2").className = "form-control is-invalid";
  	$(':input[type="submit"]').prop('disabled', true);
  }
});