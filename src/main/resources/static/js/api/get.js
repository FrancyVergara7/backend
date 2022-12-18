function getPacientes() {
    return fetch("v1/paciente/all")
    .then(response => response.json())
    .then(lista => {
        let cargarFilaPacientes = ""
        for (let pacientes of lista){
        cargarFilaPacientes += `<tr>
                                        <th scope="row">${pacientes.id}</th>
                                        <td>${pacientes.nombre}</td>
                                        <td>${pacientes.apellido}</td>
                                        <td>${pacientes.dni}</td>
                                        <td>${pacientes.domicilio}</td>
                                        <td><button class="btn btn-primary me-1">Actualizar</button><button class="btn btn-primary">Eliminar</button></td>
                                  </tr>`
        }
        return  `<table class="table table-hover">
        <thead>
        <tr>
          <th scope="col">Id</th>
          <th scope="col">Nombre</th>
          <th scope="col">Apellido</th>
          <th scope="col">DNI</th>
          <th scope="col">Domicilio</th>
          <th scope="col">Acción</th>
        </tr>
        </thead>
        <tbody>
        ${cargarFilaPacientes}
        </tbody>
      </table>`
        })
}

function getOdontologos() {
    return fetch("v1/odontologo/all")
    .then(response => response.json())
    .then(lista => {
        let cargarFilaOdontologo = "";
        for (let odontologo of lista){
        cargarFilaOdontologo += `<tr>
                                        <th scope="row">${odontologo.id}</th>
                                        <td>${odontologo.nombre}</td>
                                        <td>${odontologo.apellido}</td>
                                        <td>${odontologo.matricula}</td>
                                        <td><button class="btn btn-primary me-1">Actualizar</button><button class="btn btn-primary">Eliminar</button></td>
                                  </tr>`
        }
        return  `<table class="table table-hover">
        <thead>
        <tr>
          <th scope="col">Id</th>
          <th scope="col">Nombre</th>
          <th scope="col">Apellido</th>
          <th scope="col">Matrícula</th>
          <th scope="col">Acción</th>
        </tr>
        </thead>
        <tbody>
            ${cargarFilaOdontologo}
        </tbody>
      </table>`
        })
}

function getTurnos() {
    return fetch("v1/turno/all")
    .then(response => response.json())
    .then(lista => {
        let cargarFilaTurno = "";
        for (let turno of lista){
        cargarFilaTurno += `<tr>
                                        <th scope="row">${turno.fecha}</th>
                                        <td>${turno.hora}</td>
                                        <td>${turno.paciente}</td>
                                        <td>${turno.odontologo}</td>
                                        <td><button class="btn btn-primary">Eliminar</button></td>
                                  </tr>`
        }
        return  `<table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">Fecha</th>
            <th scope="col">Hora</th>
            <th scope="col">Paciente</th>
            <th scope="col">Odontologo</th>
            <th scope="col">Acción</th>
        </tr>
        </thead>
        <tbody>
            ${cargarFilaTurno}
        </tbody>
      </table>`
    })
}
