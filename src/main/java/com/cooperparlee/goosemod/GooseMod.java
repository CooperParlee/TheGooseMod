package com.cooperparlee.goosemod;

import com.cooperparlee.goosemod.util.ModEntities;
import com.cooperparlee.goosemod.util.ModSounds;
import com.cooperparlee.goosemod.util.ModBiomeManager;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cooperparlee.goosemod.util.ModItems;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("thegoosemod")
public class GooseMod {

	private static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "thegoosemod";
	
	public GooseMod() {

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::FMLLoadCompleteEvent);
		ModSounds.init();
		ModEntities.init();
		ModItems.init();

		MinecraftForge.EVENT_BUS.register(this);

		

		
	}
	
	private void setup(final FMLCommonSetupEvent event) {

	}
	
	private void doClientStuff(final FMLClientSetupEvent event) {
		//ModBiomeManager.setEntitySpawns(EntityClassification.CREATURE, , 10000, 2, 25, Biomes.PLAINS, Biomes.FLOWER_FOREST, Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.FOREST, Biomes.BEACH, Biomes.MOUNTAINS);

	}

	private void FMLLoadCompleteEvent(FMLLoadCompleteEvent event){
		ModBiomeManager.init();
	}
	public static Logger getLog(){
		return LOGGER;
	}
}
