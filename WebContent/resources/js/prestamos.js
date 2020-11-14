function toggle_alta_form(){
	if(document.getElementById("btn-alta").innerHTML == "Agregar Linea"){
		document.getElementById("form-alta").hidden=false;
		document.getElementById("btn-alta").innerHTML="Cerrar";
		document.getElementById("btn-alta").className="btn btn-outline-secondary";
	}
	else{
		document.getElementById("form-alta").hidden=true;
		document.getElementById("btn-alta").innerHTML="Agregar Linea";
		document.getElementById("btn-alta").className="btn btn-primary";
	}
}


function eliminar(id){
	inputID = document.getElementById("inputID-del");
	inputID.value = id;
	document.getElementById("form-del").submit();
}
