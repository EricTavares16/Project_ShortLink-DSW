package br.com.senacsp.tads.stads4ma.library.presentation;

import br.com.senacsp.tads.stads4ma.library.domainmodel.User;
import br.com.senacsp.tads.stads4ma.library.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Buscar todos
    @GetMapping
    public ResponseEntity<List<User>> fetchAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<User> fetchById(@PathVariable UUID id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    // Criar
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User created = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User user) {
        User updated = userService.update(id, user);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        boolean deleted = userService.deleteById(id);
        return deleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
}
}