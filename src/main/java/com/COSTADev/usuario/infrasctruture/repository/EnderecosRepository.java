package com.COSTADev.usuario.infrasctruture.repository;


import com.COSTADev.usuario.infrasctruture.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecosRepository extends JpaRepository<Endereco, Long> {
}
