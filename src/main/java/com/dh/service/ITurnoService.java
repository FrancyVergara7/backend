package com.dh.service;

import com.dh.dto.TurnoDto;
import com.dh.exceptions.BadRequestException;
import com.dh.exceptions.NotFoundExceptions;

import java.util.List;
import java.util.Set;

public interface ITurnoService {
    //Crear turno
    TurnoDto crearTurno(TurnoDto turnoDto) throws BadRequestException, NotFoundExceptions;
    //Buscar turno por id
    TurnoDto buscarTurnoPorId(Long id) throws NotFoundExceptions;
    //Modificar turno
    TurnoDto modificarTurno(TurnoDto turnoDto) throws NotFoundExceptions;
    //Listar turno
    Set<TurnoDto> listarTurnos() throws NotFoundExceptions;
    //Eliminar turno
    public void eliminarTurno(Long id) throws NotFoundExceptions;


}
