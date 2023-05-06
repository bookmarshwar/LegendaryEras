package folk.lemonbook.item.legendaryeras.core.init;

import folk.lemonbook.item.legendaryeras.Main;
import folk.lemonbook.item.legendaryeras.common.item.PowerCrank;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);//×¢²á¶ÔÏó
    public static final RegistryObject<PowerCrank> powerCrank =ITEMS.register("powercrank",() -> new PowerCrank(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
}
