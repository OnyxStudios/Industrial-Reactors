package dev.onyxstudios.industrialreactors.init;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import io.github.cottonmc.component.item.InventoryComponent;
import nerdhub.cardinal.components.api.ComponentRegistry;
import net.minecraft.util.Identifier;

public class ReactorComponents {

    public static final ComponentKey<InventoryComponent> INVENTORY_COMPONENT = ComponentRegistry.INSTANCE.registerStatic(new Identifier("universalcomponents", "inventory"), InventoryComponent.class);

}
