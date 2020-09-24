package dev.onyxstudios.industrialreactors.init;

import dev.onyxstudios.industrialreactors.IndustrialReactors;
import dev.onyxstudios.industrialreactors.block.BlockWithItem;
import dev.onyxstudios.industrialreactors.block.FuelAssemblyControllerBlock;
import io.github.glasspane.mesh.api.annotation.AutoRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

@AutoRegistry.Register(modid = IndustrialReactors.MODID, registry = "minecraft:block", value = Block.class)
public class ReactorBlocks {

    public static final Block LEAD_ORE = new BlockWithItem(FabricBlockSettings.copyOf(Blocks.IRON_BARS));
    public static final Block URANIUM_ORE = new BlockWithItem(FabricBlockSettings.copyOf(Blocks.IRON_BARS));

    public static final Block LEAD_BLOCK = new BlockWithItem(FabricBlockSettings.copyOf(Blocks.IRON_BARS));
    public static final Block STEEL_BLOCK = new BlockWithItem(FabricBlockSettings.copyOf(Blocks.IRON_BARS));
    public static final Block GRAPHITE_BLOCK = new BlockWithItem(FabricBlockSettings.copyOf(Blocks.IRON_BARS));
    public static final Block URANIUM_BLOCK = new BlockWithItem(FabricBlockSettings.copyOf(Blocks.IRON_BARS));
    public static final Block ENRICHED_URANIUM_BLOCK = new BlockWithItem(FabricBlockSettings.copyOf(Blocks.IRON_BARS));

    public static final Block FUEL_ROD_ASSEMBLY = new BlockWithItem(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK));
    public static final Block FUEL_ASSEMBLY_CONTROLLER = new FuelAssemblyControllerBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK));
    public static final Block STEAM_TURBINE = new BlockWithItem(FabricBlockSettings.copyOf(Blocks.IRON_BARS));
}
