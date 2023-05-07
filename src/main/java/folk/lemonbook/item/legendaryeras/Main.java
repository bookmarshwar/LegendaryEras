package folk.lemonbook.item.legendaryeras;

import folk.lemonbook.item.legendaryeras.init.BlockInit;
import folk.lemonbook.item.legendaryeras.init.ItemInit;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Main.MOD_ID)
public class Main {
    public static final String MOD_ID="legendaryeras";
    public Main(){
        MinecraftForge.EVENT_BUS.register(this);
        ItemInit.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BlockInit.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
