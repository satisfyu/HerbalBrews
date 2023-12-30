package satisfyu.herbalbrews.client.gui;

import de.cristelknight.doapi.client.recipebook.screen.AbstractRecipeBookGUIScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import satisfyu.herbalbrews.client.gui.handler.CauldronGuiHandler;
import satisfyu.herbalbrews.client.recipebook.CauldronRecipeBook;
import satisfyu.herbalbrews.util.HerbalBrewsIdentifier;

@Environment(EnvType.CLIENT)
public class CauldronGui extends AbstractRecipeBookGUIScreen<CauldronGuiHandler> {

    public static ResourceLocation BACKGROUND = new HerbalBrewsIdentifier("textures/gui/cauldron.png");

    public static final int ARROW_X = 94;
    public static final int ARROW_Y = 37;

    public CauldronGui(CauldronGuiHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title, new CauldronRecipeBook(), BACKGROUND);
    }

    @Override
    protected void init() {
        this.titleLabelX += 2;
        this.titleLabelY += -3;
        super.init();
    }

    @Override
    protected void renderProgressArrow(GuiGraphics guiGraphics) {
        int progress = this.menu.getScaledProgress(23);
        guiGraphics.blit(BACKGROUND, leftPos + ARROW_X, topPos + ARROW_Y, 177, 17, progress, 10);
    }

}