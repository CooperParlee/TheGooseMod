package com.cooperparlee.goosemod.entity.ai;


import com.cooperparlee.goosemod.entity.ai.goals.HybridizedMovementGoal;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.VoxelShape;
import com.cooperparlee.goosemod.entity.ai.goals.HybridizedMovementGoal.HybridizedMovementTypes;

public class HybridMovementController extends MovementController {
    private final int field_226323_i_;
    private final boolean field_226324_j_;
    protected Action action = Action.WAIT;
    private boolean fly = false;

    public HybridMovementController(MobEntity p_i225710_1_, int p_i225710_2_, boolean p_i225710_3_) {
        super(p_i225710_1_);
        this.field_226323_i_ = p_i225710_2_;
        this.field_226324_j_ = p_i225710_3_;
    }

    public void setFly(boolean fly){
        this.fly = fly;
    }

    @Override
    public void setMoveTo(double x, double y, double z, double speedIn) {
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        this.speed = speedIn;
        if (this.action != Action.JUMPING) {
            if(fly){
                this.action = Action.FLY_TO;
            }else{
                this.action = Action.MOVE_TO;
            }
        }

    }

    public void tick() {
        this.mob.setNoGravity(false);

        if (this.action == Action.FLY_TO) {
            flyTick();
        }else if(this.action == Action.MOVE_TO | this.action == Action.STRAFE | this.action == Action.JUMPING){
            walkTick();
        }

        this.mob.setMoveVertical(0.0F);
        this.mob.setMoveForward(0.0F);
    }

    public enum Action {
        WAIT,
        MOVE_TO,
        FLY_TO,
        STRAFE,
        JUMPING;
    }

    public void flyTick(){
        this.action = Action.WAIT;
        this.mob.setNoGravity(true);
        double d0 = this.posX - this.mob.getPosX();
        double d1 = this.posY - this.mob.getPosY();
        double d2 = this.posZ - this.mob.getPosZ();
        double d3 = d0 * d0 + d1 * d1 + d2 * d2;
        if (d3 < (double)2.5000003E-7F) {
            this.mob.setMoveVertical(0.0F);
            this.mob.setMoveForward(0.0F);
            return;
        }

        float f = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
        this.mob.rotationYaw = this.limitAngle(this.mob.rotationYaw, f, 90.0F);
        float f1;
        if (this.mob.onGround) {
            f1 = (float)(this.speed * this.mob.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
        } else {
            f1 = (float)(this.speed * this.mob.getAttribute(SharedMonsterAttributes.FLYING_SPEED).getValue());
        }

        this.mob.setAIMoveSpeed(f1);
        double d4 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
        float f2 = (float)(-(MathHelper.atan2(d1, d4) * (double)(180F / (float)Math.PI)));
        this.mob.rotationPitch = this.limitAngle(this.mob.rotationPitch, f2, (float)this.field_226323_i_);
        this.mob.setMoveVertical(d1 > 0.0D ? f1 : -f1);
    }
    public void walkTick(){
        if (this.action == Action.STRAFE) {
            float f = (float)this.mob.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue();
            float f1 = (float)this.speed * f;
            float f2 = this.moveForward;
            float f3 = this.moveStrafe;
            float f4 = MathHelper.sqrt(f2 * f2 + f3 * f3);
            if (f4 < 1.0F) {
                f4 = 1.0F;
            }

            f4 = f1 / f4;
            f2 = f2 * f4;
            f3 = f3 * f4;
            float f5 = MathHelper.sin(this.mob.rotationYaw * ((float)Math.PI / 180F));
            float f6 = MathHelper.cos(this.mob.rotationYaw * ((float)Math.PI / 180F));
            float f7 = f2 * f6 - f3 * f5;
            float f8 = f3 * f6 + f2 * f5;
            PathNavigator pathnavigator = this.mob.getNavigator();
            if (pathnavigator != null) {
                NodeProcessor nodeprocessor = pathnavigator.getNodeProcessor();
                if (nodeprocessor != null && nodeprocessor.getPathNodeType(this.mob.world, MathHelper.floor(this.mob.getPosX() + (double)f7), MathHelper.floor(this.mob.getPosY()), MathHelper.floor(this.mob.getPosZ() + (double)f8)) != PathNodeType.WALKABLE) {
                    this.moveForward = 1.0F;
                    this.moveStrafe = 0.0F;
                    f1 = f;
                }
            }

            this.mob.setAIMoveSpeed(f1);
            this.mob.setMoveForward(this.moveForward);
            this.mob.setMoveStrafing(this.moveStrafe);
            this.action = Action.WAIT;
        } else if (this.action == Action.MOVE_TO) {
            this.action = Action.WAIT;
            double d0 = this.posX - this.mob.getPosX();
            double d1 = this.posZ - this.mob.getPosZ();
            double d2 = this.posY - this.mob.getPosY();
            double d3 = d0 * d0 + d2 * d2 + d1 * d1;
            if (d3 < (double)2.5000003E-7F) {
                this.mob.setMoveForward(0.0F);
                return;
            }

            float f9 = (float)(MathHelper.atan2(d1, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
            this.mob.rotationYaw = this.limitAngle(this.mob.rotationYaw, f9, 90.0F);
            this.mob.setAIMoveSpeed((float)(this.speed * this.mob.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue()));
            BlockPos blockpos = new BlockPos(this.mob);
            BlockState blockstate = this.mob.world.getBlockState(blockpos);
            Block block = blockstate.getBlock();
            VoxelShape voxelshape = blockstate.getCollisionShape(this.mob.world, blockpos);
            if (d2 > (double)this.mob.stepHeight && d0 * d0 + d1 * d1 < (double)Math.max(1.0F, this.mob.getWidth()) || !voxelshape.isEmpty() && this.mob.getPosY() < voxelshape.getEnd(Direction.Axis.Y) + (double)blockpos.getY() && !block.isIn(BlockTags.DOORS) && !block.isIn(BlockTags.FENCES)) {
                this.mob.getJumpController().setJumping();
                this.action = Action.JUMPING;
            }
        } else if (this.action == Action.JUMPING) {
            this.mob.setAIMoveSpeed((float)(this.speed * this.mob.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue()));
            if (this.mob.onGround) {
                this.action = Action.WAIT;
            }
        } else {
            this.mob.setMoveForward(0.0F);
        }
    }
}