package net.satisfy.herbalbrews.core.compat.rei.category;

import com.google.common.collect.Lists;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;
import net.satisfy.herbalbrews.core.compat.rei.display.CauldronDisplay;
import net.satisfy.herbalbrews.core.registry.ObjectRegistry;

import java.util.List;

public class CauldronCategory implements DisplayCategory<CauldronDisplay> {

    @Override
    public CategoryIdentifier<CauldronDisplay> getCategoryIdentifier() {
        return CauldronDisplay.CAULDRON_DISPLAY;
    }

    @Override
    public Component getTitle() {
        return ObjectRegistry.CAULDRON.get().getName();
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ObjectRegistry.CAULDRON.get());
    }

    @Override
    public List<Widget> setupDisplay(CauldronDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 55, bounds.getCenterY() - 13);
        List<Widget> widgets = Lists.newArrayList();
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createArrow(new Point(startPoint.x + 54, startPoint.y - 1)).animationDurationTicks(50));
        widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 90, startPoint.y)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 90, startPoint.y))
                .entries(display.getOutputEntries().get(0))
                .disableBackground()
                .markOutput());

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 53 - 40, startPoint.y +11))
                .entries(display.getInputEntries().get(0))
                .markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 31 - 40, startPoint.y - 6))
                .entries(display.getInputEntries().get(1))
                .markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 75 - 40, startPoint.y - 6))
                .entries(display.getInputEntries().get(2))
                .markInput());

        return widgets;
    }
}
