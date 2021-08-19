package io.github.aastudiosmod.commands.impl.other;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.aastudiosmod.AAStudiosMod;
import io.github.aastudiosmod.commands.ArgBuilder;
import io.github.aastudiosmod.commands.Command;
import io.github.aastudiosmod.util.UnzipUtil;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ResourcePackCommand extends Command {

    @Override
    public LiteralArgumentBuilder<FabricClientCommandSource> getCommand() {
        return ArgBuilder.literal("resourcepack")
                .executes(ctx -> {
                    ClientPlayerEntity player = MinecraftClient.getInstance().player;

                    player.sendMessage(Text.of("§eDownloading resource pack..."), false);

                    AAStudiosMod.submitTask(() -> {
                        try {
                            String gameDir = FabricLoader.getInstance().getGameDir().toString();
                            gameDir = gameDir.substring(gameDir.length());

                            InputStream in = new URL("https://github.com/Shiverdog/DF-Community/archive/refs/heads/main.zip").openStream();
                            String pathStr = gameDir + "resourcepacks\\DF-Community.zip";

                            Path path = Paths.get(pathStr);
                            Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
                            UnzipUtil unzipUtil = new UnzipUtil();
                            unzipUtil.unzip(pathStr, gameDir + "resourcepacks");

                            Files.delete(path);

                            player.sendMessage(Text.of("§aDF-Community resourcepack has been downloaded!"), false);
                        } catch (IOException e) {
                            player.sendMessage(Text.of("§cAn error occurred when downloading the pack " + e), false);
                            e.printStackTrace();
                        }
                    });

                    return 1;
                });
    }
}