package br.com.biopark.cpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.biopark.cpa.models.Usuario;
import br.com.biopark.cpa.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario cadastrar(Usuario usuario) throws Exception {

        Usuario usuarioCadastrado = new Usuario();

        try {
            usuarioCadastrado = usuarioRepository.save(usuario);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return usuarioCadastrado;

    }

    public Iterable<Usuario> listarUsuario() {
        return usuarioRepository.findAll();
    }

}