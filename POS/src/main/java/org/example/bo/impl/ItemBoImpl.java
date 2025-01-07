package org.example.bo.impl;

import org.example.bo.BO.ItemBo;
import org.example.dao.DaoFactory;
import org.example.dao.DaoTypes;
import org.example.dao.dao.ItemDao;
import org.example.dto.ItemDTO;
import org.example.entity.Item;

public class ItemBoImpl implements ItemBo {


    ItemDao itemDao = (ItemDao) DaoFactory.getDaoFactory().getDao(DaoTypes.ITEM);

    @Override
    public boolean saveItem(ItemDTO item) {
     return itemDao.save(new Item(item.getItemCode(),item.getItemName(),item.getItemPrice(),item.getQuantity()));
    }

    @Override
    public int generateNextId() {
       return itemDao.IdGenerate();
    }
}
