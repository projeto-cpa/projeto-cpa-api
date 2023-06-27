package br.com.biopark.cpa.service;

import br.com.biopark.cpa.config.email.EmailSender;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.biopark.cpa.controller.form.UsuarioForm;
import br.com.biopark.cpa.controller.form.recuperacao.RecuperarAcessoForm;
import br.com.biopark.cpa.models.Usuario;
import br.com.biopark.cpa.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private EmailSender emailSender;

    public Usuario cadastrar(Usuario usuario) throws Exception {
        try {

            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

            usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));

            return usuarioRepository.save(usuario);

        } catch (Exception e) {
            throw new Exception("Erro ao cadastrar usuario " + e.getCause());
        }
    }

    public Usuario buscarUsuario(String login) throws Exception {
        try {
            return usuarioRepository.findByEmail(login);
        } catch (Exception e) {
            throw new Exception("Erro ao buscar usuário");
        }
    }

    public Usuario buscarUsuarioPeloEmail(String email) throws Exception {
        try {
            return usuarioRepository.findByEmail(email);
        } catch (Exception e) {
            throw new Exception("Erro ao buscar usuário");
        }
    }

    public Iterable<Usuario> listarUsuario() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long usuarioRespondenteId) {
        Optional<Usuario> usuario = usuarioRepository.findById((usuarioRespondenteId));

        if (usuario.isPresent())
            return usuario.get();
        else
            throw new EntityNotFoundException("Usuário não encontrado");

    }

    public Usuario atualizar(Long id, UsuarioForm usuarioForm) {
        Usuario usuario = usuarioRepository.findById(id).get();

        if (usuarioForm.getSenha() != null && usuarioForm.getSenhaAtual() != null) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String novaSenha = bCryptPasswordEncoder.encode(usuarioForm.getSenha());
            if (bCryptPasswordEncoder.matches(usuarioForm.getSenhaAtual(), usuario.getSenha())) {
                if (usuarioForm.getSenha() != null) {
                    usuario.setSenha(novaSenha);
                }

            } else {
                throw new IllegalArgumentException("Senha atual incorreta");
            }
        }

        if (usuarioForm.getImagem() != null) {
            usuario.setImagem(usuarioForm.getImagem());
        }

        return usuarioRepository.save(usuario);

    }

    public String gerarCodigoAleatorio(Long tamanho, Boolean letras) {
        String codigo = "";

        String[] caracteres = letras
                ? new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" }
                : new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };

        for (int i = 0; i < tamanho; i++) {
            int posicao = (int) (Math.random() * caracteres.length);
            codigo += caracteres[posicao];
        }
        
        return codigo;
    }

    public String gerarCodigoRecuperacao() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String codigoEncoded = bCryptPasswordEncoder.encode(this.gerarCodigoAleatorio((long) 12, true));
        return codigoEncoded;
    }

    public Usuario recuperar(RecuperarAcessoForm form) throws MessagingException {
        Usuario usuario = usuarioRepository.findByEmail(form.getEmail());
        String codigoRecuperacao = this.gerarCodigoRecuperacao();
        emailSender.enviarCodigoRecuperacao(form.getEmail(), codigoRecuperacao);
        usuario.setCodigoRecuperacao(codigoRecuperacao);
        return usuario;
    }

}