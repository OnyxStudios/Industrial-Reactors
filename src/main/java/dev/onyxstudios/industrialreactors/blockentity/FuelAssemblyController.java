package dev.onyxstudios.industrialreactors.blockentity;

import dev.onyxstudios.industrialreactors.IndustrialReactors;
import dev.onyxstudios.industrialreactors.init.ReactorBlockEntities;
import dev.onyxstudios.industrialreactors.init.ReactorBlocks;
import dev.onyxstudios.industrialreactors.tag.RFluidTags;
import dev.onyxstudios.industrialreactors.tag.RBlockTags;
import io.github.cottonmc.component.item.InventoryComponent;
import io.github.cottonmc.component.item.impl.SimpleInventoryComponent;
import io.github.glasspane.mesh.util.math.MathUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class FuelAssemblyController extends BlockEntity implements Tickable {

    private int fuelRodBlocks = 0;
    private int ticks = 0;
    private final Random random = new Random();
    //TODO separate input, output, and control rods
    private final SimpleInventoryComponent inventory = new SimpleInventoryComponent(10);

    public FuelAssemblyController() {
        super(ReactorBlockEntities.FUEL_ASSEMBLY_CONTROLLER);
    }

    @Nullable
    public InventoryComponent getInventory(@Nullable Direction side) {
        return side == Direction.UP || side == Direction.DOWN ? inventory : null;
    }

    @Override
    public void tick() {
        if (!world.isClient() && ticks++ % 5 == 0 && world.isReceivingRedstonePower(pos)) {
            int detectedRods = 0;
            BlockState state;
            BlockPos up = pos.up();
            boolean test;
            do {
                state = world.getBlockState(up);
                up = up.up();
                test = state.isOf(ReactorBlocks.FUEL_ROD_ASSEMBLY);
                if (test) {
                    detectedRods++;
                }
            } while (test);
            if (detectedRods != fuelRodBlocks) {
                fuelRodBlocks = detectedRods;
                IndustrialReactors.getLogger().info("detected rods: {}", detectedRods);
                this.markDirty();
            }

            //TODO move to heat and radiation system
            //update water blocks in a ring around the rod assembly
            ServerWorld sw = (ServerWorld) world;
            for (int i = detectedRods; i > 0; i--) {
                BlockPos center = pos.up(i);
                BlockPos.iterate(center.add(-1, 0, -1), center.add(1, 0, 1)).forEach(blockPos -> {
                    if (!blockPos.equals(center)) {
                        FluidState fluidState = world.getFluidState(blockPos);
                        if (fluidState.isIn(RFluidTags.STEAM_PROVIDER_FLUIDS)) { //evaporate water
                            sw.spawnParticles(ParticleTypes.DRIPPING_WATER, blockPos.getX() + 0.5D, blockPos.getY() + 1.3D, blockPos.getZ() + 0.5D, 10, 0.1D, 0.5D, 0.1D, 0.15D);
                            if(random.nextFloat() < 0.015F && fluidState.canBeReplacedWith(world, blockPos, Fluids.EMPTY, Direction.DOWN)) {
                                world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
                            }
                        }
                        else { //melt ice
                            BlockState blockState = world.getBlockState(blockPos);
                            if (blockState.isIn(RBlockTags.STEAM_SOURCE_BLOCKS)) {
                                sw.spawnParticles(ParticleTypes.SMOKE, blockPos.getX() + 0.5D, blockPos.getY() + 0.9D, blockPos.getZ() + 0.5D, 10, 0.1D, 0.5D, 0.1D, 0.15D);
                                if (random.nextFloat() < 0.015F) {
                                    if(world.getDimension().isUltrawarm()) { //don't place water in nether!
                                        world.removeBlock(blockPos, false);
                                    }
                                    else {
                                        world.setBlockState(blockPos, Blocks.WATER.getDefaultState());
                                    }
                                }
                            }
                        }
                    }
                });
            }

            //TODO move to heat system
            Vec3d controller = new Vec3d(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
            sw.getPlayers(serverPlayerEntity -> {
                if(!serverPlayerEntity.isCreative() && MathUtil.getDistanceXZ(controller, serverPlayerEntity.getPos()) < 10.0D) {
                    double distance = Math.min(Math.abs(pos.getY() - serverPlayerEntity.getY()), Math.abs(pos.getY() + this.fuelRodBlocks - serverPlayerEntity.getY()));
                    return distance <= 6.0D;
                }
                return false;
            }).forEach(player -> player.setOnFireFor(7));
        }
    }
}
