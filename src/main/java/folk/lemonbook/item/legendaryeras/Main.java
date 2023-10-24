package folk.lemonbook.item.legendaryeras;

import folk.lemonbook.item.legendaryeras.gui.CokingFurnaceScreen;
import folk.lemonbook.item.legendaryeras.gui.CombustionChamberScreen;
import folk.lemonbook.item.legendaryeras.init.BlockInit;
import folk.lemonbook.item.legendaryeras.init.EntityInit;
import folk.lemonbook.item.legendaryeras.init.ItemInit;
import folk.lemonbook.item.legendaryeras.init.MenuInit;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Main.MOD_ID)
public class Main {
    public static final String MOD_ID="legendaryeras";
    public Main(){
        MinecraftForge.EVENT_BUS.register(this);
        ItemInit.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BlockInit.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        EntityInit.BLOCK_ENTITIES.register((FMLJavaModLoadingContext.get().getModEventBus()));
        MenuInit.MENU_TYPES.register((FMLJavaModLoadingContext.get().getModEventBus()));
    }
    @Mod.EventBusSubscriber(modid = MOD_ID,bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents{
        @SubscribeEvent
        public static void onClientSetUp(FMLClientSetupEvent event){
            MenuScreens.register(MenuInit.COMBUSTION_CHAMBER_MENU.get(), CombustionChamberScreen::new);
            MenuScreens.register(MenuInit.COKING_FURNACE_MENU.get(), CokingFurnaceScreen::new);
        }
    }
}
