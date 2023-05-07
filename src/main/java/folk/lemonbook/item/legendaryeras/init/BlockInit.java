package folk.lemonbook.item.legendaryeras.init;

import folk.lemonbook.item.legendaryeras.Main;
import folk.lemonbook.item.legendaryeras.block.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MOD_ID);
    public static final RegistryObject<KineticEnergyGenerator> KINETIC_ENERGY_GENERATOR = BLOCKS.register("kinetic_energy_generator", KineticEnergyGenerator::new);
    public static final RegistryObject<PotentialEnergyGenerator> POTENTIAL_ENERGY_GENERATOR = BLOCKS.register("potential_energy_generator", PotentialEnergyGenerator::new);
    public static final RegistryObject<Turbine> TURBINE = BLOCKS.register("turbine", Turbine::new);
    public static final RegistryObject<GravitationalpotentialenergygenerationStructuralBodies> GPEG_STRUCTURAL_BODIES = BLOCKS.register("gpeg_structuralbodies", GravitationalpotentialenergygenerationStructuralBodies::new);
    public static final RegistryObject<GravitationalpotentialenergygenerationTop> GPEG_TOP = BLOCKS.register("gpeg_top", GravitationalpotentialenergygenerationTop::new);
    public static final RegistryObject<GravitationalpotentialenergygenerationBase> GPEG_BASE = BLOCKS.register("gpeg_base", GravitationalpotentialenergygenerationBase::new);

}
