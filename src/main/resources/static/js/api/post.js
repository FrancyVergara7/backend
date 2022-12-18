async function crearPaciente(paciente) {
    return fetch("/paciente", {method:'POST', headers: {'Content-Type':'application/json'}, body: JSON.stringify(paciente)})
    .then(response => response.json())
    .then(data => {
        return data
        })
}

async function crearOdontologo(odontologo) {
    return fetch(/odontologo",
        {method:'POST',
        headers: {'Content-Type':'application/json'},
        body: JSON.stringify(odontologo)})
    .then(response => response.json())
    .then(data => {
        return data
        })
}

async function crearTurno(turno) {
    return fetch("/turno",
        {method:'POST',
        headers: {'Content-Type':'application/json'},
        body: JSON.stringify(turno)})
    .then(response => response.json())
    .then(data => {
        return data
        })
}
