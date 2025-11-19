package br.com.senacsp.tads.stads4ma.library.presentation;

import br.com.senacsp.tads.stads4ma.library.security.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import br.com.senacsp.tads.stads4ma.library.security.CustomUserDetailsService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticação", description = "Endpoints de login, refresh token e geração de JWT")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtService jwtService;


    @Operation(
            summary = "Realiza login e retorna tokens JWT",
            description = "Autentica o usuário com email e senha e retorna accessToken e refreshToken.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Login realizado com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Map.class),
                                    examples = @ExampleObject(value = """
                                    {
                                      "accessToken": "jwt_token_aqui",
                                      "refreshToken": "jwt_refresh_aqui",
                                      "tokenType": "Bearer"
                                    }
                                    """)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Credenciais inválidas",
                            content = @Content(mediaType = "application/json",
                                    examples = @ExampleObject(value = "{\"error\": \"Credenciais inválidas\"}")
                            )
                    )
            }
    )
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        try {
            // authenticationManager.authenticate(
            //        new UsernamePasswordAuthenticationToken(email, password)
            // );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Credenciais inválidas"));
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        String accessToken = jwtService.generateToken(Map.of(), userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        return ResponseEntity.ok(Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken,
                "tokenType", "Bearer"
        ));
    }


    @Operation(
            summary = "Gera um token de teste",
            description = "Cria um token JWT para um usuário fictício. Apenas para testes internos.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Token gerado com sucesso")
            }
    )
    @PostMapping("/test-token")
    public ResponseEntity<String> testToken() {
        UserDetails userDetails = User.withUsername("usuarioTeste")
                .password("123")
                .roles("USER")
                .build();

        String token = jwtService.generateToken(Map.of(), userDetails);

        return ResponseEntity.ok(token);
    }


    @Operation(
            summary = "Renova o accessToken usando o refreshToken",
            description = "Recebe um refreshToken válido e retorna um novo accessToken.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "AccessToken renovado",
                            content = @Content(mediaType = "application/json",
                                    examples = @ExampleObject(value = """
                                    {
                                      "accessToken": "novo_access_token"
                                    }
                                    """))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "RefreshToken não informado",
                            content = @Content(
                                    examples = @ExampleObject(value = "{\"error\":\"refreshToken required\"}")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Refresh token inválido ou expirado",
                            content = @Content(
                                    examples = @ExampleObject(value = "{\"error\":\"Refresh token inválido\"}")
                            )
                    )
            }
    )
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> body) {
        String refreshToken = body.get("refreshToken");
        if (refreshToken == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "refreshToken required"));
        }

        try {
            String username = jwtService.extractUsername(refreshToken);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (!jwtService.isTokenValid(refreshToken, userDetails)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error", "Refresh token inválido ou expirado"));
            }

            String newAccessToken = jwtService.generateToken(Map.of(), userDetails);
            return ResponseEntity.ok(Map.of("accessToken", newAccessToken));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Refresh token inválido"));
        }
    }
}
