package com.cooperparlee.goosemod.util;

import com.cooperparlee.goosemod.GooseMod;
import com.cooperparlee.goosemod.entity.EntityGoose;
import com.cooperparlee.goosemod.entity.EntityGooseTame;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, GooseMod.MOD_ID);

    public static final RegistryObject<EntityType<EntityGoose>> GOOSE = register("goose", ModEntities::goose);
    public static final RegistryObject<EntityType<EntityGooseTame>> GOOSE_TAMED = register("goose_tamed", ModEntities::gooseTamed);

    public static void init(){
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private static <E extends Entity> RegistryObject<EntityType<E>> register(final String name, final Supplier<EntityType.Builder<E>> sup){
        RegistryObject<EntityType<E>> reg = ENTITIES.register(name, () -> sup.get().build(name));
        return reg;
    }

    public static EntityType.Builder<EntityGoose> goose(){
        return EntityType.Builder.create(EntityGoose::new, EntityClassification.CREATURE).size(0.9f, 1.1f);
    }

    public static EntityType.Builder<EntityGooseTame> gooseTamed(){
        return EntityType.Builder.create(EntityGooseTame::new, EntityClassification.CREATURE).size(0.9f, 1.1f);
    }
}
