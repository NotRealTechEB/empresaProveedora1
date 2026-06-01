package cl.example.empresaProveedora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.example.empresaProveedora.dto.EmpresaProveedoraDTO;
import cl.example.empresaProveedora.modelo.EmpresaProveedora;
import cl.example.empresaProveedora.repository.EmpresaProveedoraRepository;

@Service
public class EmpresaProveedoraService {

    @Autowired
    private EmpresaProveedoraRepository repository;

    public List<EmpresaProveedora> obtenerTodas() {
        return repository.findAll();
    }

    public Optional<EmpresaProveedora> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public EmpresaProveedora guardarEmpresa(EmpresaProveedoraDTO dto) {
        
        Optional<EmpresaProveedora> existeRut = repository.findByRut(dto.getRut());
        if (existeRut.isPresent()) {
            throw new RuntimeException("El RUT ingresado ya pertenece a otra empresa proveedora.");
        }

        EmpresaProveedora empresa = new EmpresaProveedora();
        empresa.setRut(dto.getRut());
        empresa.setNombre(dto.getNombre());
        
        return repository.save(empresa);
    }

    public EmpresaProveedora actualizarEmpresa(Long id, EmpresaProveedoraDTO dto) {
        Optional<EmpresaProveedora> existente = repository.findById(id);
        if (existente.isPresent()) {
            
            
            Optional<EmpresaProveedora> empresaConEseRut = repository.findByRut(dto.getRut());
            
            
            if (empresaConEseRut.isPresent() && !empresaConEseRut.get().getIdEmpresaProveedora().equals(id)) {
                throw new RuntimeException("El RUT ingresado ya pertenece a OTRA empresa proveedora distinta.");
            }
           
            EmpresaProveedora empresa = existente.get();
            empresa.setRut(dto.getRut());
            empresa.setNombre(dto.getNombre());
            
            
            return repository.save(empresa);
        }
        throw new RuntimeException("Empresa proveedora no encontrada con el ID: " + id);
    }

    public void eliminarEmpresa(Long id) {
        repository.deleteById(id);
    }
}