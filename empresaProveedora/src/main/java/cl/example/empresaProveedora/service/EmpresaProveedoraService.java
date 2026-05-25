package cl.example.empresaProveedora.service;

import cl.example.empresaProveedora.dto.EmpresaProveedoraDTO;
import cl.example.empresaProveedora.modelo.EmpresaProveedora;
import cl.example.empresaProveedora.repository.EmpresaProveedoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        EmpresaProveedora empresa = new EmpresaProveedora();
        empresa.setRut(dto.getRut());
        empresa.setNombre(dto.getNombre());
        return repository.save(empresa);
    }

    public EmpresaProveedora actualizarEmpresa(Long id, EmpresaProveedoraDTO dto) {
        Optional<EmpresaProveedora> existente = repository.findById(id);
        if (existente.isPresent()) {
            EmpresaProveedora empresa = existente.get();
            empresa.setRut(dto.getRut());
            empresa.setNombre(dto.getNombre());
            return repository.save(empresa);
        }
        return null;
    }

    public void eliminarEmpresa(Long id) {
        repository.deleteById(id);
    }
}