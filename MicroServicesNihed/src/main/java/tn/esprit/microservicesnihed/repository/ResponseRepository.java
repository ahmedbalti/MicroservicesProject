package tn.esprit.microservicesnihed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.microservicesnihed.entity.Response;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {

}
