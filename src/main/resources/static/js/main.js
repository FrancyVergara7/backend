window.addEventListener('load', () => {
    title.textContent = "Bienvenido"
    accion.textContent = ""
    contenido.innerHTML = "Selecciona una opción del menu para iniciar"
})

function renderizarOpcion(opcion) {
    title.textContent = opcion.titulo
    accion.textContent = opcion.accion.textContent
    contenido.innerHTML = opcion.contenido
    if (accion.textContent == "listar") {
        switch (opcion.titulo) {
            case "Odontólogo":
                getOdontologos().then(tabla => contenido.innerHTML = tabla)
                break
            case "Paciente":
                getPacientes().then(tabla => contenido.innerHTML = tabla)
                break
            case 'Turno':
                getTurnos().then(tabla => contenido.innerHTML = tabla)
                break
        }
    }
}

function habilitarBoton(titulo) {
    let formulario = document.forms[0]; // Agarro el formulario
    formulario.addEventListener("submit", (e) => {
    e.preventDefault();
    switch(titulo){
        case "Odontólogo":
            let o = {
            nombre: document.querySelector("#crearOdontologo_nombre").value,
            apellido: document.querySelector("#crearOdontologo_apellido").value,
            matricula: document.querySelector("#crearOdontologo_matricula").value,
            }
            crearOdontologo(o);
            alert("¡Creaste un Odontólogo!")
            document.querySelector("#crearOdontologo_nombre").value = "";
            document.querySelector("#crearOdontologo_apellido").value = "";
            document.querySelector("#crearOdontologo_matricula").value = "";
            break;
        case "Paciente":
            let p = {nombre: document.querySelector("#crearPaciente_nombre").value,
            apellido: document.querySelector("#crearPaciente_apellido").value,
            dni: document.querySelector("#crearPaciente_DNI").value
            }
            crearPaciente(p);
            alert("¡Creaste un Paciente!");
            document.querySelector("#crearPaciente_nombre").value = "";
            document.querySelector("#crearPaciente_apellido").value = "";
            document.querySelector("#crearPaciente_DNI").value = "";
            break;
        case "Turno":
            "Método Crear Turnos"
            break;
        }
    })
}

for (let item of menu) {
    item.accion.addEventListener('click', ()=>{
        renderizarOpcion(item)
        if (item.accion.textContent == "crear") {habilitarBoton(item.titulo)}
    })
}