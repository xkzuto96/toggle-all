package xkzuto.toggleall.client.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.render.LightmapTextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xkzuto.toggleall.client.render.NoRenderToggleController;

@Mixin(LightmapTextureManager.class)
public class LightmapFullBrightMixin {
    @Unique
    private double savedGamma = -1.0;

    @Inject(method = "update", at = @At("HEAD"), require = 0)
    private void applyFullBright(float tickDelta, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.options == null) {
            return;
        }

        SimpleOption<Double> gammaOption = ((GameOptionsAccessor) (Object) client.options).getGamma();
        if (gammaOption == null) {
            return;
        }

        boolean enabled = NoRenderToggleController.fullBrightnessEnabled();
        double current = gammaOption.getValue();

        if (enabled) {
            if (current < 14.0) {
                savedGamma = current;
            }
            ((SimpleOptionAccessor) (Object) gammaOption).setRawValue(15.0);
        } else if (savedGamma >= 0.0) {
            ((SimpleOptionAccessor) (Object) gammaOption).setRawValue(savedGamma);
            savedGamma = -1.0;
        }
    }
}
