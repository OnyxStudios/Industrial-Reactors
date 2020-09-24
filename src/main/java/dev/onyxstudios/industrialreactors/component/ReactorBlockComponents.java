package dev.onyxstudios.industrialreactors.component;

import dev.onyxstudios.cca.api.v3.block.BlockComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.block.BlockComponentInitializer;
import dev.onyxstudios.cca.api.v3.block.BlockComponents;
import dev.onyxstudios.industrialreactors.IndustrialReactors;
import dev.onyxstudios.industrialreactors.blockentity.FuelAssemblyController;
import dev.onyxstudios.industrialreactors.init.ReactorComponents;
import io.github.glasspane.mesh.api.annotation.CalledByReflection;
import net.minecraft.block.entity.BlockEntity;

@CalledByReflection
public class ReactorBlockComponents implements BlockComponentInitializer {

    @Override
    public void registerBlockComponentFactories(BlockComponentFactoryRegistry registry) {
        registry.registerFor(IndustrialReactors.id("FUEL_ASSEMBLY_CONTROLLER"), ReactorComponents.INVENTORY_COMPONENT, (state, world, pos, side) -> {
            BlockEntity be = world.getBlockEntity(pos);
            return be instanceof FuelAssemblyController ? ((FuelAssemblyController) be).getInventory(side) : null;
        });
        // make fuel rod assemblies redirect component access to their controller
        registry.registerFor(IndustrialReactors.id("FUEL_ROD_ASSEMBLY"), ReactorComponents.INVENTORY_COMPONENT, (state, world, pos, side) -> BlockComponents.get(ReactorComponents.INVENTORY_COMPONENT, state, world, pos.down(), side));
    }
}
