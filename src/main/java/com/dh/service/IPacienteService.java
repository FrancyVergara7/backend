package com.dh.service;

import com.dh.dto.PacienteDto;
import com.dh.entity.Paciente;
import com.dh.exceptions.BadRequestException;
import com.dh.exceptions.NotContentException;
import com.dh.exceptions.NotFoundExceptions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


public interface IPacienteService {

    PacienteDto crearPaciente(PacienteDto pacienteDto) throws BadRequestException, NotFoundExceptions;
    PacienteDto buscarPacientePorId(Long id) throws NotFoundExceptions;
    PacienteDto modificarPaciente(PacienteDto pacienteDto) throws NotFoundExceptions;
    void eliminarPaciente(Long id) throws NotFoundExceptions;
    Set<PacienteDto> listarPaciente() throws NotContentException;
}
