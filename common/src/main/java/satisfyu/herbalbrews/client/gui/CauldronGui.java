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

    public static final int ARROW_Y = 45;
    public static final int ARROW_X = 94;

    public static final int SHAKE_Y = 42;
    public static final int SHAKE_X = 96;

    public CauldronGui(CauldronGuiHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title, new CauldronRecipeBook(), BG);
    }

    @Override
    protected void init() {
        this.titleLabelX += 2;
        this.titleLabelY += -3;
        super.init();
    }


    protected void renderProgressArrow(GuiGraphics guiGraphics) {
        final int progressX = this.menu.getShakeXProgress();
        guiGraphics.blit(BG, leftPos + ARROW_X, topPos + ARROW_Y, 177, 26, progressX, 10);

        final int progressY = menu.slots.get(0).hasItem() ? 20 : this.menu.getShakeYProgress();
        guiGraphics.blit(BG, leftPos + SHAKE_X, topPos + SHAKE_Y - progressY, 179, 22 - progressY, 15, progressY);
    }
}

