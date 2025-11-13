    package br.com.senacsp.tads.stads4ma.library.config;

    import br.com.senacsp.tads.stads4ma.library.domainmodel.*;
    import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.PlanRepository;
    import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.RoleRepository;
    import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.UserRepository;
    import org.springframework.boot.CommandLineRunner;
    import org.springframework.stereotype.Component;

    import java.math.BigDecimal;
    import java.util.List;
    import java.util.UUID;

   // @Component
    public class UserDataLoader implements CommandLineRunner {

        private final UserRepository userRepository;
        private final RoleRepository roleRepository;
        private final PlanRepository planRepository;

        public UserDataLoader(UserRepository userRepository,
                              RoleRepository roleRepository,
                              PlanRepository planRepository) {
            this.userRepository = userRepository;
            this.roleRepository = roleRepository;
            this.planRepository = planRepository;
        }

        @Override
        public void run(String... args) {
            // Evita recriar usuários se o banco já tiver dados
            if (userRepository.count() == 0) {
                System.out.println("🔹 Carregando dados iniciais...");





                // USER padrão
                User user = User.builder()
                        .name("Administrador Padrão")
                        .email("admin@example.com")
                        .password("123456") // apenas para testes
                        .plan("BASIC")
                        .role("USER")
                        .build();

                userRepository.save(user);

                System.out.println("✅ Usuário inicial criado com sucesso!");
            } else {
                System.out.println("🔸 Usuários já existentes, DataLoader ignorado.");
            }
        }
    }
