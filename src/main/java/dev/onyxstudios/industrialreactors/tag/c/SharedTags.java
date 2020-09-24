package dev.onyxstudios.industrialreactors.tag.c;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

import java.util.Locale;

public class SharedTags {

    protected static Identifier id(String path) {
        return new Identifier("c", path.toLowerCase(Locale.ROOT));
    }

    protected static Tag<Item> item(String path) {
        return TagRegistry.item(id(path));
    }

    protected static Tag<Block> block(String path) {
        return TagRegistry.block(id(path));
    }
}
