package cl.example.empresaProveedora.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "empresas_proveedoras")
public class EmpresaProveedora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpresaProveedora;

    @Column(nullable = false, unique = true, length = 12)
    private String rut;

    @Column(nullable = false, length = 100)
    private String nombre;

   
    public Long getIdEmpresaProveedora() {
        return idEmpresaProveedora;
    }

    public void setIdEmpresaProveedora(Long idEmpresaProveedora) {
        this.idEmpresaProveedora = idEmpresaProveedora;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}