package xkzuto.toggleall.client.mixin;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xkzuto.toggleall.client.render.NoRenderToggleController;

@Mixin(ClientWorld.class)
public class ClientWorldNoRenderMixin {
    @Inject(method = "addParticleClient(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V", at = @At("HEAD"), cancellable = true, require = 0)
    private void cancelParticle(ParticleEffect effect,
            double x, double y, double z,
            double vx, double vy, double vz,
            CallbackInfo ci) {
        if (NoRenderToggleController.hideParticlesEnabled()) {
            ci.cancel();
        }
    }

    @Inject(method = "addParticleClient(Lnet/minecraft/particle/ParticleEffect;ZZDDDDDD)V", at = @At("HEAD"), cancellable = true, require = 0)
    private void cancelParticleForced(ParticleEffect effect, boolean force, boolean canSpawnOnMinimal,
            double x, double y, double z,
            double vx, double vy, double vz,
            CallbackInfo ci) {
        if (NoRenderToggleController.hideParticlesEnabled()) {
            ci.cancel();
        }
    }
}
