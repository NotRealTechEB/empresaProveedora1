package cl.example.empresaProveedora.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmpresaProveedoraDTO {

    @NotBlank(message = "El RUT no puede estar vacío")
    @Size(min = 8, max = 12, message = "El RUT debe tener entre 8 y 12 caracteres")
    private String rut;

    @NotBlank(message = "El nombre de la empresa no puede estar vacío")
    private String nombre;
}