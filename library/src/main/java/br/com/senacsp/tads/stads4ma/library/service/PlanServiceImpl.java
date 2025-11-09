package br.com.senacsp.tads.stads4ma.library.service;


import br.com.senacsp.tads.stads4ma.library.domainmodel.Plan;

import br.com.senacsp.tads.stads4ma.library.domainmodel.PlanType;
import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.PlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;

    public PlanServiceImpl(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public List<Plan> findAll() {
        return planRepository.findAll();
    }

    @Override
    public Optional<Plan> findById(UUID id) {
        return planRepository.findById(id);
    }

    @Override
    public Optional<Plan> findByType(PlanType type) {
        return planRepository.findByType(type);
    }

    @Override
    public Plan create(Plan plan) {
        return planRepository.save(plan);
    }

    @Override
    public Plan update(UUID id, Plan updatedPlan) {
        return planRepository.findById(id)
                .map(existingPlan -> {
                    existingPlan.setType(updatedPlan.getType());
                    existingPlan.setPrice(updatedPlan.getPrice());
                    existingPlan.setMaxLinks(updatedPlan.getMaxLinks());
                    return planRepository.save(existingPlan);
                })
                .orElseThrow(() -> new RuntimeException("Plano não encontrado para atualização"));
    }

    @Override
    public boolean deleteById(UUID id) {
        if (planRepository.existsById(id)) {
            planRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean existsById(UUID id) {
        return planRepository.existsById(id);
    }
}