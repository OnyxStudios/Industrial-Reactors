package dev.onyxstudios.industrialreactors;

import dev.onyxstudios.industrialreactors.data.BlockDataManager;
import io.github.glasspane.mesh.api.logging.MeshLoggerFactory;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

public class IndustrialReactors implements ModInitializer {

    public static final String MODID = "industrial_reactors";

    private static final Logger log = MeshLoggerFactory.createPrefixLogger(MODID, "Industrial Reactors");

    public static Identifier id(String path) {
        return new Identifier(MODID, path.toLowerCase(Locale.ROOT));
    }

    public static Logger getLogger() {
        return log;
    }

    @Override
    public void onInitialize() {
        log.info("Fission go BRRRRR");
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new BlockDataManager());
    }
}
