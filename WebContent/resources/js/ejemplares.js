
function toggle_alta_form(){
	if(document.getElementById("btn-alta").innerHTML == "Agregar"){
		document.getElementById("form-alta").hidden=false;
		document.getElementById("btn-alta").innerHTML="Cerrar";
		document.getElementById("btn-alta").style.width="83px";
		document.getElementById("btn-alta").className="btn btn-outline-secondary";
	}
	else{
		document.getElementById("form-alta").hidden=true;
		document.getElementById("btn-alta").innerHTML="Agregar";
		document.getElementById("btn-alta").className="btn btn-outline-primary";
	}
}

function toggle_baja_form(){
	if(document.getElementById("btn-baja").innerHTML == "Eliminar"){
		document.getElementById("form-baja").hidden=false;
		document.getElementById("btn-baja").innerHTML="Cerrar";
		document.getElementById("btn-baja").style.width="83px";
		document.getElementById("btn-baja").className="btn btn-outline-secondary";
	}
	else{
		document.getElementById("form-baja").hidden=true;
		document.getElementById("btn-baja").innerHTML="Eliminar";
		document.getElementById("btn-baja").className="btn btn-outline-danger";
	}
}

function selectLibro(){
	var s = document.getElementById("inputLibro").value;
	var site = "/libreria-java/admin/ejemplares?id="+s;
	window.location.href = site;
}