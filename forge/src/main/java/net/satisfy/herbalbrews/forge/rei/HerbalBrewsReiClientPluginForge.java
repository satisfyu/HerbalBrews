package net.satisfy.herbalbrews.forge.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.forge.REIPluginClient;
import net.satisfy.herbalbrews.compat.rei.HerbalBrewsReiClientPlugin;

@REIPluginClient
public class HerbalBrewsReiClientPluginForge implements REIClientPlugin {

    @Override
    public void registerCategories(CategoryRegistry registry) {
        HerbalBrewsReiClientPlugin.registerCategories(registry);
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        HerbalBrewsReiClientPlugin.registerDisplays(registry);
    }
}
