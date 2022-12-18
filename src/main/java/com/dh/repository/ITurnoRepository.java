package com.dh.repository;

import com.dh.dto.TurnoDto;
import com.dh.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Long> {

}
