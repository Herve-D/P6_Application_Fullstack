package com.openclassrooms.mddapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.model.MddUser;

@Repository
public interface IUserRepository extends JpaRepository<MddUser, Long> {

	Optional<MddUser> findByEmail(String email);

	boolean existsByEmail(String email);

}
