package folk.lemonbook.item.legendaryeras;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("legendaryeras")
@Mod.EventBusSubscriber//����һ���¼�������
public class Main {
    @SubscribeEvent//�¼�������
    public  static  void playerJoinWorld(PlayerEvent.PlayerLoggedInEvent event){
        Player player =(Player) event.getEntity();
        Level level=player.level;
        player.sendSystemMessage(Component.nullToEmpty("xxx ������Ϸ"));
    }

}
