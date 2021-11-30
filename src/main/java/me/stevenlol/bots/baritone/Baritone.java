package me.stevenlol.bots.baritone;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.command.CommandManager;

import java.util.concurrent.TimeUnit;

public class Baritone implements ModInitializer {
    @Override
    public void onInitialize() {

        WebInteraction web = new WebInteraction();

        ClientCommandManager.DISPATCHER.register(
                ClientCommandManager.literal("init").executes(context -> {

                    while (true) {
                        try {
                            web.getCommand(MinecraftClient.getInstance().player.getName().asString()).thenAccept(command -> {
                                if (command.equals("ERROR_COMMAND_ALREADY_RAN")) return;
                                MinecraftClient.getInstance().player.sendChatMessage("#stop");
                                if (!command.startsWith("#")) MinecraftClient.getInstance().player.sendChatMessage("#" + command);
                                else MinecraftClient.getInstance().player.sendChatMessage(command);
                            }).exceptionally( throwable -> {
                                throwable.printStackTrace();
                                return null;
                            });
                            TimeUnit.SECONDS.sleep(6);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            break;
                        }
                    }

                    return 1;
                })
        );
        /*




         */

    }
}

/*

#goto 10 10 10



 */
