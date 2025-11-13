package br.com.senacsp.tads.stads4ma.library.config;

import br.com.senacsp.tads.stads4ma.library.domainmodel.*;
import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Component
public class FullDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final LinkRepository linkRepository;
    private final MovementHistoryRepository movementHistoryRepository;
    private final RoleRepository roleRepository;
    private final PlanRepository planRepository;
    private final ProfileRepository profileRepository;
    private final ClickRepository clickRepository;


    public FullDataLoader(
            UserRepository userRepository,
            GroupRepository groupRepository,
            GroupMemberRepository groupMemberRepository,
            LinkRepository linkRepository,
            MovementHistoryRepository movementHistoryRepository,
            RoleRepository roleRepository,
            PlanRepository planRepository,
            ProfileRepository profileRepository,
            ClickRepository clickRepository
    ) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.groupMemberRepository = groupMemberRepository;
        this.linkRepository = linkRepository;
        this.movementHistoryRepository = movementHistoryRepository;
        this.roleRepository = roleRepository;
        this.planRepository = planRepository;
        this.profileRepository = profileRepository;
        this.clickRepository = clickRepository;
    }

    @Override
    public void run(String... args) {

        Role adminRole;
        Role userRole;
        Plan basicPlan;
        Plan premiumPlan;

        System.out.println("🔹 Iniciando FullDataLoader...");

        // 1. CARREGAR/CRIAR ROLES
        if (roleRepository.count() == 0) {
            System.out.println("   -> Cadastrando Roles iniciais...");
            adminRole = roleRepository.save(Role.builder().type("ADMIN").build());
            userRole = roleRepository.save(Role.builder().type("USER").build());
            System.out.println("   ✅ Roles cadastradas.");
        } else {
            System.out.println("   🔸 Roles já existentes. Recuperando...");
            adminRole = roleRepository.findByType("ADMIN").orElseThrow();
            userRole = roleRepository.findByType("USER").orElseThrow();
        }

        // 2. CARREGAR/CRIAR PLANS
        if (planRepository.count() == 0) {
            System.out.println("   -> Cadastrando Plans iniciais...");
            basicPlan = planRepository.save(
                    Plan.builder().type("BASIC").price(new BigDecimal("0.00")).maxLinks(100).build()
            );
            premiumPlan = planRepository.save(
                    Plan.builder().type("PREMIUM").price(new BigDecimal("19.90")).maxLinks(5000).build()
            );
            System.out.println("   ✅ Plans cadastrados.");
        } else {
            System.out.println("   🔸 Plans já existentes. Recuperando...");
            // Se já existirem, apenas recupera para usar na criação dos Usuários
            basicPlan = planRepository.findByType("BASIC").orElseThrow();
            premiumPlan = planRepository.findByType("PREMIUM").orElseThrow();
        }

        // 3. VERIFICAÇÃO FINAL DE USUÁRIOS
        if (userRepository.count() > 0) {
            System.out.println("🔸 Usuários já existentes, FullDataLoader ignorado.");
            return;
        }

        System.out.println("   -> Cadastrando Usuários e dados de exemplo...");

        // Usuário 1 (Admin)
        User user1 = userRepository.save(
                User.builder()
                        .name("Administrador Padrão")
                        .email("admin@example.com")
                        .password("123456")
                        .plan(basicPlan.getType())
                        .role(adminRole.getType())
                        .build()
        );

        // Usuário 2 (Convidado)
        User user2 = userRepository.save(
                User.builder()
                        .name("Convidado Teste")
                        .email("guest@example.com")
                        .password("654321")
                        .plan(basicPlan.getType())
                        .role(userRole.getType())
                        .build()
        );
        // Criar Profile para user1
        Profile profile1 = Profile.builder()
                .user(user1)
                .fullName("Administrador do Sistema")
                .language("pt-BR")
                .avatarUrl("https://example.com/avatarAdmin.png")
                .country("Brasil")
                .themePreference("dark")
                .email(user1.getEmail())
                .phone("+55 11 99999-1234")
                .build();

        profileRepository.save(profile1);

        // Criar Profile para user2
        Profile profile2 = Profile.builder()
                .user(user2)
                .fullName("Usuário Convidado")
                .language("pt-BR")
                .avatarUrl("https://example.com/avatarUser.png")
                .country("Brasil")
                .themePreference("light")
                .email(user2.getEmail())
                .phone("+55 11 98888-4321")
                .build();

        profileRepository.save(profile2);

        // Grupo
        Group group = groupRepository.save(
                Group.builder()
                        .name("Grupo de Teste")
                        .createdAt(LocalDate.now())
                        .build()
        );

        // Adicionar membros ao grupo
        groupMemberRepository.save(GroupMember.builder()
                .id(new GroupMemberId(user1.getId(), group.getId()))
                .user(user1)
                .group(group)
                .role(adminRole.getType()) // agora String
                .addedAt(LocalDate.now())
                .build());

        groupMemberRepository.save(GroupMember.builder()
                .id(new GroupMemberId(user2.getId(), group.getId()))
                .user(user2)
                .group(group)
                .role(userRole.getType()) // agora String
                .addedAt(LocalDate.now())
                .build());

        // Criar Link
        Link link = linkRepository.save(
                Link.builder()
                        .shortCode("abc123")
                        .originalUrl("https://www.exemplo.com")
                        .createdAt(LocalDate.now())
                        .expiresAt(LocalDate.now().plusDays(30))
                        .isActive(true)
                        .user(user1)
                        .group(group)
                        .build()
        );
        // Criar 3 cliques no link existente
        clickRepository.save(
                Click.builder()
                        .link(link)
                        .date(LocalDate.now())
                        .clickedAt(LocalDate.now().atTime(10, 15))
                        .region("São Paulo")
                        .city("São Paulo")
                        .device("Desktop")
                        .referer("https://google.com")
                        .build()
        );

        clickRepository.save(
                Click.builder()
                        .link(link)
                        .date(LocalDate.now())
                        .clickedAt(LocalDate.now().atTime(11, 40))
                        .region("Rio de Janeiro")
                        .city("Niterói")
                        .device("Mobile")
                        .referer("https://instagram.com")
                        .build()
        );

        clickRepository.save(
                Click.builder()
                        .link(link)
                        .date(LocalDate.now())
                        .clickedAt(LocalDate.now().atTime(12, 55))
                        .region("Minas Gerais")
                        .city("Belo Horizonte")
                        .device("Tablet")
                        .referer("https://facebook.com")
                        .build()
        );


        // Criar histórico de movimentação
        MovementHistoryId historyId = new MovementHistoryId(
                link.getId(),
                LocalDate.now(),
                UUID.randomUUID()
        );

        MovementHistory movement = movementHistoryRepository.save(
                MovementHistory.builder()
                        .id(historyId)
                        .link(link)
                        .createdAt(LocalDate.now().atStartOfDay())
                        .totalClicks(10)
                        .action("CREATED")
                        .description("Link criado automaticamente no DataLoader")
                        .userName(user1.getName())
                        .build()
        );

        System.out.println("✅ Dados de exemplo criados com sucesso!");
        System.out.println("Usuários: " + user1.getEmail() + " | " + user2.getEmail());
        System.out.println("Grupo: " + group.getName());
        System.out.println("Link: " + link.getShortCode());
        System.out.println("Histórico: " + movement.getAction() + " - " + movement.getDescription());
    }
}