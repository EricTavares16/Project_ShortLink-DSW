package br.com.senacsp.tads.stads4ma.library.security;

import br.com.senacsp.tads.stads4ma.library.domainmodel.User;
import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // Observação: seu UserRepository tem List<User> findByEmail(String). Ideal seria Optional<User>.
    // Se preferir, adicione: Optional<User> findByEmail(String email) no repo.
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        // aqui assumimos que o 'username' é o email
        List<User> users = userRepository.findByEmail(usernameOrEmail);
        if (users == null || users.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + usernameOrEmail);
        }
        User user = users.get(0);

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
        );
    }
}
