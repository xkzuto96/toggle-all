package xkzuto.toggleall.client.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xkzuto.toggleall.client.render.NoRenderToggleController;

@Mixin(InGameHud.class)
public class InGameHudNoRenderMixin {
    @Inject(method = "renderChat", at = @At("HEAD"), cancellable = true, require = 0)
    private void cancelChat(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (NoRenderToggleController.hideChatEnabled()) {
            ci.cancel();
        }
    }

    @Inject(method = "renderScoreboardSidebar", at = @At("HEAD"), cancellable = true, require = 0)
    private void cancelScoreboard(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (NoRenderToggleController.hideScoreboardEnabled()) {
            ci.cancel();
        }
    }
}
