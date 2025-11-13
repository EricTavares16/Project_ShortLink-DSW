package br.com.senacsp.tads.stads4ma.library.domainmodel.repository;
import br.com.senacsp.tads.stads4ma.library.domainmodel.GroupMember;
import br.com.senacsp.tads.stads4ma.library.domainmodel.GroupMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, GroupMemberId> {
    List<GroupMember> findByGroup_Id(UUID groupId);
    List<GroupMember> findByUser_Id(UUID userId);
}
