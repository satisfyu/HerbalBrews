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
import net.satisfy.herbalbrews.core.blocks.entity.TeaKettleBlockEntity;
import net.satisfy.herbalbrews.core.compat.rei.display.TeaKettleDisplay;
import net.satisfy.herbalbrews.core.registry.ObjectRegistry;

import java.util.List;

public class TeaKettleCategory implements DisplayCategory<TeaKettleDisplay> {


    public static final CategoryIdentifier<TeaKettleDisplay> COOKING_CAULDRON_DISPLAY = CategoryIdentifier.of(HerbalBrews.MOD_ID, "tea_kettle_display");

    @Override
    public CategoryIdentifier<TeaKettleDisplay> getCategoryIdentifier() {
        return COOKING_CAULDRON_DISPLAY;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("rei.herbalbrews.tea_kettle_category");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ObjectRegistry.TEA_KETTLE.get());
    }

    @Override
    public int getDisplayHeight() {
        return 64;
    }

    @Override
    public List<Widget> setupDisplay(TeaKettleDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getX() + 16, bounds.getCenterY() - 18);
        List<Widget> widgets = Lists.newArrayList();
        widgets.add(Widgets.createRecipeBase(bounds));
     //   widgets.add(Widgets.createArrow(new Point(startPoint.x + 60, startPoint.y + 9)).animationDurationTicks(TeaKettleBlockEntity.MAX_COOKING_TIME));

        widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 96, startPoint.y + 9)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 96, startPoint.y + 9)).entries(display.getOutputEntries().get(0)).disableBackground().markOutput());

        for (int slot = 0; slot < 6; slot++) {
            if (display.getInputEntries().size() < slot + 1)
                widgets.add(Widgets.createSlotBackground(new Point(startPoint.x + 18 * (slot % 3), startPoint.y + 18 * (slot / 3))));
            else
                widgets.add(Widgets.createSlot(new Point(startPoint.x + 18 * (slot % 3), startPoint.y + 18 * (slot / 3))).entries(display.getInputEntries().get(slot)).markInput());
        }

        return widgets;
    }
}
