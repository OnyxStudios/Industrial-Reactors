package dev.onyxstudios.industrialreactors.data;

import dev.onyxstudios.industrialreactors.api.block.RadiationManager;
import it.unimi.dsi.fastutil.objects.Object2FloatArrayMap;
import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import net.minecraft.block.Block;

public class RadiationManagerImpl implements RadiationManager {

    public static final RadiationManagerImpl INSTANCE = new RadiationManagerImpl();
    private final Object2FloatMap<Block> radiationShieldingData = new Object2FloatArrayMap<>();

    @Override
    public float getRadiationShielding(Block block) {
        return radiationShieldingData.getOrDefault(block, 0.0F);
    }

    void reloadData(Object2FloatMap<Block> data) {
        synchronized (radiationShieldingData) {
            radiationShieldingData.clear();
            radiationShieldingData.putAll(data);
        }
    }
}
