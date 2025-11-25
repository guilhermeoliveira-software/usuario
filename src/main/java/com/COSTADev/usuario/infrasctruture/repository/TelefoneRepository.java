package com.COSTADev.usuario.infrasctruture.repository;

import com.costadev.aprendendo_spring.infra.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
