package com.dh.service.impl;

import com.dh.dto.PacienteDto;
import com.dh.entity.Paciente;
import com.dh.exceptions.BadRequestException;
import com.dh.exceptions.NotContentException;
import com.dh.exceptions.NotFoundExceptions;
import com.dh.repository.IPacienteRepository;
import com.dh.service.IPacienteService;
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
public class PacienteService implements IPacienteService {

    private static final Logger LOG = LogManager.getLogger(PacienteService.class);

    IPacienteRepository pacienteRepository;
    ObjectMapper mapper;

    @Autowired
    public PacienteService(IPacienteRepository pacienteRepository, ObjectMapper mapper) {
        this.pacienteRepository = pacienteRepository;
        this.mapper = mapper;
    }

    //Crear paciente
    public PacienteDto crearPaciente(PacienteDto pacienteDto) throws BadRequestException {
        Paciente paciente = pacienteRepository.save(mapper.convertValue(pacienteDto, Paciente.class));
        if (paciente.getId() == null) {
            LOG.info("No se creó el paciente");
            throw new BadRequestException("No se pudo crear el paciente");
        } else if (paciente.getNombre() == null || paciente.getNombre().equals("") || paciente.getApellido() == null || paciente.getApellido().equals("")) {
            LOG.info("No se puede crear un paciente sin nombre y apellido");
        }
        LOG.info("Paciente creado");
        return mapper.convertValue(paciente, PacienteDto.class);
    }

    //Buscar paciente por id
    @Override
    public PacienteDto buscarPacientePorId(Long id) throws NotFoundExceptions {
        Paciente paciente = pacienteRepository.findById(id).orElseThrow(() -> new NotFoundExceptions ("NO se encontró el paciente con el id " + id));
        if (paciente.getId() == null || paciente.getId() <= 0) {
            LOG.error("Debe ingresar un id válido");
        } else {
            LOG.info("Se encontró el paciente con el id " + id);
            return mapper.convertValue(paciente, PacienteDto.class);
        }
        return null;
    }

   //Buscar por id
   public Optional<Paciente> buscarPorId(Long id) {
       return pacienteRepository.findById(id);
   }

   //Modificar paciente
   @Override
   public PacienteDto modificarPaciente(PacienteDto pacienteDto) throws NotFoundExceptions {
       Paciente pacienteAActualizar = mapper.convertValue(pacienteDto, Paciente.class);
       if (pacienteAActualizar.getId() == null || pacienteAActualizar.getId() <= 0) {
           LOG.error("Debe ingresar un id válido");
           throw new NotFoundExceptions ("Debe ingresar un id válido");
       }
       LOG.info("Se modificó el paciente con el id " + pacienteAActualizar.getId());
       Paciente pacienteActualizado = pacienteRepository.save(pacienteAActualizar);
       return mapper.convertValue(pacienteActualizado, PacienteDto.class);
   }

    //Listar paciente
    @Override
    public Set<PacienteDto> listarPaciente() throws NotContentException {
        List<Paciente> pacientes = pacienteRepository.findAll();
        Set<PacienteDto> pacientesDto = new HashSet<>();
        if (pacientes.isEmpty()) {
            LOG.error("No hay pacientes para listar");
            throw new NotContentException("La lista de pacientes está vacía");
        }
        for (Paciente paciente : pacientes) {
            pacientesDto.add(mapper.convertValue(paciente, PacienteDto.class));
        }
        LOG.info("La lista de pacientes tiene " + pacientesDto.size() + " odontólogos");
        return pacientesDto;
    }
    //Eliminar paciente
    @Override
    public void eliminarPaciente(Long id) throws NotFoundExceptions {
        PacienteDto pacienteAEliminar = buscarPacientePorId(id);
        if (pacienteAEliminar.getId() == null || pacienteAEliminar.getId() <= 0) {
            LOG.error("Debe ingresar un id válido");
            throw new NotFoundExceptions ("Debe ingresar un id válido");
        }
        LOG.info("Se eliminó el paciente con el id " + id);
        pacienteRepository.deleteById(id);
    }
}
