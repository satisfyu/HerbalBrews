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
import net.satisfy.herbalbrews.HerbalBrews;
import net.satisfy.herbalbrews.core.compat.rei.display.TeaKettleDisplay;
import net.satisfy.herbalbrews.core.registry.ObjectRegistry;

import java.util.List;

public class TeaKettleCategory implements DisplayCategory<TeaKettleDisplay> {
    public static final CategoryIdentifier<TeaKettleDisplay> TEA_KETTLE_DISPLAY = CategoryIdentifier.of(HerbalBrews.MOD_ID, "tea_kettle_display");

    @Override
    public CategoryIdentifier<TeaKettleDisplay> getCategoryIdentifier() {
        return TEA_KETTLE_DISPLAY;
    }

    @Override
    public Component getTitle() {
        return ObjectRegistry.TEA_KETTLE.get().getName();
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ObjectRegistry.TEA_KETTLE.get());
    }

    @Override
    public int getDisplayHeight() {
        return 70;
    }

    @Override
    public List<Widget> setupDisplay(TeaKettleDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getX() + 10, bounds.getY() + 10);
        List<Widget> widgets = Lists.newArrayList();

        widgets.add(Widgets.createArrow(new Point(startPoint.x + 54, startPoint.y + 22)).animationDurationTicks(200));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 85, startPoint.y + 22))
                .entries(display.getOutputEntries().get(0))
                .markOutput());

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 31, startPoint.y + 30))
                .entries(display.getInputEntries().get(0))
                .markInput());

        for (int slot = 1; slot < 4; slot++) {
            int x = 13 + ((slot - 1) % 2) * 18;
            int y = 12 + ((slot - 1) / 2) * 18;
            widgets.add(Widgets.createSlot(new Point(startPoint.x + x, startPoint.y + y))
                    .entries(display.getInputEntries().get(slot))
                    .markInput());
        }

        return widgets;
    }
}
