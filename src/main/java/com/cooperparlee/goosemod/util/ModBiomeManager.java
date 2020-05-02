package com.cooperparlee.goosemod.util;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

import static net.minecraft.entity.EntitySpawnPlacementRegistry.register;

public class ModBiomeManager {
    public static void init(){
        registerAllOverworldEntitySpawns();
    }


    public static void registerAllOverworldEntitySpawns(){
        for (Biome biome : ForgeRegistries.BIOMES){
            if(biome != Biomes.THE_END | biome != Biomes.NETHER){
                if(biome != Biomes.SNOWY_TUNDRA | biome != Biomes.SNOWY_MOUNTAINS | biome != Biomes.ICE_SPIKES | biome != Biomes.FROZEN_RIVER | biome != Biomes.FROZEN_OCEAN | biome != Biomes.DEEP_FROZEN_OCEAN){
                    registerEntityWorldSpawn(ModEntities.GOOSE.get(), 1, 1, 10,
                            Biomes.BIRCH_FOREST,Biomes.BIRCH_FOREST_HILLS,
                            Biomes.FLOWER_FOREST,
                            Biomes.FOREST);
                }
            }
        }
    }


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
