package satisfyu.herbalbrews.fabric.rei;


import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import satisfyu.herbalbrews.compat.rei.HerbalBrewsReiClientPlugin;

public class HerbalbrewsREIClientPluginFabric implements REIClientPlugin {

    @Override
    public void registerCategories(CategoryRegistry registry) {
        HerbalBrewsReiClientPlugin.registerCategories(registry);
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        HerbalBrewsReiClientPlugin.registerDisplays(registry);
    }
}
