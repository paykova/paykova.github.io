package org.softuni.finalpoject.repository;

import org.softuni.finalpoject.domain.entities.OtherActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtherActivityRepository extends JpaRepository<OtherActivity, String> {
}
