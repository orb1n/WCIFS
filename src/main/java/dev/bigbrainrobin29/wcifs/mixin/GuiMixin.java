package dev.bigbrainrobin29.wcifs.mixin;

import dev.bigbrainrobin29.wcifs.WCIFS;
import net.minecraft.client.gui.Gui;
import net.minecraft.network.chat.Component;
//? >= 1.19 {
import net.minecraft.network.chat.contents.TranslatableContents;
//?} else {
/*import net.minecraft.network.chat.TranslatableComponent;
*///?}
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {
    @Inject(at = @At("HEAD"), method = "setOverlayMessage", cancellable = true)
	private void init(Component component, boolean bl, CallbackInfo info) {
		//? >= 1.19 {
		boolean shouldInject = component.getContents() instanceof TranslatableContents translatableContents && translatableContents.getKey().equals("block.minecraft.bed.no_sleep");
		//?} else {
		/*boolean shouldInject = component instanceof TranslatableComponent translatableText && translatableText.getKey().equals("block.minecraft.bed.no_sleep");
		*///?}

		if (shouldInject) {
			component = component.copy().append(WCIFS.getTimeUntilNightComponent());
			//? >= 1.19 && < 26.1
			//((Gui)(Object)this).setChatDisabledByPlayerShown(false);

			((Gui)(Object)this).overlayMessageString = component;
			((Gui)(Object)this).overlayMessageTime = 60;
			((Gui)(Object)this).animateOverlayMessageColor = bl;

			info.cancel();
		}
	}
}