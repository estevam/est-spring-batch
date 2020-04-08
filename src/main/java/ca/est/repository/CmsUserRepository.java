package ca.est.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.est.entity.CmsUser;

@Repository
public interface CmsUserRepository extends JpaRepository<CmsUser, Long> {

}
