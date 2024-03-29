package folk.lemonbook.item.legendaryeras.init;

import folk.lemonbook.item.legendaryeras.Main;
import folk.lemonbook.item.legendaryeras.item.CokingCoal;
import folk.lemonbook.item.legendaryeras.item.Portrait;
import folk.lemonbook.item.legendaryeras.item.Portrait_son;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static class ModCreativeTab extends CreativeModeTab {
        public static final ModCreativeTab instance = new ModCreativeTab(CreativeModeTab.TABS.length, Main.MOD_ID);//mod类
        private ModCreativeTab(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(PORTRAIT.get());//获取PORTRAIT图标给项目栏//作者头像
        }
    }
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);//注册对象
    public static final RegistryObject<Portrait> PORTRAIT =ITEMS.register("portrait",() -> new Portrait(new Item.Properties().tab(ModCreativeTab.instance)));
    public static final RegistryObject<Portrait_son> PORTRAIT_SON =ITEMS.register("portrait_son",() -> new Portrait_son(new Item.Properties().tab(ModCreativeTab.instance).food(new FoodProperties.Builder()
            .nutrition(-1)//饱食度
            .saturationMod(10)//金色饱食度
            .effect(() -> new MobEffectInstance(MobEffects.JUMP, 100*20, 2), 1)//百分百给予一个跳跃提升2+1;
            .build())));//结束返回build
    //方块物品注册
    public static final RegistryObject<Item> KINETIC_ENERGY_CONVERTER= ITEMS.register("kinetic_energy_converter",()->new BlockItem(BlockInit.KINETIC_ENERGY_CONVERTER.get(), new Item.Properties().tab(ModCreativeTab.instance)));
    public static final RegistryObject<Item> POTENTIAL_ENERGY_CONVERTER =ITEMS.register("potential_energy_converter",()-> new BlockItem(BlockInit.POTENTIAL_ENERGY_CONVERTER.get(), new Item.Properties().tab(ModCreativeTab.instance)));
    public static final RegistryObject<Item> TURBINE =ITEMS.register("turbine",()-> new BlockItem(BlockInit.TURBINE.get(), new Item.Properties().tab(ModCreativeTab.instance)));
    public static final RegistryObject<Item> GPEG_STRUCTURAL_BODIES =ITEMS.register("gpeg_structuralbodies",()-> new BlockItem(BlockInit.GPEG_STRUCTURAL_BODIES.get(), new Item.Properties().tab(ModCreativeTab.instance)));
    public static final RegistryObject<Item> GPEG_TOP =ITEMS.register("gpeg_top",()-> new BlockItem(BlockInit.GPEG_TOP.get(), new Item.Properties().tab(ModCreativeTab.instance)));
    public static final RegistryObject<Item> GPEG_BASE =ITEMS.register("gpeg_base",()-> new BlockItem(BlockInit.GPEG_BASE.get(), new Item.Properties().tab(ModCreativeTab.instance)));
    public static final RegistryObject<Item> STEAM_BOILER =ITEMS.register("steam_boiler",()-> new BlockItem(BlockInit.STEAM_BOILER.get(), new Item.Properties().tab(ModCreativeTab.instance)));
    public static final RegistryObject<Item> BATTERY =ITEMS.register("battery",()-> new BlockItem(BlockInit.BATTERY.get(), new Item.Properties().tab(ModCreativeTab.instance)));
    public static final RegistryObject<Item> COMBUSTION_CHAMBER =ITEMS.register("combustion_chamber",()-> new BlockItem(BlockInit.COMBUSTION_CHAMBER.get(), new Item.Properties().tab(ModCreativeTab.instance)));
    public static final RegistryObject<Item> GENERATOR =ITEMS.register("generator",()-> new BlockItem(BlockInit.GENERATOR.get(), new Item.Properties().tab(ModCreativeTab.instance)));
    public static final RegistryObject<Item>  BASIC_MACHINE_FRAMEWORK=ITEMS.register("basic_machine_framework",()->new BlockItem(BlockInit.BASIC_MACHINE_FRAMEWORK.get(), new Item.Properties().tab(ModCreativeTab.instance)));
    public static final RegistryObject<CokingCoal> COKING_COAL=ITEMS.register("coking_coal",()->new CokingCoal(new Item.Properties().tab(ModCreativeTab.instance)));
    public static final RegistryObject<Item> COKING_FURNACE=ITEMS.register("coking_furnace",()->new BlockItem(BlockInit.COKING_FURNACE.get(),new Item.Properties().tab(ModCreativeTab.instance) ));
}


