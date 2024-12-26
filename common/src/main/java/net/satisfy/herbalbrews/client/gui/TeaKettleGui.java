package net.satisfy.herbalbrews.client.gui;

import net.satisfy.herbalbrews.client.gui.handler.TeaKettleGuiHandler;
import net.satisfy.herbalbrews.core.util.HerbalBrewsIdentifier;
import org.joml.Vector2i;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class TeaKettleGui extends AbstractContainerScreen<TeaKettleGuiHandler> {
    private static final ResourceLocation BACKGROUND = new HerbalBrewsIdentifier("textures/gui/tea_kettle.png");
    private static final int ARROW_X = 92;
    private static final int ARROW_Y = 10;
    private final Vector2i screenPos = new Vector2i();

    public TeaKettleGui(TeaKettleGuiHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        screenPos.set(leftPos, topPos);
        titleLabelX += 2;
        titleLabelY += 3;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        guiGraphics.blit(BACKGROUND, screenPos.x(), screenPos.y(), 0, 0, imageWidth, imageHeight);
        int progress = this.menu.getScaledProgress(17);
        guiGraphics.blit(BACKGROUND, screenPos.x() + ARROW_X, screenPos.y() + ARROW_Y, 178, 16, progress, 29);
        if (menu.isBeingBurned()) {
            guiGraphics.blit(BACKGROUND, screenPos.x() + 124, screenPos.y() + 51, 176, 0, 17, 16);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
