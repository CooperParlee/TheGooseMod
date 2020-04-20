package com.cooperparlee.goosemod.items;

import com.cooperparlee.goosemod.entity.EntityEggThrown;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class ItemEgg extends Item {

    public ItemEgg() {
        super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(8));
    }

    public ActionResult<ItemStack> onItemRightClick (World worldIn, PlayerEntity playerIn, Hand handIn){
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        worldIn.playSound((PlayerEntity)null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_EGG_THROW, SoundCategory.PLAYERS, 0.5f, 0.3f / (random.nextFloat() * 0.4F + 0.8F));
        if(!worldIn.isRemote){
            EntityEggThrown eggentity = new EntityEggThrown(worldIn, playerIn);
            eggentity.setItem(itemstack);
            eggentity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0f, 1f, 1.2f);
            worldIn.addEntity(eggentity);
        }

        playerIn.addStat(Stats.ITEM_USED.get(this));
        if(!playerIn.abilities.isCreativeMode){
            itemstack.shrink(1);
        }

        return ActionResult.resultSuccess(itemstack);
    }
}
