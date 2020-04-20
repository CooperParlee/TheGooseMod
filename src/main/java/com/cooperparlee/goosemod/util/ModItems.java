package com.cooperparlee.goosemod.util;

import com.cooperparlee.goosemod.GooseMod;

import com.cooperparlee.goosemod.items.ItemBase;
import com.cooperparlee.goosemod.items.ItemEgg;
import com.cooperparlee.goosemod.items.ItemFoodBase;
import com.cooperparlee.goosemod.items.ItemGooseSpawnEgg;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, GooseMod.MOD_ID); // Item registrar

	public static void init() {
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	// Items
	public static final RegistryObject<Item> GOOSE_EGG = ITEMS.register("goose_egg", ItemEgg::new);

	public static final RegistryObject<Item> GOOSE_UNCOOKED = ITEMS.register("goose_uncooked", () -> new ItemFoodBase(ModFood.GOOSE_UNCOOKED));
	public static final RegistryObject<Item> GOOSE_COOKED = ITEMS.register("goose_cooked", () -> new ItemFoodBase(ModFood.GOOSE_COOKED));
	public static final RegistryObject<Item> GOOSE_NUGGET_UNCOOKED = ITEMS.register("goose_nugget_uncooked", () -> new ItemFoodBase(ModFood.GOOSE_NUGGET_UNCOOKED));
	public static final RegistryObject<Item> GOOSE_NUGGET_COOKED = ITEMS.register("goose_nugget_cooked", () -> new ItemFoodBase(ModFood.GOOSE_NUGGET_COOKED));

	public static final RegistryObject<Item> GOOSE_FEATHER = ITEMS.register("goose_feather", ItemBase::new);
	public static final RegistryObject<Item> GOOSE_SPAWN_EGG = ITEMS.register("goose_spawn_egg", ItemGooseSpawnEgg::new);

}
