package dev.onyxstudios.industrialreactors.init;

import dev.onyxstudios.industrialreactors.IndustrialReactors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class ReactorItemGroups {

    public static final ItemGroup ITEMS = FabricItemGroupBuilder.build(IndustrialReactors.id("items"), () -> new ItemStack(Items.IRON_INGOT));
}
