package dev.onyxstudios.industrialreactors.data;

import dev.onyxstudios.industrialreactors.IndustrialReactors;
import dev.onyxstudios.industrialreactors.api.block.HeatManager;
import dev.onyxstudios.industrialreactors.tag.c.SharedBlockTags;
import it.unimi.dsi.fastutil.objects.Object2FloatArrayMap;
import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.resource.ResourceReloadListenerKeys;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.block.Block;
import net.minecraft.resource.ResourceManager;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Consumer;

public class BlockDataManager implements SimpleSynchronousResourceReloadListener {

    public static final Identifier RESOURCEMANAGER_ID = IndustrialReactors.id("block_data");

    @Override
    public Identifier getFabricId() {
        return RESOURCEMANAGER_ID;
    }

    @Override
    public void apply(ResourceManager manager) {
        Builder.create()
                .putTag(SharedBlockTags.AIR, 0.1F, 0.1F)
                .build(HeatManagerImpl.INSTANCE::reloadData, RadiationManagerImpl.INSTANCE::reloadData);
    }

    @Override
    public Collection<Identifier> getFabricDependencies() {
        return Collections.singleton(ResourceReloadListenerKeys.TAGS);
    }

    private void register(Tag<Block> blocks, float heatTransferRate, float radiationShielding, Object2FloatMap<Block> heat, Object2FloatMap<Block> rad) {
        blocks.values().forEach(it -> register(it, heatTransferRate, radiationShielding, heat, rad));
    }

    private void register(Block block, float heatTransferRate, float radiationShielding, Object2FloatMap<Block> heat, Object2FloatMap<Block> rad) {
        heat.put(block, heatTransferRate);
        rad.put(block, radiationShielding);
    }

    private static class Builder {

        private final Object2FloatMap<Block> heatData = new Object2FloatArrayMap<>();
        private final Object2FloatMap<Tag<Block>> tagHeatData = new Object2FloatArrayMap<>();
        private final Object2FloatMap<Block> radiationData = new Object2FloatArrayMap<>();
        private final Object2FloatMap<Tag<Block>> tagRadiationData = new Object2FloatArrayMap<>();

        private Builder() {
            //NO-OP
        }

        public static Builder create() {
            return new Builder();
        }

        Builder putHeat(Block block, float heatTransfer) {
            if(heatData.containsKey(block)) {
                throw new IllegalStateException("duplicate heat transfer entry for block " + Registry.BLOCK.getId(block));
            }
            heatData.put(block, heatTransfer);
            return this;
        }

        Builder putRadiation(Block block, float radiationShielding) {
            if (radiationData.containsKey(block)) {
                throw new IllegalStateException("duplicate radiation shielding entry for block " + Registry.BLOCK.getId(block));
            }
            radiationData.put(block, radiationShielding);
            return this;
        }

        Builder put(Block block, float heatTransfer, float radiationShielding) {
            return putHeat(block, heatTransfer).putRadiation(block, radiationShielding);
        }

        Builder putTag(Tag<Block> tag, float heatTransfer, float radiationShielding) {
            if(tagHeatData.containsKey(tag) || tagRadiationData.containsKey(tag)) {
                throw new IllegalStateException("duplicate entry for tag");
            }
            tagHeatData.put(tag, heatTransfer);
            tagRadiationData.put(tag, radiationShielding);
            return this;
        }

        public void build(Consumer<Object2FloatMap<Block>> heatConsumer, Consumer<Object2FloatMap<Block>> radiationConsumer) {
            tagHeatData.keySet().forEach(tag -> {
                float heatTransfer = tagHeatData.getFloat(tag);
                tag.values().forEach(it -> heatData.putIfAbsent(it, heatTransfer));
            });
            tagRadiationData.keySet().forEach(tag -> {
                float radiationShielding = tagRadiationData.getFloat(tag);
                tag.values().forEach(it -> radiationData.putIfAbsent(it, radiationShielding));
            });
            heatConsumer.accept(heatData);
            radiationConsumer.accept(radiationData);
        }
    }
}
