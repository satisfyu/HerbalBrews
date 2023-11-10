package satisfyu.herbalbrews.forge;

import satisfyu.herbalbrews.HerbalBrewsExpectPlatform;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class HerbalBrewsExpectPlatformImpl {
    /**
     * This is our actual method to {@link HerbalBrewsExpectPlatform#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }
}
