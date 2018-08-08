package com.example.posmvcpersistent.models.data;

import com.example.posmvcpersistent.models.Registry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface RegistryDao extends CrudRepository<Registry, Integer> {
}
