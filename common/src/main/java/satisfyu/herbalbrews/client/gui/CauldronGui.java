package satisfyu.herbalbrews.client.gui;

import de.cristelknight.doapi.client.recipebook.screen.AbstractRecipeBookGUIScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import satisfyu.herbalbrews.client.gui.handler.CauldronGuiHandler;
import satisfyu.herbalbrews.client.recipebook.CauldronRecipeBook;
import satisfyu.herbalbrews.util.HerbalBrewsIdentifier;

public class CauldronGui extends AbstractRecipeBookGUIScreen<CauldronGuiHandler> {
    public static final ResourceLocation BG = new HerbalBrewsIdentifier("textures/gui/cauldron.png");

    public static final int ARROW_X = 93;
    public static final int ARROW_Y = 32;
    public CauldronGui(CauldronGuiHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title, new CauldronRecipeBook(), BG);
    }

    @Override
    protected void init() {
        this.titleLabelX += 2;
        this.titleLabelY += -3;
        super.init();
    }

    @Override
    public void renderProgressArrow(GuiGraphics guiGraphics) {
        int progress = this.menu.getScaledProgress(18);
        guiGraphics.blit(BG,leftPos + 93, topPos + 31, 178, 19, progress, 26);
    }

    @Override
    public void renderBurnIcon(GuiGraphics guiGraphics, int posX, int posY) {
        if (this.menu.isBeingBurned()) {
            guiGraphics.blit(BG, posX + 62, posY + 50, 176, 0, 17, 16);
        }
    }
}