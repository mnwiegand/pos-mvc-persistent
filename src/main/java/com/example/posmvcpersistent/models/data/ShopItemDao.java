package com.example.posmvcpersistent.models.data;

import com.example.posmvcpersistent.models.ShopItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ShopItemDao extends CrudRepository<ShopItem, Integer> {
}
