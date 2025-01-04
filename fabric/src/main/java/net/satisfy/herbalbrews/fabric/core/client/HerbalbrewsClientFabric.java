package net.satisfy.herbalbrews.fabric.core.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.satisfy.herbalbrews.client.HerbalbrewsClient;
import net.satisfy.herbalbrews.core.registry.ObjectRegistry;

public class HerbalbrewsClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HerbalbrewsClient.preInitClient();
        HerbalbrewsClient.onInitializeClient();

        ArmorRenderer.register(new HatArmorRenderer(), ObjectRegistry.TOP_HAT.get(), ObjectRegistry.WITCH_HAT.get());
    }
}
