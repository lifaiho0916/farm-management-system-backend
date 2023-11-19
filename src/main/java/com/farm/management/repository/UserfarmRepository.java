package com.farm.management.repository;

import com.farm.management.model.Userfarm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserfarmRepository extends JpaRepository<Userfarm, Long> {
}
