package net.satisfy.herbalbrews.client.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.satisfy.herbalbrews.client.gui.handler.CauldronGuiHandler;
import net.satisfy.herbalbrews.core.util.HerbalBrewsIdentifier;
import org.joml.Vector2i;

public class CauldronGui extends AbstractContainerScreen<CauldronGuiHandler> {
    private static final ResourceLocation BACKGROUND = new HerbalBrewsIdentifier("textures/gui/cauldron.png");
    private static final int PROGRESS_X = 114;
    private static final int PROGRESS_Y = 38;
    private static final int PROGRESS_TEX_X = 176;
    private static final int PROGRESS_TEX_Y = 0;
    private static final int PROGRESS_WIDTH = 11;
    private static final int PROGRESS_HEIGHT = 29;
    private final Vector2i screenPos = new Vector2i();

    public CauldronGui(CauldronGuiHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        screenPos.set(leftPos, topPos);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        guiGraphics.blit(BACKGROUND, screenPos.x(), screenPos.y(), 0, 0, imageWidth, imageHeight);
        int progress = this.menu.getScaledProgress(PROGRESS_HEIGHT);
        guiGraphics.blit(BACKGROUND, screenPos.x() + PROGRESS_X, screenPos.y() + PROGRESS_Y + (PROGRESS_HEIGHT - progress), PROGRESS_TEX_X, PROGRESS_TEX_Y, PROGRESS_WIDTH, progress);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
