package satisfyu.herbalbrews.client.gui;

import de.cristelknight.doapi.client.recipebook.screen.AbstractRecipeBookGUIScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import satisfyu.herbalbrews.HerbalBrews;
import satisfyu.herbalbrews.client.gui.handler.TeaKettleGuiHandler;
import satisfyu.herbalbrews.client.recipebook.TeaKettleRecipeBook;

@Environment(EnvType.CLIENT)
public class TeaKettleGui extends AbstractRecipeBookGUIScreen<TeaKettleGuiHandler> {
    public static final ResourceLocation BACKGROUND;

    public static final int ARROW_X = 92;
    public static final int ARROW_Y = 10;

    public TeaKettleGui(TeaKettleGuiHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title, new TeaKettleRecipeBook(), BACKGROUND);
    }

    @Override
    protected void init() {
        this.titleLabelX += 2;
        this.titleLabelY += 3;
        super.init();
    }

    @Override
    protected void renderProgressArrow(GuiGraphics guiGraphics) {
        int progress = this.menu.getScaledProgress(17);
        guiGraphics.blit(BACKGROUND, leftPos + ARROW_X, topPos + ARROW_Y, 178, 16, progress, 29);
    }

    @Override
    protected void renderBurnIcon(GuiGraphics guiGraphics, int posX, int posY) {
        if (menu.isBeingBurned()) {
            guiGraphics.blit(BACKGROUND, posX + 124, posY + 51, 176, 0, 17, 16);
        }
    }


    static {
        BACKGROUND = new ResourceLocation(HerbalBrews.MOD_ID, "textures/gui/tea_kettle.png");
    }
}