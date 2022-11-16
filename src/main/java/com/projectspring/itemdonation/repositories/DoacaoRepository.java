package com.projectspring.itemdonation.repositories;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projectspring.itemdonation.models.DoacaoModel;

@Repository
public interface DoacaoRepository extends JpaRepository<DoacaoModel, UUID> {
    List<DoacaoModel> findByPessoaId(UUID pessoaId);

}
