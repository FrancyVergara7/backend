const title = document.querySelector("#SeccionTitulo")
const accion = document.querySelector("#SeccionAccion")
const contenido = document.querySelector("#SeccionContenido")
const btnMenuCrearOdontologo = document.querySelector("#btnMenuCrearOdontologo")
const btnMenuListarOdontologos = document.querySelector("#btnMenuListarOdontologos")
const btnMenuCrearPaciente = document.querySelector("#btnMenuCrearPaciente")
const btnMenuListarPacientes = document.querySelector("#btnMenuListarPacientes")
const btnMenuCrearTurno = document.querySelector("#btnMenuCrearTurno")
const btnMenuListarTurnos = document.querySelector("#btnMenuListarTurnos")
const menu = [
{titulo:'Odontólogo',accion:btnMenuCrearOdontologo, contenido:form_crearOdontologo},
{titulo:'Paciente',accion:btnMenuCrearPaciente, contenido:form_crearPaciente},
{titulo:'Turno',accion:btnMenuCrearTurno, contenido:form_crearTurno},
{titulo:'Odontólogo',accion:btnMenuListarOdontologos, contenido:verOdontologos},
{titulo:'Paciente',accion:btnMenuListarPacientes, contenido:verPacientes},
{titulo:'Turno',accion:btnMenuListarTurnos, contenido:verTurnos}
]