package satisfyu.herbalbrews.fabric.terrablender;

import satisfyu.herbalbrews.terrablender.HerbalBrewsRegion;
import terrablender.api.TerraBlenderApi;

public class TerrablenderFabric implements TerraBlenderApi {

    @Override
    public void onTerraBlenderInitialized() {
        HerbalBrewsRegion.loadTerrablender();
    }
}

