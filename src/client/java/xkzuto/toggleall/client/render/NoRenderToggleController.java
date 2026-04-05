package xkzuto.toggleall.client.render;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public final class NoRenderToggleController {
    private static final KeyBinding.Category KEY_CATEGORY = KeyBinding.Category
            .create(Identifier.of("toggle-all", "controls"));
    private static final String KEY_HIDE_CHAT = "key.toggle_all.hide_chat";
    private static final String KEY_HIDE_SCOREBOARD = "key.toggle_all.hide_scoreboard";
    private static final String KEY_HIDE_PARTICLES = "key.toggle_all.hide_particles";
    private static final String KEY_FULL_BRIGHTNESS = "key.toggle_all.full_brightness";
    private static final String KEY_SUPPRESS_BACKGROUND = "key.toggle_all.background_suppression";

    private static KeyBinding hideChatKey;
    private static KeyBinding hideScoreboardKey;
    private static KeyBinding hideParticlesKey;
    private static KeyBinding fullBrightnessKey;
    private static KeyBinding backgroundSuppressionKey;

    private static boolean hideChat;
    private static boolean hideScoreboard;
    private static boolean hideParticles;
    private static boolean fullBrightness;
    private static boolean suppressBackground;

    private NoRenderToggleController() {
    }

    public static void initialize() {
        if (hideChatKey != null) {
            return;
        }

        hideChatKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_HIDE_CHAT,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_ALT,
                KEY_CATEGORY));

        hideScoreboardKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_HIDE_SCOREBOARD,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                KEY_CATEGORY));

        hideParticlesKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_HIDE_PARTICLES,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                KEY_CATEGORY));

        fullBrightnessKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_FULL_BRIGHTNESS,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                KEY_CATEGORY));

        backgroundSuppressionKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_SUPPRESS_BACKGROUND,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                KEY_CATEGORY));
    }

    public static void onEndTick(MinecraftClient client) {
        if (client == null || hideChatKey == null) {
            return;
        }

        while (hideChatKey.wasPressed()) {
            hideChat = !hideChat;
        }

        while (hideScoreboardKey.wasPressed()) {
            hideScoreboard = !hideScoreboard;
        }

        while (hideParticlesKey.wasPressed()) {
            hideParticles = !hideParticles;
        }

        while (fullBrightnessKey.wasPressed()) {
            fullBrightness = !fullBrightness;
        }

        while (backgroundSuppressionKey.wasPressed()) {
            suppressBackground = !suppressBackground;
        }
    }

    public static boolean hideChatEnabled() {
        return hideChat;
    }

    public static boolean hideScoreboardEnabled() {
        return hideScoreboard;
    }

    public static boolean hideParticlesEnabled() {
        return hideParticles;
    }

    public static boolean fullBrightnessEnabled() {
        return fullBrightness;
    }

    public static boolean backgroundSuppressionEnabled() {
        return suppressBackground;
    }
}