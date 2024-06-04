package satisfy.herbalbrews.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import satisfy.herbalbrews.client.HerbalbrewsClient;

public class HerbalbrewsClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HerbalbrewsClient.preInitClient();
        HerbalbrewsClient.initClient();
    }
}
