package com.salescontrol.repository;

import com.salescontrol.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<Client> findByName(String name);
    List<Client> findByNickname(String nickname);
    Optional<Client> findByCpf(String cpf);

//    @Query("SELECT name FROM tb_clients WHERE name LIKE %:name%")
//    List<Client> findByNameContaining(String name);

    @Query("SELECT c FROM Client c WHERE c.name LIKE %:name%")
    List<Client> findByNameContaining(@Param("name") String name);

}
