package org.jerryopen.vsfix.mixin.comforts;

import com.illusivesoulworks.comforts.common.ComfortsConfig;
import com.mojang.datafixers.util.Either;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Unit;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jerryopen.vsfix.init.conversion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(com.illusivesoulworks.comforts.common.block.BaseComfortsBlock.class)
public class BaseComfortsBlock {
    @Inject(method = "use",at=@At( "HEAD" ),remap = false,cancellable = true)
    private void use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit, CallbackInfoReturnable<InteractionResult> cir){
        pos = new BlockPos(conversion.blockposconvertgetShipToWorld(level, pos));
    }
    @Inject(method = "trySleep",at=@At( "HEAD" ),remap = false,cancellable = true)
    private static void trysleep(ServerPlayer player, BlockPos at, boolean dryRun, CallbackInfoReturnable<Either<Player.BedSleepingProblem, Unit>> cir){
        at = new BlockPos(conversion.blockposconvertgetShipToWorld(player.level(), at));
    }
}
