package cl.example.empresaProveedora.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de respuesta para empresa proveedora")
public class EmpresaProveedoraResponseDTO {

    @Schema(description = "ID único de la empresa proveedora", example = "1")
    private Long idEmpresaProveedora;

    @Schema(description = "RUT de la empresa proveedora", example = "123456789")
    private String rut;

    @Schema(description = "Nombre de la empresa proveedora", example = "Proveedora Ejemplo")
    private String nombre;

    // ========== GETTERS Y SETTERS ==========
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