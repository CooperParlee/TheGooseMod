package com.cooperparlee.goosemod.entity;

import com.cooperparlee.goosemod.util.ModEntities;
import com.cooperparlee.goosemod.util.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityEggThrown extends net.minecraft.entity.projectile.EggEntity {
    public EntityEggThrown(World worldIn, LivingEntity throwerIn) {
        super(worldIn, throwerIn);
    }

    public EntityEggThrown(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            ((EntityRayTraceResult)result).getEntity().attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);
        }

        if (!this.world.isRemote) {
            if (this.rand.nextInt(8) == 0) {
                int i = 1;
                if (this.rand.nextInt(32) == 0) {
                    i = 4;
                }

                for(int j = 0; j < i; ++j) {
                    EntityGoose entityGoose = ModEntities.GOOSE.get().create(this.world);
                    entityGoose.setGrowingAge(-24000);
                    entityGoose.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationYaw, 0.0F);
                    this.world.addEntity(entityGoose);
                }
            }

            this.world.setEntityState(this, (byte)3);
            this.remove();
        }

    }
}
