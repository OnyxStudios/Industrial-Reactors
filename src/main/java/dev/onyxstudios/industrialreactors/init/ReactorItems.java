package dev.onyxstudios.industrialreactors.init;

import dev.onyxstudios.industrialreactors.IndustrialReactors;
import io.github.glasspane.mesh.api.annotation.AutoRegistry;
import net.minecraft.item.Item;

@AutoRegistry.Register(modid = IndustrialReactors.MODID, registry = "minecraft:item", value = Item.class)
public class ReactorItems {

    public static final Item ENRICHED_URANIUM_INGOT = new Item(new Item.Settings().group(ReactorItemGroups.ITEMS));
    public static final Item GEIGER_COUNTER = new Item(new Item.Settings().group(ReactorItemGroups.ITEMS));
    public static final Item GRAPHITE = new Item(new Item.Settings().group(ReactorItemGroups.ITEMS));
    public static final Item LEAD_INGOT = new Item(new Item.Settings().group(ReactorItemGroups.ITEMS));
    public static final Item STEEL_INGOT = new Item(new Item.Settings().group(ReactorItemGroups.ITEMS));
    public static final Item THERMOMETER = new Item(new Item.Settings().group(ReactorItemGroups.ITEMS));
    public static final Item URANIUM_FUEL_PELLET = new Item(new Item.Settings().group(ReactorItemGroups.ITEMS));
    public static final Item URANIUM_FUEL_ROD = new Item(new Item.Settings().group(ReactorItemGroups.ITEMS));
    public static final Item URANIUM_INGOT = new Item(new Item.Settings().group(ReactorItemGroups.ITEMS));
}
