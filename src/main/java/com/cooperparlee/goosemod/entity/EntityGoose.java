package com.cooperparlee.goosemod.entity;

import com.cooperparlee.goosemod.util.ModEntities;
import com.cooperparlee.goosemod.util.ModItems;
import com.cooperparlee.goosemod.util.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.passive.fish.SalmonEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityGoose extends AnimalEntity {
    private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS, Items.SWEET_BERRIES);
    public float wingRotation;
    public float destPos;
    public float oFlapSpeed;
    public float oFlap;
    public float wingRotDelta = 1.0F;
    public int timeUntilNextEgg = this.rand.nextInt(500) + 500;

    public EntityGoose(EntityType<? extends AnimalEntity> type, World worldIn){
        super(type, worldIn);
        this.setPathPriority(PathNodeType.WATER, 0.0F);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.3F));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.2D, false, TEMPTATION_ITEMS));
        this.goalSelector.addGoal(4, new AvoidEntityGoal(this, PlayerEntity.class, 8.0F, 1D, 1D));
        this.goalSelector.addGoal(4, new AvoidEntityGoal(this, HorseEntity.class, 8.0F, 1D, 1D));
        this.goalSelector.addGoal(4, new AvoidEntityGoal(this, CowEntity.class, 8.0F, 1D, 1D));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.2D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));

        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, SpiderEntity.class, false));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, BeeEntity.class, false));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, SilverfishEntity.class, false));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, SalmonEntity.class, false));
    }

    @Override
    public AgeableEntity createChild(AgeableEntity ageable) {
        return ModEntities.GOOSE.get().create(world);
    }

    @Override
    public boolean onLivingFall(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    protected void registerAttributes(){
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(9.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
    }
    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return TEMPTATION_ITEMS.test(stack);
    }


    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        if (compound.contains("EggLayTime")) {
            this.timeUntilNextEgg = compound.getInt("EggLayTime");
        }

    }
    protected SoundEvent getAmbientSound() {
        return ModSounds.ENTITY_GOOSE_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModSounds.ENTITY_GOOSE_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return ModSounds.ENTITY_GOOSE_DEATH.get();
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(ModSounds.ENTITY_GOOSE_STEP.get(), 0.35F, 1.0F);
    }

    @Override
    public void livingTick() {
        super.livingTick();
        this.oFlap = this.wingRotation;
        this.oFlapSpeed = this.destPos;
        this.destPos = (float)((double)this.destPos + (double)(this.onGround ? -1 : 4) * 0.3D);
        this.destPos = MathHelper.clamp(this.destPos, 0.0F, 1.0F);
        if (!this.onGround && this.wingRotDelta < 1.0F) {
            this.wingRotDelta = 1.0F;
        }

        this.wingRotDelta = (float)((double)this.wingRotDelta * 0.9D);

        Vec3d vec3d = this.getMotion();
        if (!this.onGround && vec3d.y < 0.0D) {
            this.setMotion(vec3d.mul(1.0D, 0.6D, 1.0D));
        }

        this.wingRotation += this.wingRotDelta * 2.0F;
        if (!this.world.isRemote && this.isAlive() && !this.isChild() && --this.timeUntilNextEgg <= 0) {
            this.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            this.entityDropItem(ModItems.GOOSE_EGG.get());
            this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
        }
    }

    public boolean processInteract(PlayerEntity player, Hand hand){
        ItemStack itemstack = player.getHeldItem(hand);
        Item item = itemstack.getItem();

        if (item == Items.APPLE && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
            if (!player.abilities.isCreativeMode) {
                itemstack.shrink(1);
            }

            if (this.rand.nextInt(3) == 0) {
                EntityGooseTame entityGoose = ModEntities.GOOSE_TAMED.get().create(this.world);
                entityGoose.setGrowingAge(this.getGrowingAge());
                entityGoose.setOwnerId(player.getUniqueID());
                entityGoose.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationYaw, 0.0F);
                this.world.setEntityState(entityGoose, (byte)2); //Sets it to sit state
                this.world.addEntity(entityGoose);
                return entityGoose.ScheduledDeletionInvokation(this, this.world);
            }

            return true;
        }

        return super.processInteract(player, hand);
    }
}
