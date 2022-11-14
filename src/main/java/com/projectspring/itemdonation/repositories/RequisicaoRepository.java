package com.projectspring.itemdonation.repositories;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projectspring.itemdonation.models.RequisicaoModel;

@Repository
public interface RequisicaoRepository extends JpaRepository<RequisicaoModel, UUID>{
    List<RequisicaoModel> findByDoacaoId(UUID doacao_Id);
}
