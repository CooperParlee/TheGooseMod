package com.cooperparlee.goosemod.items;

import com.cooperparlee.goosemod.util.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;

import java.util.function.Supplier;

public class ItemGooseSpawnEgg extends ItemBase{


    public ItemGooseSpawnEgg(){
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        Entity goose = ModEntities.GOOSE.get().spawn(context.getWorld(), context.getItem(), context.getPlayer(), context.getPos().add(0, 1, 0), SpawnReason.SPAWN_EGG, false, false);
        context.getWorld().addEntity(goose); //entityType.spawn(context.getWorld(), context.getItem(), context.getPlayer(), context.getPos().add(0, 1, 0), SpawnReason.SPAWN_EGG, false, false);

        return ActionResultType.SUCCESS;

    }
}
