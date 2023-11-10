package satisfyu.herbalbrews.client.gui;

import de.cristelknight.doapi.client.recipebook.screen.AbstractRecipeBookGUIScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import satisfyu.herbalbrews.client.gui.handler.TeaKettleGuiHandler;
import satisfyu.herbalbrews.client.recipebook.TeaKettleRecipeBook;
import satisfyu.herbalbrews.util.HerbalBrewsIdentifier;

public class TeaKettleGui extends AbstractRecipeBookGUIScreen<TeaKettleGuiHandler> {
    public static final ResourceLocation BG = new HerbalBrewsIdentifier("textures/gui/tea_kettle.png");

    public static final int ARROW_Y = 45;
    public static final int ARROW_X = 94;

    public TeaKettleGui(TeaKettleGuiHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title, new TeaKettleRecipeBook(), BG);
    }

    @Override
    protected void init() {
        this.titleLabelX += 2;
        this.titleLabelY += -3;
        super.init();
    }

    @Override
    public void renderBurnIcon(GuiGraphics guiGraphics, int posX, int posY) {
        if (menu.isBeingBurned()) {
            guiGraphics.blit(BG, posX + 124, posY + 56, 176, 0, 17, 15);
        }
    }


    protected void renderProgressArrow(GuiGraphics guiGraphics) {
        final int progressX = this.menu.getShakeXProgress();
        guiGraphics.blit(BG, leftPos + ARROW_X, topPos + ARROW_Y, 177, 26, progressX, 10);
    }
}

