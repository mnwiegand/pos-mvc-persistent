package com.example.posmvcpersistent.models.data;


import com.example.posmvcpersistent.models.Category;
import com.example.posmvcpersistent.models.ItemType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ItemTypeDao extends CrudRepository<ItemType, Integer> {
}
