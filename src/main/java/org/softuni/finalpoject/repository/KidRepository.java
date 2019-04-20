package org.softuni.finalpoject.repository;

import org.softuni.finalpoject.domain.entities.Kid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KidRepository extends JpaRepository<Kid, String> {



    List<Kid> findAllKidsByParentId(String id);

    List<Kid> findAllByParent(String id);
    List<Kid> findAllKidsByParent_Id(String username);
    Optional<Kid> findByName(String name);
}
