package org.example.bo.BO;

import org.example.bo.SuperBO;
import org.example.dto.ItemDTO;
import org.example.entity.Item;

import java.util.List;

public interface ItemBo extends SuperBO {
    boolean saveItem(ItemDTO item);

    int generateNextId();

    List<ItemDTO> getAllItems();

    boolean updateItem(ItemDTO itemDTO);

    boolean DeleteItem(int itemId);
}

