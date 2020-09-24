package dev.onyxstudios.industrialreactors.block;

import dev.onyxstudios.industrialreactors.init.ReactorItemGroups;
import io.github.glasspane.mesh.api.registry.BlockItemProvider;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import org.jetbrains.annotations.Nullable;

public class BlockWithItem extends Block implements BlockItemProvider {

    public BlockWithItem(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable Item createItem() {
        return new BlockItem(this, new Item.Settings().group(ReactorItemGroups.ITEMS));
    }
}
