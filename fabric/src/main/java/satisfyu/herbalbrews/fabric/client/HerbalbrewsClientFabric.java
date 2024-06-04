package satisfyu.herbalbrews.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import satisfyu.herbalbrews.client.HerbalbrewsClient;

public class HerbalbrewsClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HerbalbrewsClient.preInitClient();
        HerbalbrewsClient.initClient();
    }
}
