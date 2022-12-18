package com.dh.service;

import com.dh.dto.OdontologoDto;
import com.dh.entity.Odontologo;
import com.dh.exceptions.BadRequestException;
import com.dh.exceptions.NotContentException;
import com.dh.exceptions.NotFoundExceptions;

import java.util.List;
import java.util.Set;

public interface IOdontologoService {

    OdontologoDto crearOdontologo(OdontologoDto odontologoDto) throws BadRequestException;
    OdontologoDto buscarOdontologoPorId(Long id) throws NotFoundExceptions;
    OdontologoDto modificarOdontologo(OdontologoDto odontologoDto) throws Exception;
    void eliminarOdontologo(Long id) throws NotFoundExceptions;
    Set<OdontologoDto> listarOdontologos() throws NotFoundExceptions, NotContentException;
}
