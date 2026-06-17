package dev.bigbrainrobin29.wcifs.mixin;

import dev.bigbrainrobin29.wcifs.WCIFS;
//? >= 26.2
import net.minecraft.client.gui.Hud;
//? < 26.2
//import net.minecraft.client.gui.Gui;
import net.minecraft.network.chat.Component;
//? >= 1.19 {
import net.minecraft.network.chat.contents.TranslatableContents;
//?} else {
/*import net.minecraft.network.chat.TranslatableComponent;
*///?}
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
//? >= 26.2
@Mixin(Hud.class)
//? < 26.2
//@Mixin(Gui.class)
public class GuiMixin {
    @ModifyVariable(at = @At("HEAD"), method = "setOverlayMessage", argsOnly = true, ordinal = 0)
	private Component wcifs$setOverlayMessage(Component component) {
		//? >= 1.19 {
		boolean shouldInject = component.getContents() instanceof TranslatableContents translatableContents && translatableContents.getKey().equals("block.minecraft.bed.no_sleep");
		//?} else {
		/*boolean shouldInject = component instanceof TranslatableComponent translatableText && translatableText.getKey().equals("block.minecraft.bed.no_sleep");
		*///?}

		if (shouldInject) {
			return component.copy().append(WCIFS.getTimeUntilNightComponent());
		}
        return component;
    }
}