package dev.onyxstudios.industrialreactors.tag;

import dev.onyxstudios.industrialreactors.IndustrialReactors;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;

public class RBlockTags {

    public static final Tag<Block> STEAM_SOURCE_BLOCKS = TagRegistry.block(IndustrialReactors.id("steam_sources"));
}
