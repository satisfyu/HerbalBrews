package satisfyu.herbalbrews.client.gui;

import de.cristelknight.doapi.client.recipebook.screen.AbstractRecipeBookGUIScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import satisfyu.herbalbrews.client.gui.handler.TeaKettleGuiHandler;
import satisfyu.herbalbrews.client.recipebook.TeaKettleRecipeBook;
import satisfyu.herbalbrews.util.HerbalBrewsIdentifier;

@Environment(EnvType.CLIENT)
public class TeaKettleGui extends AbstractRecipeBookGUIScreen<TeaKettleGuiHandler> {
    public static final ResourceLocation BACKGROUND;

    public static final int ARROW_X = 95;
    public static final int ARROW_Y = 14;

    public TeaKettleGui(TeaKettleGuiHandler handler, Inventory playerInventory, Component title) {
        super(handler, playerInventory, title, new TeaKettleRecipeBook(), BACKGROUND);
    }

    @Override
    public void renderProgressArrow(GuiGraphics guiGraphics) {
        int progress = this.menu.getScaledProgress(18);
        guiGraphics.blit(BACKGROUND, this.leftPos + ARROW_X, this.topPos + ARROW_Y, 178, 15, progress, 30);
    }

    @Override
    public void renderBurnIcon(GuiGraphics guiGraphics, int posX, int posY) {
        if (menu.isBeingBurned()) {
            guiGraphics.blit(BACKGROUND, posX + 124, posY + 56, 176, 0, 17, 15);
        }
    }

    @Override
    protected void init() {
        this.titleLabelX += 2;
        this.titleLabelY += -3;
        super.init();
    }

    static {
        BACKGROUND = new HerbalBrewsIdentifier("textures/gui/tea_kettle.png");
    }
}

