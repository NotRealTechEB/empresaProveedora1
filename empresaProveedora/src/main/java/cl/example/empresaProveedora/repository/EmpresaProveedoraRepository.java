package cl.example.empresaProveedora.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.example.empresaProveedora.modelo.EmpresaProveedora;

@Repository
public interface EmpresaProveedoraRepository extends JpaRepository<EmpresaProveedora, Long> {
    
    Optional<EmpresaProveedora> findByRut(String rut);
}   