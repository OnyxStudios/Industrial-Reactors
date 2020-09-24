package dev.onyxstudios.industrialreactors.data;

import dev.onyxstudios.industrialreactors.api.block.HeatManager;
import it.unimi.dsi.fastutil.objects.Object2FloatArrayMap;
import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import net.minecraft.block.Block;

public class HeatManagerImpl implements HeatManager {

    public static final HeatManagerImpl INSTANCE = new HeatManagerImpl();
    private final Object2FloatMap<Block> heatTransferData = new Object2FloatArrayMap<>();

    private HeatManagerImpl() {
        //NO-OP
    }

    void reloadData(Object2FloatMap<Block> data) {
        synchronized (heatTransferData) {
            heatTransferData.clear();
            heatTransferData.putAll(data);
        }
    }


    @Override
    public float getHeatTransferRate(Block block) {
        return heatTransferData.getOrDefault(block, 1.0F);
    }
}
