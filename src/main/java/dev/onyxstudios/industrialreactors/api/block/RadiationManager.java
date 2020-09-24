package dev.onyxstudios.industrialreactors.api.block;

import dev.onyxstudios.industrialreactors.data.RadiationManagerImpl;
import net.minecraft.block.Block;

public interface RadiationManager {

    RadiationManager INSTANCE = RadiationManagerImpl.INSTANCE;

    float getRadiationShielding(Block block);
}
