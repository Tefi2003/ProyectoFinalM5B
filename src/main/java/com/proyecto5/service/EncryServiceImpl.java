package com.proyecto5.service;

import com.proyecto5.model.Usuarios;
import com.proyecto5.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class EncryServiceImpl implements EncryptSservice{

    @Autowired
    private UsuariosRepository usuariosRepository;

    public EncryServiceImpl(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    public String encryPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean verifyPassword(String originalPassowrd, String hasPassword) {
        return BCrypt.checkpw(originalPassowrd, hasPassword);
    }

    public Usuarios findUsuarioByCorrreo(String correo) {
        // Implementa la lógica para buscar y devolver un objeto Usuarios según el nombre de usuario (o cualquier campo único)
        return this.usuariosRepository.findByCorreo(correo);
    }

    public Usuarios saveUser(Usuarios newusuarios) {
        return this.usuariosRepository.save(newusuarios);
    }
}
