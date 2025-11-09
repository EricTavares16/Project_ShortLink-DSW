package br.com.senacsp.tads.stads4ma.library.domainmodel.repository;

import br.com.senacsp.tads.stads4ma.library.domainmodel.Click;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClickRepository extends JpaRepository<Click, UUID> {

    List<Click> findByLink_Id(UUID linkId);

    List<Click> findByCity(String city);

    List<Click> findByRegion(String region);

    List<Click> findByDevice(String device);
}
