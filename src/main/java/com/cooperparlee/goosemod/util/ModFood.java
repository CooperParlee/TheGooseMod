package com.cooperparlee.goosemod.util;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import java.util.function.Supplier;

public class ModFood {
    public static final Food GOOSE_NUGGET_COOKED = (new Food.Builder()).hunger(3).saturation(0.3F).meat().build();
    public static final Food GOOSE_NUGGET_UNCOOKED = (new Food.Builder()).hunger(1).saturation(0.1F).effect(() -> new EffectInstance(Effects.HUNGER, 600, 0), 0.9F).effect(() -> new EffectInstance(Effects.POISON, 600, 0), 0.6F).build();
    public static final Food GOOSE_COOKED = (new Food.Builder()).hunger(6).saturation(0.6F).meat().build();
    public static final Food GOOSE_UNCOOKED = (new Food.Builder()).hunger(2).saturation(0.3F).effect(() -> new EffectInstance(Effects.HUNGER, 600, 0), 0.3F).build();

}
