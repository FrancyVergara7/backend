package com.dh.controller;

import com.dh.dto.PacienteDto;
import com.dh.exceptions.BadRequestException;
import com.dh.exceptions.NotContentException;
import com.dh.exceptions.NotFoundExceptions;
import com.dh.service.impl.PacienteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private static final Logger LOG = LogManager.getLogger(PacienteController.class);

    PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/create")
    public ResponseEntity<PacienteDto> crearNuevoPaciente(@RequestBody PacienteDto pacienteDto) throws BadRequestException, NotFoundExceptions {
        if (pacienteDto.nombre == null || pacienteDto.nombre.equals(" ") || pacienteDto.apellido == null || pacienteDto.apellido.equals(" ")) {
            LOG.error("No se puede crear un paciente con datos vacios o nulos");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        LOG.info("Creando Paciente: " + pacienteDto.nombre + " " + pacienteDto.apellido);
        return ResponseEntity.ok(pacienteService.crearPaciente(pacienteDto));
    }

    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<PacienteDto> buscarPacientePorId(@PathVariable Long id) throws NotFoundExceptions {
        if (id == null) {
            LOG.info("Debe ingresar un id válido");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            PacienteDto pacienteEncontrado = pacienteService.buscarPacientePorId(id);
            LOG.info("Se encontró el paciente con el id " + id);
            return ResponseEntity.ok(pacienteEncontrado);
        }
    }

    @PutMapping("/modificar")
    public ResponseEntity<PacienteDto> modificarPacientePorId(@RequestBody PacienteDto pacienteDto) throws NotFoundExceptions {
        if (pacienteDto.getId() == null) {
            LOG.info("Debe ingresar un id válido");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        LOG.info("Se actualizó el paciente con el id" + pacienteDto.getId());
        return ResponseEntity.ok(pacienteService.modificarPaciente(pacienteDto));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws NotFoundExceptions {
        if (id == null) {
            LOG.info("No se encontró el paciente con el id " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        LOG.info("Se eliminó el paciente con el id " + id);
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.status(HttpStatus.OK).body("Se eliminó el paciente con el id " + id);
    }

    @GetMapping("/lista")
    public ResponseEntity<Collection<PacienteDto>> getTodos() throws NotContentException {
        Set<PacienteDto> listaDePacientes = pacienteService.listarPaciente();
        if (listaDePacientes.isEmpty()) {
            LOG.info("No hay ningùn paciente registrado");
        }
        LOG.info("Se encontraron " + listaDePacientes.size() + " pacientes");
        return ResponseEntity.ok(listaDePacientes);
    }
}
