package com.cooperparlee.goosemod.entity;

import com.cooperparlee.goosemod.GooseMod;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import javax.swing.text.html.parser.Entity;

public class EntityGooseRenderer extends MobRenderer<EntityGoose, EntityGooseModel<EntityGoose>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(GooseMod.MOD_ID, "textures/entity/goose.png");
    public EntityGooseRenderer(EntityRendererManager renderManager){
        super(renderManager, new EntityGooseModel<EntityGoose>(), 0.5F);

    }

    @Override
    public ResourceLocation getEntityTexture(EntityGoose entity) {
        return TEXTURE;
    }

    @Override
    protected float handleRotationFloat(EntityGoose livingBase, float partialTicks) {
        float f = MathHelper.lerp(partialTicks, livingBase.oFlap, livingBase.wingRotation);
        float f1 = MathHelper.lerp(partialTicks, livingBase.oFlapSpeed, livingBase.destPos);
        return (MathHelper.sin(f) + 1.0F) * f1;
    }
}
