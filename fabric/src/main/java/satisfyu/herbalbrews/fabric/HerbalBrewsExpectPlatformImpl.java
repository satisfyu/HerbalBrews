package satisfyu.herbalbrews.fabric;

import satisfyu.herbalbrews.HerbalBrewsExpectPlatform;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class HerbalBrewsExpectPlatformImpl {
    /**
     * This is our actual method to {@link HerbalBrewsExpectPlatform#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
