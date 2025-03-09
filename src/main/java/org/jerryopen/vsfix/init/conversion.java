package org.jerryopen.vsfix.init;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.joml.Vector3d;
import org.valkyrienskies.mod.common.VSGameUtilsKt;

public class conversion {
    public static Vector3d blockpostov3d(BlockPos pos) {
        return new Vector3d(pos.getX(), pos.getY(), pos.getZ());
    }
    public static BlockPos blockposconvertgetShipToWorld(Level world, BlockPos pos) {
        Vector3d v3dpos = blockpostov3d(pos);
        if (VSGameUtilsKt.getShipObjectManagingPos(world, pos) != null) {
             Vector3d phypos = VSGameUtilsKt.getShipObjectManagingPos((Level) world, v3dpos).getTransform().getShipToWorld().transformPosition(v3dpos);
             pos = new BlockPos((int) phypos.x, (int) phypos.y, (int) phypos.z);
        }
        return pos;
    }
}
