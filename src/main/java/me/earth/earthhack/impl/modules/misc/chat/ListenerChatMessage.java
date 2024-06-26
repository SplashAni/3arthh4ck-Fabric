package me.earth.earthhack.impl.modules.misc.chat;

import me.earth.earthhack.impl.event.events.network.PacketEvent;
import me.earth.earthhack.impl.event.listeners.ModuleListener;
import me.earth.earthhack.impl.modules.client.commands.Commands;
import me.earth.earthhack.impl.modules.misc.chat.util.SuffixMode;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;

public class ListenerChatMessage extends ModuleListener<Chat, PacketEvent.Send<ChatMessageC2SPacket>> {
    public ListenerChatMessage(Chat module){
        super(module, PacketEvent.Send.class, ChatMessageC2SPacket.class);
    }

    @Override
    public void invoke(PacketEvent.Send<ChatMessageC2SPacket> e)
    {
        if (mc.player != null && mc.world != null) {
            if (module.suffixMode.getValue() != SuffixMode.None)
            {
                String message = e.getPacket().chatMessage();
                if (!message.startsWith(Commands.getPrefix())) {
                    if (module.suffixWhispers.getValue()
                            && (message.startsWith("/msg")
                            || message.startsWith("/r")
                            || message.startsWith("/w")
                            || message.startsWith("/tell")
                            || message.startsWith("/l")))
                    {
                        e.setCancelled(true);
                        sendMessage(message);
                    }
                    else if (!message.startsWith("/"))
                    {
                        e.setCancelled(true);
                        sendMessage(message);
                    }
                }
            }
        }
    }

    public void sendMessage(String message)
    {
        // String suffix = module.suffixMode.getValue() == SuffixMode.Custom
        //         ? module.customSuffix.getValue()
        //         : module.suffixMode.getValue().getSuffix();

        mc.getNetworkHandler().sendChatMessage(message  + " | " + module.suffixMode.getValue().getSuffix());
    }
}
