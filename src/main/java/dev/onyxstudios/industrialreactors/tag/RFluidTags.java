package dev.onyxstudios.industrialreactors.tag;

import dev.onyxstudios.industrialreactors.IndustrialReactors;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.fluid.Fluid;
import net.minecraft.tag.Tag;

public class RFluidTags {
    public static final Tag<Fluid> STEAM_PROVIDER_FLUIDS = TagRegistry.fluid(IndustrialReactors.id("steam_providers"));
}
