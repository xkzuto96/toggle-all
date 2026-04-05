package xkzuto.toggleall.client.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xkzuto.toggleall.client.render.NoRenderToggleController;

@Mixin(Screen.class)
public class ScreenNoRenderMixin {
    @Inject(method = "renderBackground", at = @At("HEAD"), cancellable = true, require = 0)
    private void cancelBackground(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (NoRenderToggleController.backgroundSuppressionEnabled()) {
            ci.cancel();
        }
    }
}
