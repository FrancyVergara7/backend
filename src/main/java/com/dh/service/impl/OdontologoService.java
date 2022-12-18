package com.dh.service.impl;

import com.dh.dto.OdontologoDto;
import com.dh.entity.Odontologo;
import com.dh.exceptions.BadRequestException;
import com.dh.exceptions.NotContentException;
import com.dh.exceptions.NotFoundExceptions;
import com.dh.repository.IOdontologoRepository;
import com.dh.service.IOdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class
OdontologoService implements IOdontologoService {

    private static final Logger LOG = LogManager.getLogger(OdontologoService.class);

    IOdontologoRepository odontologoRepository;
    ObjectMapper mapper;

    @Autowired
    public OdontologoService(IOdontologoRepository odontologoRepository, ObjectMapper mapper) {
        this.odontologoRepository = odontologoRepository;
        this.mapper = mapper;
    }
    //Crear odontólogo
    @Override
    public OdontologoDto crearOdontologo(OdontologoDto odontologoDto) throws BadRequestException {
        Odontologo odontologo = odontologoRepository.save(mapper.convertValue(odontologoDto, Odontologo.class));
        if (odontologo.getId() == null) {
            LOG.error("No se creó el odontólogo");
            throw new BadRequestException("No se pudo crear el odontologo");
        } else if (odontologo.getNombre() == null || odontologo.getNombre().equals("") || odontologo.getApellido() == null || odontologo.getApellido().equals("")) {
            LOG.error("No se puede crear un odontologo sin nombre y apellido");
        }
        LOG.info("Odontólogo creado");
        return mapper.convertValue(odontologo, OdontologoDto.class);
    }

    //Buscar odontólogo por id
    @Override
    public OdontologoDto buscarOdontologoPorId(Long id) throws NotFoundExceptions {
        Odontologo odontologo = odontologoRepository.findById(id).orElseThrow(() -> new NotFoundExceptions("No se encontró el odontólogo con el id " + id));
        if (odontologo.getId() == null || odontologo.getId() <= 0) {
            LOG.error("Debe ingresar un id válido");
        } else {
            LOG.info("Se encontró el odontólogo con el id " + id);
            return mapper.convertValue(odontologo, OdontologoDto.class);
        }
        return null;
    }

    //Buscar por id

    public Optional<Odontologo> buscarPorId(Long id) {
        return odontologoRepository.findById(id);
    }

   //Modificar odontólogo
   @Override
   public OdontologoDto modificarOdontologo(OdontologoDto odontologoDto) throws NotFoundExceptions {
       Odontologo odontologoAModificar = mapper.convertValue(odontologoDto, Odontologo.class);
       if (odontologoAModificar.getId() == null || odontologoAModificar.getId() <= 0) {
           LOG.error("Debe ingresar un id válido");
           throw new NotFoundExceptions("Debe ingresar un id válido");
       }
       LOG.info("Se modificó el odontólogo con el id " + odontologoAModificar.getId());
       Odontologo odontologoModificado = odontologoRepository.save(odontologoAModificar);
       return mapper.convertValue(odontologoModificado, OdontologoDto.class);
   }

   //Listar odontólogos
   @Override
   public Set<OdontologoDto> listarOdontologos() throws NotContentException {
       List<Odontologo> odontologos = odontologoRepository.findAll();
       Set<OdontologoDto> odontologosDto = new HashSet<>();
       if (odontologos.isEmpty()) {
           LOG.error("No hay odontólogos para listar");
           throw new NotContentException("La lista de odontólogos está vacía");
       }
       for (Odontologo odontologo : odontologos) {
           odontologosDto.add(mapper.convertValue(odontologo, OdontologoDto.class));
       }
       LOG.info("La lista de odontólogos tiene " + odontologosDto.size() + " odontólogos");
       return odontologosDto;
   }
   //Eliminar odontólogo
   @Override
   public void eliminarOdontologo(Long id) throws NotFoundExceptions {
       OdontologoDto odontologoAEliminar = buscarOdontologoPorId(id);
       if (odontologoAEliminar.getId() == null || odontologoAEliminar.getId() <= 0) {
           LOG.error("Debe ingresar un id válido");
           throw new NotFoundExceptions ("Debe ingresar un id válido");
       }
       LOG.info("Se eliminó el odontólogo con el id " + id);
       odontologoRepository.deleteById(id);
   }

}


