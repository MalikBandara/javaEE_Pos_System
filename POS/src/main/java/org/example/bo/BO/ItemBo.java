package org.example.bo.BO;

import org.example.bo.SuperBO;
import org.example.dto.ItemDTO;
import org.example.entity.Item;

public interface ItemBo extends SuperBO {
    boolean saveItem(ItemDTO item);

    int generateNextId();
}
