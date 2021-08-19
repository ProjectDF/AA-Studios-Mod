package io.github.aastudiosmod;

import io.github.aastudiosmod.commands.Command;
import io.github.aastudiosmod.commands.impl.item.GiveCommand;
import io.github.aastudiosmod.commands.impl.other.ResourcePackCommand;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AAStudiosMod implements ModInitializer {

    private static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();

    @Override
    public void onInitialize() {
        System.out.println(FabricLoader.getInstance().getGameDir());


        Command.registerCommands(
                new ResourcePackCommand(),
                new GiveCommand());

    }

    public static void submitTask(Runnable runnable) {
        EXECUTOR.submit(runnable);
    }

}
