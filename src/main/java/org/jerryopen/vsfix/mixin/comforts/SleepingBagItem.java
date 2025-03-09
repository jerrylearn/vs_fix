package org.jerryopen.vsfix.mixin.comforts;

import com.illusivesoulworks.comforts.common.ComfortsConfig;
import com.illusivesoulworks.comforts.common.block.BaseComfortsBlock;
import com.illusivesoulworks.comforts.common.item.BaseComfortsItem;
import com.illusivesoulworks.comforts.platform.Services;
import com.mojang.datafixers.util.Either;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Unit;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nonnull;
import java.util.List;

@Mixin(com.illusivesoulworks.comforts.common.item.SleepingBagItem.class)
public abstract class SleepingBagItem extends BaseComfortsItem {
    public SleepingBagItem(Block block) {
        super(block);
    }
    @Shadow public abstract void syncedUseOn(@Nonnull UseOnContext useOnContext);
    @Shadow public abstract void appendHoverText(@Nonnull ItemStack stack, @Nullable Level level, @Nonnull List<Component> components, @Nonnull TooltipFlag flag);
    @Inject(method = "useOn",at=@At( "HEAD" ),remap = false,cancellable = true)
    private void onUseOn(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {cir.cancel();
    }
}
