package dev.onyxstudios.industrialreactors.block;

import dev.onyxstudios.industrialreactors.blockentity.FuelAssemblyController;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class FuelAssemblyControllerBlock extends BlockWithItem implements BlockEntityProvider {

    public FuelAssemblyControllerBlock(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockView world) {
        return new FuelAssemblyController();
    }
}
