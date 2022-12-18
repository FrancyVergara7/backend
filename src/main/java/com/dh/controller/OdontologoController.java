package com.dh.controller;

import com.dh.dto.OdontologoDto;
import com.dh.exceptions.BadRequestException;
import com.dh.exceptions.NotContentException;
import com.dh.exceptions.NotFoundExceptions;
import com.dh.service.impl.OdontologoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private static final Logger LOG = LogManager.getLogger(OdontologoController.class);

    OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping("/create")
    public ResponseEntity<OdontologoDto> crearNuevoOdontologo(@Valid @RequestBody OdontologoDto odontologoDto) throws BadRequestException {
        if (odontologoDto.nombre == null || odontologoDto.nombre.equals(" ") || odontologoDto.apellido == null || odontologoDto.apellido.equals(" ")) {
            LOG.error("No se puede crear un odontologo con datos vacios o nulos");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        LOG.info("Creando Odontologo: " + odontologoDto.nombre + " " + odontologoDto.apellido);
        return ResponseEntity.ok(odontologoService.crearOdontologo(odontologoDto));
    }

    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<OdontologoDto> buscarOdontologoPorId(@PathVariable Long id) throws NotFoundExceptions {
        if (id == null) {
            LOG.info("Debe ingresar un id válido");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            OdontologoDto odontologoEncontrado = odontologoService.buscarOdontologoPorId(id);
            LOG.info("Se encontró el odontologo con el id " + id);
            return ResponseEntity.ok(odontologoEncontrado);
        }
    }

    @PutMapping("/modificar")
    public ResponseEntity<OdontologoDto> modificarOdontologoPorId(@RequestBody OdontologoDto odontologoDto) throws ChangeSetPersister.NotFoundException, NotFoundExceptions {
        if (odontologoDto.getId() == null) {
            LOG.info("Debe ingresar un id válido");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        LOG.info("Se actualizó el odontologo con el id " + odontologoDto.getId());
        return ResponseEntity.ok(odontologoService.modificarOdontologo(odontologoDto));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws NotFoundExceptions {
        if (id == null) {
            LOG.info("No se encontró el odontologo con el id " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        LOG.info("Se eliminó el odontologo con el id " + id);
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.status(HttpStatus.OK).body("Se eliminó el odontólogo con el id " + id);
    }
    @GetMapping("/lista")
    public ResponseEntity<Collection<OdontologoDto>> getTodos() throws NotContentException {
        Set<OdontologoDto> listaDeOdontologos = odontologoService.listarOdontologos();
        if (listaDeOdontologos.isEmpty()) {
            LOG.info("No existe ningun odontologo");
        }
        LOG.info("Se encontraron " + listaDeOdontologos.size() + " odontólogos");
        return ResponseEntity.ok(listaDeOdontologos);
    }
}