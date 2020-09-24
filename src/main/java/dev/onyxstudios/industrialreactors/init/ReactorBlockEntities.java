package dev.onyxstudios.industrialreactors.init;

import dev.onyxstudios.industrialreactors.IndustrialReactors;
import dev.onyxstudios.industrialreactors.blockentity.FuelAssemblyController;
import io.github.glasspane.mesh.api.annotation.AutoRegistry;
import net.minecraft.block.entity.BlockEntityType;

@AutoRegistry.Register(modid = IndustrialReactors.MODID, registry = "minecraft:block_entity_type", value = BlockEntityType.class)
public class ReactorBlockEntities {

    public static final BlockEntityType<FuelAssemblyController> FUEL_ASSEMBLY_CONTROLLER = BlockEntityType.Builder.create(FuelAssemblyController::new, ReactorBlocks.FUEL_ASSEMBLY_CONTROLLER).build(null);

}
