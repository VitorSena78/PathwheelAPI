package com.pathwheel.entidadejpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface Repositoryjpa extends JpaRepository<Entidadejpa,Long> {
}
