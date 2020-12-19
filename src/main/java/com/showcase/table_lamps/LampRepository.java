package com.showcase.table_lamps;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LampRepository extends JpaRepository<LampEntity, Integer>{

}