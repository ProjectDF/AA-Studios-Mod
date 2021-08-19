package io.github.aastudiosmod.commands.impl.item;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.aastudiosmod.commands.ArgBuilder;
import io.github.aastudiosmod.commands.Command;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;

import net.minecraft.client.MinecraftClient;
import net.minecraft.command.argument.ItemStackArgument;
import net.minecraft.command.argument.ItemStackArgumentType;
import net.minecraft.item.ItemStack;

public class GiveCommand extends Command {

    private static final MinecraftClient MC = MinecraftClient.getInstance();

    @Override
    public LiteralArgumentBuilder<FabricClientCommandSource> getCommand() {
        return ArgBuilder.literal("aagive")
                .then(ArgBuilder.argument("item", ItemStackArgumentType.itemStack())
                    .executes(ctx -> {
                        ItemStack item = ctx.getArgument("item", ItemStackArgument.class).createStack(1, false);

                        MC.interactionManager.clickCreativeStack(item, MC.player.getInventory().selectedSlot + 36);
                        return 1;
                    }))
                .then(ArgBuilder.literal("clipboard")
                        .executes(ctx -> {
                            String clipboard = MC.keyboard.getClipboard();

                            MC.player.sendChatMessage("/aagive " + clipboard);
                            return 1;
                        }));
    }

}
