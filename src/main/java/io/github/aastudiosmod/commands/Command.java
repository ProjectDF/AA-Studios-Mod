package io.github.aastudiosmod.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;


public abstract class Command {

    public abstract LiteralArgumentBuilder<FabricClientCommandSource> getCommand();

    public static void registerCommands(Command... commands) {
        for (Command command : commands) {
            ClientCommandManager.DISPATCHER.register(command.getCommand());
        }
    }
}
