package folk.lemonbook.item.legendaryeras.init;

import folk.lemonbook.item.legendaryeras.Main;
import folk.lemonbook.item.legendaryeras.gui.CombustionChamberMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuInit {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES=DeferredRegister.create(ForgeRegistries.MENU_TYPES, Main.MOD_ID);
    public  static final RegistryObject<MenuType<CombustionChamberMenu>> COMBUSTION_CHAMBER_MENU =registryObject(CombustionChamberMenu::new,"combustion_chamber_menu");
    public static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registryObject(IContainerFactory<T> factory,String name){
        return  MENU_TYPES.register(name,()-> IForgeMenuType.create(factory));
    }
}
