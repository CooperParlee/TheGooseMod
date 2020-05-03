package com.cooperparlee.goosemod.entity;

import com.cooperparlee.goosemod.GooseMod;
import com.cooperparlee.goosemod.entity.EntityGoose;
import com.cooperparlee.goosemod.entity.EntityGooseModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class EntityGooseTameRenderer extends MobRenderer<EntityGooseTame, EntityGooseTamedModel<EntityGooseTame>> {
    private boolean wasSitting = false;

    protected static final ResourceLocation TEXTURE = new ResourceLocation(GooseMod.MOD_ID, "textures/entity/goose.png");
    public EntityGooseTameRenderer(EntityRendererManager renderManager){
        super(renderManager, new EntityGooseTamedModel<EntityGooseTame>(), 0.5F);
    }

    @Override
    public void render(EntityGooseTame entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        if(entityIn.isSitting()){
            MatrixStack nStack = matrixStackIn;
            //GooseMod.getLog().debug(nStack.getLast().getMatrix().toString());
            nStack.push();
            nStack.translate(0, -0.2d, 0);
            super.render(entityIn, entityYaw, partialTicks, nStack, bufferIn, packedLightIn);
            matrixStackIn.pop();

        }else if(!entityIn.isSitting()){
            super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        }

    }

    @Override
    public ResourceLocation getEntityTexture(EntityGooseTame entity) {
        return TEXTURE;
    }

    @Override
    protected float handleRotationFloat(EntityGooseTame livingBase, float partialTicks) {
        float f = MathHelper.lerp(partialTicks, livingBase.oFlap, livingBase.wingRotation);
        float f1 = MathHelper.lerp(partialTicks, livingBase.oFlapSpeed, livingBase.destPos);
        return (MathHelper.sin(f) + 1.0F) * f1;
    }
}
