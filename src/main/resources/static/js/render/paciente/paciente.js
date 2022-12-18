const form_crearPaciente = '<form> <div class="mb-3"> <label for="crearPaciente_nombre" class="form-label">Nombre</label> <input type="text" class="form-control" id="crearPaciente_nombre" placeholder="Nombre"> </div> <div class="mb-3"> <label for="crearPaciente_apellido" class="form-label">Apellido</label> <input type="text" class="form-control" id="crearPaciente_apellido" placeholder="Apellido"> </div> <div class="mb-3"> <label for="crearPaciente_DNI" class="form-label">DNI</label> <input type="text" class="form-control" id="crearPaciente_DNI" placeholder="11222333"> </div> <input type="submit" value="crear paciente"> </form>'

const verPacientes = `<table class="table table-hover w-100">
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
<tr>
  <th scope="row"><strong>Loading...</strong></th>
  <td><div class="spinner-border ms-auto" role="status" aria-hidden="true"></div></td>
  <td><div class="spinner-border ms-auto" role="status" aria-hidden="true"></div></td>
  <td><div class="spinner-border ms-auto" role="status" aria-hidden="true"></div></td>
  <td><div class="spinner-border ms-auto" role="status" aria-hidden="true"></div></td>
  <td>
    <button class="btn btn-primary" type="button" disabled>
      <span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
      <span class="visually-hidden">Loading...</span>
    </button>
  </td>
</tr>
</tbody>
</table>`