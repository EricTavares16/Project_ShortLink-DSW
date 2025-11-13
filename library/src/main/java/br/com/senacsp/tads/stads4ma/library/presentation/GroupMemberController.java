package br.com.senacsp.tads.stads4ma.library.presentation;


import br.com.senacsp.tads.stads4ma.library.application.dto.GroupMemberDTO;
import br.com.senacsp.tads.stads4ma.library.service.GroupMemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/group-members")
public class GroupMemberController {

    private final GroupMemberService groupMemberService;

    public GroupMemberController(GroupMemberService groupMemberService) {
        this.groupMemberService = groupMemberService;
    }

    @PostMapping
    public ResponseEntity<GroupMemberDTO> create(@RequestBody GroupMemberDTO dto) {
        GroupMemberDTO created = groupMemberService.create(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<GroupMemberDTO>> findAll() {
        List<GroupMemberDTO> members = groupMemberService.findAll();
        return ResponseEntity.ok(members);
    }

    @GetMapping("/{groupId}/{userId}")
    public ResponseEntity<GroupMemberDTO> findById(@PathVariable UUID groupId, @PathVariable UUID userId) {
        GroupMemberDTO dto = groupMemberService.findById(groupId, userId);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{groupId}/{userId}")
    public ResponseEntity<Void> delete(@PathVariable UUID groupId, @PathVariable UUID userId) {
        groupMemberService.delete(groupId, userId);
        return ResponseEntity.noContent().build();
    }
}
