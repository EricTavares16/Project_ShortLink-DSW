package br.com.senacsp.tads.stads4ma.library.config;

import br.com.senacsp.tads.stads4ma.library.domainmodel.*;
import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class FullDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final LinkRepository linkRepository;
    private final MovementHistoryRepository movementHistoryRepository;

    public FullDataLoader(
            UserRepository userRepository,
            GroupRepository groupRepository,
            GroupMemberRepository groupMemberRepository,
            LinkRepository linkRepository,
            MovementHistoryRepository movementHistoryRepository
    ) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.groupMemberRepository = groupMemberRepository;
        this.linkRepository = linkRepository;
        this.movementHistoryRepository = movementHistoryRepository;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() > 0) {
            System.out.println("🔸 Dados já existentes, FullDataLoader ignorado.");
            return;
        }

        System.out.println("🔹 Carregando dados iniciais...");

        // Usuário 1 (Admin)
        User user1 = userRepository.save(
                User.builder()
                        .name("Administrador Padrão")
                        .email("admin@example.com")
                        .password("123456")
                        .plan("BASIC")
                        .role("ADMIN")
                        .build()
        );

        // Usuário 2 (Convidado)
        User user2 = userRepository.save(
                User.builder()
                        .name("Convidado Teste")
                        .email("guest@example.com")
                        .password("654321")
                        .plan("BASIC")
                        .role("USER")
                        .build()
        );

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
                .role("ADMIN") // agora String
                .addedAt(LocalDate.now())
                .build());

        groupMemberRepository.save(GroupMember.builder()
                .id(new GroupMemberId(user2.getId(), group.getId()))
                .user(user2)
                .group(group)
                .role("USER") // agora String
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
