package cl.example.empresaProveedora.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "empresas_proveedoras")
@Data
public class EmpresaProveedora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpresaProveedora;

    @Column(nullable = false, unique = true, length = 12)
    private String rut;

    @Column(nullable = false, length = 100)
    private String nombre;
}