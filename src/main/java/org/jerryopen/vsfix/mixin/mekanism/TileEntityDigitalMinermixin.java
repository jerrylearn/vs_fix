package org.jerryopen.vsfix.mixin.mekanism;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMaps;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import mekanism.api.inventory.IInventorySlot;
import mekanism.common.CommonWorldTickHandler;
import mekanism.common.capabilities.energy.MinerEnergyContainer;
import mekanism.common.content.filter.SortableFilterManager;
import mekanism.common.content.miner.MinerFilter;
import mekanism.common.content.miner.ThreadMinerSearch;
import mekanism.common.lib.chunkloading.IChunkLoader;
import mekanism.common.lib.inventory.HashedItem;
import mekanism.common.registries.MekanismBlocks;
import mekanism.common.tags.MekanismTags;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.tile.component.TileComponentChunkLoader;
import mekanism.common.tile.interfaces.IBoundingBlock;
import mekanism.common.tile.interfaces.IHasVisualization;
import mekanism.common.tile.interfaces.ISustainedData;
import mekanism.common.tile.interfaces.ITileFilterHolder;
import mekanism.common.tile.machine.TileEntityDigitalMiner;
import mekanism.common.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.PathNavigationRegion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jerryopen.vsfix.init.conversion;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.BitSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static mekanism.common.tile.machine.TileEntityDigitalMiner.*;

@Mixin(TileEntityDigitalMiner.class)
public abstract class TileEntityDigitalMinermixin extends TileEntityMekanism implements ISustainedData, IChunkLoader, IBoundingBlock, ITileFilterHolder<MinerFilter<?>>, IHasVisualization {
    // ┏┓　　　┏┓
// ┏┛┻━━━┛┻┓
// ┃　　　　　　　┃ 　
// ┃　　　━　　　┃
// ┃　┳┛　┗┳　┃
// ┃　　　　　　　┃
// ┃　　　┻　　　┃
// ┃　　　　　　　┃
// ┗━┓　　　┏━┛
// ┃　　　┃ 神兽保佑　　　　　　　　
// ┃　　　┃ 代码无BUG！
// ┃　　　┗━━━┓
// ┃　　　　　　　┣┓
// ┃　　　　　　　┏┛
// ┗┓┓┏━┳┓┏┛
// ┃┫┫　┃┫┫
// ┗┻┛　┗┻┛


    @Shadow public abstract int getMinY();
    @Shadow public int radius;
    protected TileEntityDigitalMinermixin(BlockPos pos, BlockState state) {
        super(MekanismBlocks.DIGITAL_MINER, pos, state);

    }@Inject(method = "getStartingPos",at=@At( "HEAD" ),remap = false,cancellable = true)
    private void getStaringPos(CallbackInfoReturnable<BlockPos> cir) {
        cir.setReturnValue(new BlockPos(conversion.blockposconvertgetShipToWorld(level,getBlockPos()).getX() - radius, getMinY(), conversion.blockposconvertgetShipToWorld(level,getBlockPos()).getZ() - radius));
    }

}
