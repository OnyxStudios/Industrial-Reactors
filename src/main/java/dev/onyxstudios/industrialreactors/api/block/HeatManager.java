package dev.onyxstudios.industrialreactors.api.block;

import dev.onyxstudios.industrialreactors.data.HeatManagerImpl;
import net.minecraft.block.Block;

public interface HeatManager {

    HeatManager INSTANCE = HeatManagerImpl.INSTANCE;

    float getHeatTransferRate(Block block);
}
