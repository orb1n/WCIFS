package dev.orb1n.wcifs;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
//? < 1.19
//import net.minecraft.network.chat.TranslatableComponent;

public class WCIFS {
    public static Component getTimeUntilNightComponent() {
        int firstSleepTick = Minecraft.getInstance().level.isRaining() ? 12010 : 12542;
        long currentDayTick = Minecraft.getInstance().level./*? >= 26.1 {*/getDefaultClockTime()/*?} else {*//*getDayTime()*//*?}*/ % 24000L;

        long ticksUntilNight = firstSleepTick - currentDayTick;

        long totalSeconds = ticksUntilNight / 20;
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;

        String string = (minutes == 0 ? "" : minutes + "m ") + seconds + "s";

        //? if >= 1.19 {
        return Component.translatable("block.minecraft.bed.time_until_night", string);
        //?} else {
        /*return new TranslatableComponent("block.minecraft.bed.time_until_night", string);
         *///?}
    }
}
