package folk.lemonbook.item.legendaryeras.init;

import folk.lemonbook.item.legendaryeras.Main;
import folk.lemonbook.item.legendaryeras.block.*;
import folk.lemonbook.item.legendaryeras.machine.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MOD_ID);
    public static final RegistryObject<KineticEnergyConverter> KINETIC_ENERGY_CONVERTER = BLOCKS.register("kinetic_energy_converter", KineticEnergyConverter::new);
    public static final RegistryObject<PotentialEnergyConverter> POTENTIAL_ENERGY_CONVERTER = BLOCKS.register("potential_energy_converter", PotentialEnergyConverter::new);
    public static final RegistryObject<Turbine> TURBINE = BLOCKS.register("turbine", Turbine::new);
    public static final RegistryObject<GravitationalpotentialenergygenerationStructuralBodies> GPEG_STRUCTURAL_BODIES = BLOCKS.register("gpeg_structuralbodies", GravitationalpotentialenergygenerationStructuralBodies::new);
    public static final RegistryObject<GravitationalpotentialenergygenerationTop> GPEG_TOP = BLOCKS.register("gpeg_top", GravitationalpotentialenergygenerationTop::new);
    public static final RegistryObject<GravitationalpotentialenergygenerationBase> GPEG_BASE = BLOCKS.register("gpeg_base", GravitationalpotentialenergygenerationBase::new);
    public static final RegistryObject<SteamBoiler> STEAM_BOILER = BLOCKS.register("steam_boiler", SteamBoiler::new);
    public static final RegistryObject<Battery> BATTERY  = BLOCKS.register("battery", Battery::new);
    public static final RegistryObject<CombustionChamber> COMBUSTION_CHAMBER  = BLOCKS.register("combustion_chamber", CombustionChamber::new);
    public static final RegistryObject<Generator> GENERATOR  = BLOCKS.register("generator", Generator::new);
}
