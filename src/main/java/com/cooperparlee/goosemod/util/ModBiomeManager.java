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
                    registerEntityWorldSpawn(ModEntities.GOOSE.get(), 1, 3, 30,
                            Biomes.BEACH,
                            Biomes.BIRCH_FOREST,Biomes.BIRCH_FOREST_HILLS,
                            Biomes.FLOWER_FOREST,
                            Biomes.FOREST,
                            Biomes.GIANT_SPRUCE_TAIGA, Biomes.GIANT_SPRUCE_TAIGA_HILLS, Biomes.GIANT_TREE_TAIGA, Biomes.GIANT_TREE_TAIGA_HILLS,
                            Biomes.PLAINS,
                            Biomes.SNOWY_BEACH, Biomes.SNOWY_TAIGA, Biomes.SNOWY_TAIGA_HILLS, Biomes.SNOWY_TAIGA_MOUNTAINS,
                            Biomes.STONE_SHORE, Biomes.SUNFLOWER_PLAINS,
                            Biomes.SWAMP, Biomes.SWAMP_HILLS,
                            Biomes.TAIGA, Biomes.TAIGA_HILLS, Biomes.TAIGA_MOUNTAINS,
                            Biomes.TALL_BIRCH_FOREST, Biomes.TALL_BIRCH_HILLS,
                            Biomes.WOODED_BADLANDS_PLATEAU, Biomes.WOODED_HILLS, Biomes.WOODED_MOUNTAINS);
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
