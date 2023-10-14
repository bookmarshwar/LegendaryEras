package folk.lemonbook.item.legendaryeras.init;

import folk.lemonbook.item.legendaryeras.Main;
import folk.lemonbook.item.legendaryeras.entity.CombustionChamberEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES,Main.MOD_ID);
    public  static  final RegistryObject<BlockEntityType<CombustionChamberEntity>> COMBUSTION_CHAMBER_ENTITY=BLOCK_ENTITIES.register("combustion_chamber_entity",()-> BlockEntityType.Builder.of(CombustionChamberEntity::new,BlockInit.COMBUSTION_CHAMBER.get()).build(null));
}
