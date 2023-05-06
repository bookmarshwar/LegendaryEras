package folk.lemonbook.item.legendaryeras.core.init;

import folk.lemonbook.item.legendaryeras.Main;
import folk.lemonbook.item.legendaryeras.common.item.Portrait;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static class ModCreativeTab extends CreativeModeTab {
        public static final ModCreativeTab instance = new ModCreativeTab(CreativeModeTab.TABS.length, Main.MOD_ID);//mod��
        private ModCreativeTab(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Portrait.get());
        }
    }
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);//ע�����
    public static final RegistryObject<Portrait> Portrait =ITEMS.register("portrait",() -> new Portrait(new Item.Properties().tab(ModCreativeTab.instance)));
    public static final RegistryObject<Portrait> Portrait_son =ITEMS.register("portrait_son",() -> new Portrait(new Item.Properties().tab(ModCreativeTab.instance).food(new FoodProperties.Builder()
            .nutrition(-1)//��ʳ��
            .saturationMod(10)//��ɫ��ʳ��
            .effect(() -> new MobEffectInstance(MobEffects.JUMP, 100*20, 2), 1)//�ٷְٸ���һ����Ծ����2+1;
            .build())));//��������build

}



