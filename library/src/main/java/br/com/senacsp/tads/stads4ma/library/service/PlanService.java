package br.com.senacsp.tads.stads4ma.library.service;
import br.com.senacsp.tads.stads4ma.library.domainmodel.Plan;
import br.com.senacsp.tads.stads4ma.library.domainmodel.PlanType;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlanService {
    List<Plan> findAll();
    Optional<Plan> findById(UUID id);
    Optional<Plan> findByType(String type);
    Plan create(Plan plan);
    Plan update(UUID id, Plan updatedPlan);
    boolean deleteById(UUID id);
    boolean existsById(UUID id);
}