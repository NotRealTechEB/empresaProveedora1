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
@RequestMapping("/api/empresas-proveedoras") 
public class EmpresaProveedoraController {

    @Autowired
    private EmpresaProveedoraService service;

 
    @GetMapping
    public ResponseEntity<List<EmpresaProveedora>> obtenerTodas() {
        List<EmpresaProveedora> empresas = service.obtenerTodas();
        return new ResponseEntity<>(empresas, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmpresaProveedora> obtenerPorId(@PathVariable Long id) {
        Optional<EmpresaProveedora> empresa = service.obtenerPorId(id);
        return empresa.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    
    @PostMapping
    public ResponseEntity<EmpresaProveedora> guardarEmpresa(@Valid @RequestBody EmpresaProveedoraDTO dto) {
        EmpresaProveedora nuevaEmpresa = service.guardarEmpresa(dto);
        return new ResponseEntity<>(nuevaEmpresa, HttpStatus.CREATED); 
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<EmpresaProveedora> actualizarEmpresa(@PathVariable Long id, @Valid @RequestBody EmpresaProveedoraDTO dto) {
        try {
            EmpresaProveedora empresaActualizada = service.actualizarEmpresa(id, dto);
            return new ResponseEntity<>(empresaActualizada, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpresa(@PathVariable Long id) {
        service.eliminarEmpresa(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
    }
}