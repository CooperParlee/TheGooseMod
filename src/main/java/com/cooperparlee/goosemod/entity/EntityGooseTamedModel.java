package com.cooperparlee.goosemod.entity;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;


public class EntityGooseTamedModel<T extends EntityGooseTame> extends AgeableModel<T> {
    private final ModelRenderer body;
    private final ModelRenderer leftWing;
    private final ModelRenderer rightWing;
    private final ModelRenderer head;
    private final ModelRenderer leftLeg;
    private final ModelRenderer rightLeg;

    public EntityGooseTamedModel() {
        textureWidth = 64;
        textureHeight = 64;

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 20.0F, 0.0F);
        body.setTextureOffset(30, 4).addBox(-1.0F, -5.0F, 9.0F, 2, 2, 1, 0.0F, false);
        body.setTextureOffset(0, 0).addBox(-4.0F, -5.0F, -7.0F, 8, 5, 14, 0.0F, false);
        body.setTextureOffset(0, 19).addBox(-4.0F, -6.0F, -7.0F, 8, 1, 12, 0.0F, false);
        body.setTextureOffset(0, 11).addBox(-3.0F, -6.0F, 5.0F, 6, 1, 1, 0.0F, false);
        body.setTextureOffset(0, 19).addBox(-2.0F, -6.0F, 6.0F, 4, 1, 2, 0.0F, false);
        body.setTextureOffset(0, 6).addBox(-3.0F, -5.0F, 7.0F, 6, 4, 1, 0.0F, false);
        body.setTextureOffset(0, 27).addBox(-2.0F, -5.0F, 8.0F, 4, 3, 1, 0.0F, false);
        body.setTextureOffset(0, 0).addBox(-3.0F, -6.0F, -8.0F, 6, 5, 1, 0.0F, false);

        leftWing = new ModelRenderer(this);
        leftWing.setRotationPoint(4.0F, 15.0F, 0.0F);
        leftWing.setTextureOffset(31, 31).addBox(0.0F, 0.0F, -5.0F, 1, 4, 9, 0.0F, false);

        rightWing = new ModelRenderer(this);
        rightWing.setRotationPoint(-4.0F, 15.0F, 0.0F);
        rightWing.setTextureOffset(30, 0).addBox(-1.0F, 0.0F, -5.0F, 1, 4, 9, 0.0F, false);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 15.0F, -6.0F);
        head.setTextureOffset(28, 19).addBox(-2.0F, -9.0F, -2.0F, 4, 8, 4, 0.0F, false);
        head.setTextureOffset(0, 22).addBox(-1.0F, -8.0F, -5.0F, 2, 2, 3, 0.0F, false);

        leftLeg = new ModelRenderer(this);
        leftLeg.setRotationPoint(1.0F, 20.0F, 0.0F);
        leftLeg.setTextureOffset(10, 22).addBox(0.0F, 0.0F, 0.0F, 1, 4, 0, 0.0F, false);
        leftLeg.setTextureOffset(28, 0).addBox(-1.0F, 4.0F, -2.0F, 3, 0, 2, 0.0F, false);

        rightLeg = new ModelRenderer(this);
        rightLeg.setRotationPoint(-1.0F, 20.0F, 0.0F);
        rightLeg.setTextureOffset(10, 26).addBox(-1.0F, 0.0F, 0.0F, 1, 4, 0, 0.0F, false);
        rightLeg.setTextureOffset(28, 2).addBox(-2.0F, 4.0F, -2.0F, 3, 0, 2, 0.0F, false);
        
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        super.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        //body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    @Override
    protected Iterable<ModelRenderer> getHeadParts() {
        return ImmutableList.of(head);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        //this.body.rotateAngleX = ((float)Math.PI / 2F);
        this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.rightWing.rotateAngleZ = ageInTicks;
        this.leftWing.rotateAngleZ = -ageInTicks;
    }

    protected Iterable<ModelRenderer> getBodyParts() {
        return ImmutableList.of(this.body, this.rightLeg, this.leftLeg, this.rightWing, this.leftWing);
    }

    @Override
    public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
    }

    public ModelRenderer getBody() {
        return body;
    }

    public ModelRenderer getHead() {
        return head;
    }

    public ModelRenderer getLegL() {
        return leftLeg;
    }

    public ModelRenderer getLegR() {
        return rightLeg;
    }

    public ModelRenderer getWingL() {
        return leftWing;
    }

    public ModelRenderer getWingR() {
        return rightWing;
    }
}