package com.cooperparlee.goosemod.util;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static net.minecraft.entity.EntitySpawnPlacementRegistry.register;

public class ModBiomeManager {

    @SubscribeEvent
    public static void registerEntityWorldSpawn(EntityType<?> entity, int weight, int groupMin, int groupMax,  Biome... biomes) {
        for (Biome biome : biomes) {
            if (biome != null) {
                biome.getSpawns(entity.getClassification()).add(new Biome.SpawnListEntry(entity, weight, groupMin, groupMax));

            }
        }
    }

    static {
        register(ModEntities.GOOSE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
    }
}
