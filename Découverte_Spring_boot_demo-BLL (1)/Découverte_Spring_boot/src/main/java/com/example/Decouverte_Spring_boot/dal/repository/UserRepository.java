package com.example.Decouverte_Spring_boot.dal.repository;

import com.example.Decouverte_Spring_boot.dal.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Je vous ai menti, ici repository n'est pas obligatoire car on implémente presque toujours une classe
 * qui possède déjà une annotation similaire sur nos repository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
