function toggle_alta_form(){
	if(document.getElementById("btn-alta").innerHTML == "Agregar"){
		document.getElementById("form-alta").hidden=false;
		document.getElementById("btn-alta").innerHTML="Cerrar";
		document.getElementById("btn-alta").className="btn btn-outline-secondary";
	}
	else{
		document.getElementById("form-alta").hidden=true;
		document.getElementById("btn-alta").innerHTML="Agregar";
		document.getElementById("btn-alta").className="btn btn-primary";
	}
}

var modificando = false;

function modificar(id){
	if (modificando == false){
				
		modificando = true;
		var sid = id.toString();
		oldRow = document.getElementById('row-cat'+sid);
	
		idlbl = document.getElementById("id-lbl-"+sid);
		idlbl.hidden=true;
		inputID = document.getElementById("inputID-"+sid);
		inputID.hidden=false;
		inputID.style.width="50px";
		inputID.disabled=false;
		inputID.readOnly=true;
		inputID.value = idlbl.innerHTML;
		
		deslbl = document.getElementById("des-lbl-"+sid);
		deslbl.hidden=true;
		inputDesc = document.getElementById("inputDesc-"+sid);
		inputDesc.hidden=false;
		inputDesc.disabled=false;
		inputDesc.value = deslbl.innerHTML;
		
		estlbl = document.getElementById("est-lbl-"+sid);
		estlbl.hidden=true;
		inputEstado = document.getElementById("inputEstado-"+sid);
		inputEstado.hidden=false;
		inputEstado.disabled=false;
		inputEstado.value = estlbl.innerHTML;
			
		modbtn = document.getElementById("mod-btn-"+sid);
		newMod = document.createElement('button');
		newMod.className="btn btn-primary";
		newMod.innerHTML="Aceptar";
		modbtn.parentNode.replaceChild(newMod, modbtn);
			
		delbtn = document.getElementById("del-btn-"+sid);
		newDel = document.createElement('button');
		newDel.className="btn btn-secondary";
		newDel.innerHTML="Cancelar";
		newDel.setAttribute('type', 'button');
		newDel.addEventListener("click",function(){console.log("hello");window.location.href = "/libreria-java/admin/listado-cat-libro";})
		delbtn.parentNode.replaceChild(newDel, delbtn);
	}
}

function eliminar(id){
	inputID = document.getElementById("inputID-del");
	inputID.value = id;
	document.getElementById("form-del").submit();
}

