package tn.esprit.microservicesnihed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.microservicesnihed.entity.Claim;
import tn.esprit.microservicesnihed.entity.State;

import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {

    public List<Claim> findClaimByStateClmIs(State state);
    public List<Claim> findClaimByArchiveClm(boolean arch);

}
