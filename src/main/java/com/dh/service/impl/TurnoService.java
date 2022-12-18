package com.dh.service.impl;
import com.dh.dto.TurnoDto;
import com.dh.entity.Odontologo;
import com.dh.entity.Paciente;
import com.dh.entity.Turno;
import com.dh.exceptions.BadRequestException;
import com.dh.exceptions.NotFoundExceptions;
import com.dh.repository.ITurnoRepository;
import com.dh.service.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class TurnoService implements ITurnoService {
    private static final Logger LOG = LogManager.getLogger(TurnoService.class);

    @Autowired
    ITurnoRepository turnoRepository;
    @Autowired
    ObjectMapper mapper;

    @Autowired
    PacienteService pacienteService;
    @Autowired
    OdontologoService odontologoService;

    @Autowired
    public TurnoService(ITurnoRepository turnoRepository, ObjectMapper mapper) {
        this.turnoRepository = turnoRepository;
        this.mapper = mapper;
    }

    //Crear turno
    @Override
    public TurnoDto crearTurno(TurnoDto turnoDto) throws BadRequestException, NotFoundExceptions {
        Paciente paciente = pacienteService.buscarPorId(turnoDto.paciente.getId()).orElseThrow(()-> new NotFoundExceptions("No se encontró el odontólogo"));
        Odontologo odontologo = odontologoService.buscarPorId(turnoDto.odontologo.getId()).orElseThrow(()-> new NotFoundExceptions("No se encontró el odontólogo"));
        Turno nuevoTurno = new Turno(turnoDto.fecha, turnoDto.hora, paciente, odontologo);
        Turno turno = turnoRepository.save(nuevoTurno);
        LOG.info("Se ha creado un turno");
        return mapper.convertValue(turno, TurnoDto.class);
    }


    //Buscar turno por id
    @Override
    public TurnoDto buscarTurnoPorId(Long id) throws NotFoundExceptions {
        Turno turno = turnoRepository.findById(id).orElseThrow(() -> new NotFoundExceptions("No se encontró un turno correspondiente a este id" + id));
        if (turno.getId() == null || turno.getId() <= 0) {
            LOG.error("Debe ingresar un id válido");
        } else {
            LOG.info("Se encontró el turno con el id " + id);
        }
        return mapper.convertValue(turno, TurnoDto.class);
    }

    //Modificar turno
    @Override
    public TurnoDto modificarTurno(TurnoDto turnoDto) throws NotFoundExceptions {
        Turno turnoAModificar = mapper.convertValue(turnoDto, Turno.class);
        if (turnoAModificar.getId() == null || turnoAModificar.getId() <= 0) {
            LOG.error("El id no es válido, debe ingresar un id válido");
            throw new NotFoundExceptions("El id no es válido, debe ingresar un id válido");
        }
        LOG.info("Se modificó satisfactoriamente" + turnoAModificar.getId());
        Turno turnoActualizado = turnoRepository.save(turnoAModificar);
        return mapper.convertValue(turnoActualizado, TurnoDto.class);
    }

    //Listar turno
    @Override
    public Set<TurnoDto> listarTurnos() throws NotFoundExceptions {
        List<Turno> turnos = turnoRepository.findAll();
        Set<TurnoDto> turnosDto = new HashSet<>();
        if (turnos.isEmpty()) {
            LOG.error("No hay turnos para listar");
            throw new NotFoundExceptions("La lista de turnos está vacía");
        }
        for (Turno turno : turnos) {
            turnosDto.add(mapper.convertValue(turno, TurnoDto.class));
        }
        LOG.info("La lista de turnos tiene " + turnosDto.size() + " turnos");
        return turnosDto;
    }
    //Eliminar turno
    @Override
    public void eliminarTurno(Long id) throws NotFoundExceptions {
        TurnoDto turnoDto = buscarTurnoPorId(id);
        if (turnoDto.getId() == null || turnoDto.getId() <= 0) {
            LOG.error("Debe ingresar un id válido");
            throw new NotFoundExceptions("Debe ingresar un id válido");
        }
        LOG.info("Se eliminó el turno con el id " + id);
        turnoRepository.deleteById(id);
    }


}
