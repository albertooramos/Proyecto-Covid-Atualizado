package com.example.demo.persistencia.repositories;

import com.example.demo.modelo.alumno.Alumno;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, String> {

	@Query(value = "select a from Alumno a where a.nombre = :name", nativeQuery = true)
	public Page<Alumno> findByName(@Param("name") String name, org.springframework.data.domain.Pageable a);

	public Page<Alumno> findByNombre(String nombre, Pageable pageable);
}
