package com.example.posmvcpersistent.models.data;


import com.example.posmvcpersistent.models.Batch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BatchDao extends CrudRepository<Batch, Integer> {
}
