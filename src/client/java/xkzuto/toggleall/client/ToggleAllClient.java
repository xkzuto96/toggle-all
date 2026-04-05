package xkzuto.toggleall.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import xkzuto.toggleall.client.render.NoRenderToggleController;

public class ToggleAllClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		NoRenderToggleController.initialize();

		ClientTickEvents.END_CLIENT_TICK.register(ToggleAllClient::onEndTick);
	}

	private static void onEndTick(MinecraftClient client) {
		if (client == null || client.getWindow() == null) {
			return;
		}

		NoRenderToggleController.onEndTick(client);
	}
}