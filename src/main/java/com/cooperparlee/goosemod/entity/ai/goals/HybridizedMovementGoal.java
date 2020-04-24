package com.cooperparlee.goosemod.entity.ai.goals;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class HybridizedMovementGoal extends Goal {
    protected final CreatureEntity creature;
    protected double x;
    protected double y;
    protected double z;
    protected final double speed;
    protected int executionChanceWalk;
    protected int executionChanceFly;
    protected boolean mustUpdate;
    protected HybridizedMovementTypes movementType = HybridizedMovementTypes.NULL;

    public enum HybridizedMovementTypes{
        NULL, WALK, FLY
    }

    public HybridizedMovementGoal(CreatureEntity creatureIn, double speedIn) {
        this(creatureIn, speedIn, 120, 200);
    }

    public HybridizedMovementGoal(CreatureEntity creatureIn, double speedIn, int chanceWalk, int chanceFly) {
        this.creature = creatureIn;
        this.speed = speedIn;
        this.executionChanceWalk = chanceWalk;
        this.executionChanceWalk = chanceFly;
        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean shouldExecute() {
        if (this.creature.isBeingRidden()) {
            return false; //If this creature is being ridden (wtf), dont execute
        } else { // If it is not being ridden, then check a bunch of things
            if(!this.mustUpdate){
                if (this.creature.getIdleTime() >= 100) {
                    return false;
                }

                if (checkFly()) {
                    return true;
                }
                if (checkWalk()) {
                    return true;
                }
            }

            Vec3d vec3d = this.getPosition();
            if (vec3d == null) {
                return false;
            } else {
                if(1/(this.creature.getRNG().nextInt(this.executionChanceFly)+1) > 1/(this.creature.getRNG().nextInt(this.executionChanceWalk)+1)){
                    movementType = HybridizedMovementTypes.FLY;
                }else{movementType = HybridizedMovementTypes.WALK;}

                this.x = vec3d.x;
                this.y = vec3d.y;
                this.z = vec3d.z;
                this.mustUpdate = false;
                return true;
            }
        }
    }

    @Nullable
    protected Vec3d getPosition() {
        return RandomPositionGenerator.findRandomTarget(this.creature, 10, 7);
    }

    public void setExecutionChanceFly(int newchance){
        this.executionChanceFly = newchance;
    }

    public void setExecutionChanceWalk(int newchance){
        this.executionChanceWalk = newchance;
    }

    private boolean checkFly(){
        if (this.creature.getRNG().nextInt(this.executionChanceFly) == 0) {
            movementType = HybridizedMovementTypes.FLY;
            return true;
        }
        return false;
    }

    private boolean checkWalk(){
        if (this.creature.getRNG().nextInt(this.executionChanceWalk) == 0) {
            movementType = HybridizedMovementTypes.WALK;
            return true;
        }
        return false;
    }
    public boolean shouldContinueExecuting() {
        return !this.creature.getNavigator().noPath() && !this.creature.isBeingRidden();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.creature.getNavigator().tryMoveToXYZ(this.x, this.y, this.z, this.speed);
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask() {
        this.creature.getNavigator().clearPath();
        super.resetTask();
    }

    /**
     * Makes task to bypass chance
     */
    public void makeUpdate() {
        this.mustUpdate = true;
    }
}
