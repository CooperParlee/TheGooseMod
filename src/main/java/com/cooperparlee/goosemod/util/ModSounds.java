package com.cooperparlee.goosemod.util;

import com.cooperparlee.goosemod.GooseMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = new DeferredRegister<SoundEvent>(ForgeRegistries.SOUND_EVENTS, GooseMod.MOD_ID);

    public static void init() {
        SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<SoundEvent> ENTITY_GOOSE_AMBIENT = SOUNDS.register("entity.goose.ambient", () -> new SoundEvent(new ResourceLocation(GooseMod.MOD_ID, "entity.goose.ambient")));
    public static final RegistryObject<SoundEvent> ENTITY_GOOSE_HURT = SOUNDS.register("entity.goose.hurt", () -> new SoundEvent(new ResourceLocation(GooseMod.MOD_ID, "entity.goose.hurt")));
    public static final RegistryObject<SoundEvent> ENTITY_GOOSE_DEATH = SOUNDS.register("entity.goose.death", () -> new SoundEvent(new ResourceLocation(GooseMod.MOD_ID, "entity.goose.death")));
    public static final RegistryObject<SoundEvent> ENTITY_GOOSE_STEP = SOUNDS.register("entity.goose.step", () -> new SoundEvent(new ResourceLocation(GooseMod.MOD_ID, "entity.goose.step")));


}
