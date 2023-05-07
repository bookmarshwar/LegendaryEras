package folk.lemonbook.item.legendaryeras.init;

import folk.lemonbook.item.legendaryeras.Main;
import folk.lemonbook.item.legendaryeras.block.KineticEnergyGenerator;
import folk.lemonbook.item.legendaryeras.block.PotentialEnergyGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MOD_ID);
    public static final RegistryObject<KineticEnergyGenerator> KINETIC_ENERGY_GENERATOR = BLOCKS.register("kinetic_energy_generator", KineticEnergyGenerator::new);
    public static final RegistryObject<PotentialEnergyGenerator> POTENTIAL_ENERGY_GENERATOR = BLOCKS.register("potential_energy_generator", PotentialEnergyGenerator::new);
}
