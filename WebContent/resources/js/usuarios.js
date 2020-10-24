function eliminar(id){
	inputID = document.getElementById("inputID-del");
	inputID.value = id;
	document.getElementById("form-del").submit();
}