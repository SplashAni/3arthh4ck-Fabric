package me.earth.earthhack.impl.modules.movement.speed;

import me.earth.earthhack.impl.event.events.network.PacketEvent;
import me.earth.earthhack.impl.event.listeners.ModuleListener;
import me.earth.earthhack.impl.managers.Managers;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;

final class ListenerPosLook extends
        ModuleListener<Speed, PacketEvent.Receive<PlayerPositionLookS2CPacket>>
{
    public ListenerPosLook(Speed module)
    {
        super(module, PacketEvent.Receive.class, PlayerPositionLookS2CPacket.class);
    }

    @Override
    public void invoke(PacketEvent.Receive<PlayerPositionLookS2CPacket> event)
    {
        if (mc.player != null)
        {
            module.distance = 0.0;
            module.speed = 0.0; // not used??????
        }

        module.speed = 0.0;
        module.vanillaStage   = 0;
        module.onGroundStage  = 2;
        module.ncpStage    = 1;
        module.gayStage    = 1;
        module.vStage      = 1;
        module.bhopStage   = 4;
        module.stage       = 4;
        module.lowStage    = 4;
        module.constStage = 0;
        Managers.TIMER.setTimer(1.0f);
    }

}
