package dev.onyxstudios.industrialreactors;

import io.github.glasspane.mesh.api.logging.MeshLoggerFactory;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.Logger;

public class IndustrialReactors implements ModInitializer {

    public static final String MODID = "industrial_reactors";

    private static final Logger log = MeshLoggerFactory.createPrefixLogger(MODID, "Industrial Reactors");

    public static Logger getLogger() {
        return log;
    }

    @Override
    public void onInitialize() {
        log.info("Fission go BRRRRR");
    }
}
