package com.dh.controller;

import com.dh.dto.TurnoDto;
import com.dh.exceptions.BadRequestException;
import com.dh.exceptions.NotFoundExceptions;
import com.dh.service.impl.TurnoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

@Controller
@RequestMapping(path = "/turnos")

public class TurnoController {

    private TurnoService turnoService;
    private static final Logger LOG = LogManager.getLogger(TurnoController.class);

    @Autowired
    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    //Crear turno

    @PostMapping("/create")
    public ResponseEntity<TurnoDto> crearNuevoTurno(@RequestBody TurnoDto turnoDto) throws BadRequestException, ChangeSetPersister.NotFoundException, NotFoundExceptions {
        if (turnoDto.fecha == null || turnoDto.hora == null ){
            LOG.error("No se puede crear un turno sin fecha y hora");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        LOG.info("Creando turno con el id " + turnoDto.getId());
        return ResponseEntity.ok(turnoService.crearTurno(turnoDto));
    }
    //Buscar turno por id

    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<TurnoDto> buscarTurnoPorId(@PathVariable Long id) throws NotFoundExceptions {
        if (id == null) {
            LOG.info("Debe ingresar un id válido");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            TurnoDto turnoEncontrado = turnoService.buscarTurnoPorId(id);
            LOG.info("Se encontró el turno con el id  " + turnoEncontrado.getId());
            return ResponseEntity.ok(turnoEncontrado);
        }
    }

    //Modificar turno
    @PutMapping("/modificar")
    public ResponseEntity<TurnoDto> modificarTurnoPorId(@RequestBody TurnoDto turnoDto) throws NotFoundExceptions {
        if (turnoDto.getId() == null) {
            LOG.info("Debe ingresar un id válido");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        LOG.info("Se actualizó el turno con el id " + turnoDto.getId());
        return ResponseEntity.ok(turnoService.modificarTurno(turnoDto));
    }

    //Lista de turnos

    @GetMapping("/lista")
    public ResponseEntity<Collection<TurnoDto>> getTodos() throws NotFoundExceptions {
        Set<TurnoDto> listaDeTurnos = turnoService.listarTurnos();
        if (listaDeTurnos.isEmpty()) {
            LOG.info("No hay ningùn turno registrado");
        }
        LOG.info("se encontraron " + listaDeTurnos.size() + " turnos");
        return ResponseEntity.ok(listaDeTurnos);
    }
    //Eliminar turno
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws NotFoundExceptions {
        if (id == null) {
            LOG.info("No se encontró el turno con el id " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        LOG.info("Se eliminó el turno con el id " + id);
        turnoService.eliminarTurno(id);
        return ResponseEntity.status(HttpStatus.OK).body("Se eliminó el turno con el id " + id);
    }
}



