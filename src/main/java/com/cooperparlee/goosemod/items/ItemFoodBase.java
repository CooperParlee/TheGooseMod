package com.cooperparlee.goosemod.items;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemFoodBase extends Item {
    public ItemFoodBase(Food food) {
        super(new Item.Properties().group(ItemGroup.FOOD).food(food));
    }
}
