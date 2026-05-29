package cl.example.empresaProveedora.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.example.empresaProveedora.dto.EmpresaProveedoraDTO;
import cl.example.empresaProveedora.modelo.EmpresaProveedora;
import cl.example.empresaProveedora.service.EmpresaProveedoraService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/empresas-proveedoras") // Ruta base para consumir este microservicio
public class EmpresaProveedoraController {

    @Autowired
    private EmpresaProveedoraService service;

    // 1. Obtener todas las empresas proveedoras (GET)
    @GetMapping
    public ResponseEntity<List<EmpresaProveedora>> obtenerTodas() {
        List<EmpresaProveedora> empresas = service.obtenerTodas();
        return new ResponseEntity<>(empresas, HttpStatus.OK);
    }

    // 2. Obtener una empresa proveedora específica por su ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaProveedora> obtenerPorId(@PathVariable Long id) {
        Optional<EmpresaProveedora> empresa = service.obtenerPorId(id);
        return empresa.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // 3. Crear una nueva empresa proveedora (POST)
    // El @Valid activa las reglas del DTO (@NotBlank y @Size). Si fallan, no entra al método.
    @PostMapping
    public ResponseEntity<EmpresaProveedora> guardarEmpresa(@Valid @RequestBody EmpresaProveedoraDTO dto) {
        EmpresaProveedora nuevaEmpresa = service.guardarEmpresa(dto);
        return new ResponseEntity<>(nuevaEmpresa, HttpStatus.CREATED); // Retorna código 201 Created
    }

    // 4. Actualizar una empresa proveedora existente (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<EmpresaProveedora> actualizarEmpresa(@PathVariable Long id, @Valid @RequestBody EmpresaProveedoraDTO dto) {
        try {
            EmpresaProveedora empresaActualizada = service.actualizarEmpresa(id, dto);
            return new ResponseEntity<>(empresaActualizada, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Si el ID no existe, responde 404
        }
    }

    // 5. Eliminar una empresa proveedora (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpresa(@PathVariable Long id) {
        service.eliminarEmpresa(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retorna código 204 No Content (Éxito sin cuerpo)
    }
}