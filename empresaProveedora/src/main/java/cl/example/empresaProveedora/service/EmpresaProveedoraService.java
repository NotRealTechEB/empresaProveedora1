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
        // Verificamos que el RUT no exista ya en la base de datos
        Optional<EmpresaProveedora> existeRut = repository.findByRut(dto.getRut());
        if (existeRut.isPresent()) {
            throw new RuntimeException("El RUT ingresado ya pertenece a otra empresa proveedora.");
        }

        EmpresaProveedora empresa = new EmpresaProveedora();
        empresa.setRut(dto.getRut());
        empresa.setNombre(dto.getNombre());
        empresa.setPilotoId(dto.getPilotoId()); // <- Pasamos el pilotoId
        return repository.save(empresa);
    }

    public EmpresaProveedora actualizarEmpresa(Long id, EmpresaProveedoraDTO dto) {
        Optional<EmpresaProveedora> existente = repository.findById(id);
        if (existente.isPresent()) {
            EmpresaProveedora empresa = existente.get();
            
            // Opcional: si actualiza el RUT, habría que verificar que no choque con otro.
            // Por simplicidad, aquí actualizamos los campos directo.
            empresa.setRut(dto.getRut());
            empresa.setNombre(dto.getNombre());
            empresa.setPilotoId(dto.getPilotoId()); // <- Actualizamos el pilotoId
            
            return repository.save(empresa);
        }
        throw new RuntimeException("Empresa proveedora no encontrada con el ID: " + id);
    }

    public void eliminarEmpresa(Long id) {
        repository.deleteById(id);
    }
}