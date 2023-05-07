package folk.lemonbook.item.legendaryeras.init;

import folk.lemonbook.item.legendaryeras.Main;
import folk.lemonbook.item.legendaryeras.block.KineticEnergyGenerator;
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
    public static final RegistryObject<Item> KINETIC_ENERGY_GENERATOR= ITEMS.register("kinetic_energy_generator",()->new BlockItem(BlockInit.KINETIC_ENERGY_GENERATOR.get(), new Item.Properties().tab(ModCreativeTab.instance)));
    public static final RegistryObject<Item> POTENTIAL_ENERGY_GENERATOR =ITEMS.register("potential_energy_generator",()-> new BlockItem(BlockInit.POTENTIAL_ENERGY_GENERATOR.get(), new Item.Properties().tab(ModCreativeTab.instance)));
    public static final RegistryObject<Item> TURBINE =ITEMS.register("turbine",()-> new BlockItem(BlockInit.TURBINE.get(), new Item.Properties().tab(ModCreativeTab.instance)));
    public static final RegistryObject<Item> GPEG_STRUCTURAL_BODIES =ITEMS.register("gpeg_structuralbodies",()-> new BlockItem(BlockInit.GPEG_STRUCTURAL_BODIES.get(), new Item.Properties().tab(ModCreativeTab.instance)));
    public static final RegistryObject<Item> GPEG_TOP =ITEMS.register("gpeg_top",()-> new BlockItem(BlockInit.GPEG_TOP.get(), new Item.Properties().tab(ModCreativeTab.instance)));
    public static final RegistryObject<Item> GPEG_BASE =ITEMS.register("gpeg_base",()-> new BlockItem(BlockInit.GPEG_BASE.get(), new Item.Properties().tab(ModCreativeTab.instance)));

}



