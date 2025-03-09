package org.jerryopen.vsfix.mixin.create;

import com.simibubi.create.content.contraptions.bearing.SailBlock;
import com.simibubi.create.foundation.block.WrenchableDirectionalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Inject;
import org.valkyrienskies.core.api.ships.ServerShip;
import org.valkyrienskies.core.api.ships.Wing;
import org.valkyrienskies.core.api.ships.WingManager;
import org.valkyrienskies.mod.common.block.WingBlock;
import org.valkyrienskies.mod.common.util.VectorConversionsMCKt;

@Mixin(SailBlock.class)
public abstract class sail extends WrenchableDirectionalBlock implements WingBlock {
    public sail(Properties properties) {
        super(properties);
    }
/*
    //@Shadow public abstract BlockState getStateForPlacement(BlockPlaceContext context);
    @Unique
    @Override
    public Wing getWing(Level level, BlockPos pos, BlockState blockState) {
        double wingPower = 150.0; // 翼面推力
        double wingDrag = 30.0; // 翼面阻力
        Double wingBreakingForce = null; // 翼面断裂力（未设置）
        double wingCamberAttackAngleBias = 0.0; // 翼面攻角偏置
        return new Wing(VectorConversionsMCKt.toJOMLD(blockState.getValue(FACING).getNormal()),
                wingPower, wingDrag, wingBreakingForce, wingCamberAttackAngleBias);
    }*/
}
