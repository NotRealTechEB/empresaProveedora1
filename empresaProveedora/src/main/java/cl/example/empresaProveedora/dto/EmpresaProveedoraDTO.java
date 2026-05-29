package cl.example.empresaProveedora.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EmpresaProveedoraDTO {

    @NotBlank(message = "El RUT no puede estar vacío")
    @Size(min = 8, max = 12, message = "El RUT debe tener entre 8 y 12 caracteres")
    private String rut;

    @NotBlank(message = "El nombre de la empresa no puede estar vacío")
    private String nombre;

    @NotNull(message = "El ID del piloto es obligatorio")
    private Long pilotoId;

    // --- GETTERS Y SETTERS MANUALES ---

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

    public Long getPilotoId() {
        return pilotoId;
    }

    public void setPilotoId(Long pilotoId) {
        this.pilotoId = pilotoId;
    }
}