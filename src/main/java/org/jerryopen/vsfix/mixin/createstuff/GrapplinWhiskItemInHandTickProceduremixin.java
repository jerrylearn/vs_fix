package org.jerryopen.vsfix.mixin.createstuff;

import net.mcreator.createstuffadditions.CreateSaMod;
import net.mcreator.createstuffadditions.init.CreateSaModEnchantments;
import net.mcreator.createstuffadditions.procedures.GrapplinWhiskItemInHandTickProcedure;
import net.mcreator.createstuffadditions.procedures.GrapplinWhiskRightclickedProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;
import org.joml.Vector3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.valkyrienskies.core.api.ships.ServerShip;
import org.valkyrienskies.mod.common.VSGameUtilsKt;

import java.text.NumberFormat;
import java.util.Comparator;
import java.util.Vector;

@Mixin(GrapplinWhiskItemInHandTickProcedure.class)
public class GrapplinWhiskItemInHandTickProceduremixin {

    @Inject(method = "execute",at=@At( "HEAD" ),remap = false,cancellable = true)
    private static void GrapplinWhiskItemInHandTickProceduremixin(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack, CallbackInfo ci) {
        if (entity != null) {
            double distance = (double)0.0F;

            if (itemstack.getOrCreateTag().getDouble("tagFuel") > (double)0.0F) {
                if (itemstack.getOrCreateTag().getBoolean("tagHooked")) {
                    Vector3d itemstackget = new Vector3d(itemstack.getOrCreateTag().getDouble("xPostion"),itemstack.getOrCreateTag().getDouble("yPostion"),itemstack.getOrCreateTag().getDouble("zPostion"));
                    if (!world.isEmptyBlock(BlockPos.containing(itemstackget.x, itemstackget.y, itemstackget.z))) {
                        //ServerShip servership = (ServerShip) VSGameUtilsKt.getShipObjectManagingPos((Level) world, itemstackget);
                        if (VSGameUtilsKt.getShipObjectManagingPos((Level) world, itemstackget) != null) {
                            itemstackget = VSGameUtilsKt.getShipObjectManagingPos((Level) world, itemstackget).getTransform().getShipToWorld().transformPosition(itemstackget);
                        }
                        distance = Math.sqrt((itemstackget.x - entity.getX()) * (itemstackget.x - entity.getX()) + (itemstackget.y - entity.getY()) * (itemstackget.y - entity.getY()) + (itemstackget.z - entity.getZ()) * (itemstackget.z - entity.getZ()));
                        entity.setDeltaMovement(new Vec3(entity.getDeltaMovement().x() + (itemstackget.x + (double)0.5F - entity.getX()) / distance * 0.2, entity.getDeltaMovement().y() + (itemstackget.y + (double)0.5F - entity.getY()) / distance * 0.2, entity.getDeltaMovement().z() + (itemstackget.z + (double)0.5F - entity.getZ()) / distance * 0.2));
                        if (entity.getDeltaMovement().y() >= -0.2) {
                            entity.fallDistance = 0.0F;
                        }

                        if (!world.isClientSide() && world instanceof Level) {
                            Level _level = (Level)world;
                            if (!_level.isClientSide()) {
                                _level.playSound((Player)null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_chain")), SoundSource.NEUTRAL, 0.25F, 0.5F);
                            } else {
                                _level.playLocalSound(entity.getX(), entity.getY(), entity.getZ(), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_chain")), SoundSource.NEUTRAL, 0.25F, 0.5F, false);
                            }
                        }

                        if (itemstack.getOrCreateTag().getDouble("tagCooldown") >= (double)5.0F) {
                            itemstack.getOrCreateTag().putDouble("tagFuel", itemstack.getOrCreateTag().getDouble("tagFuel") - (double)1.0F);
                            itemstack.getOrCreateTag().putDouble("tagCooldown", (double)0.0F);
                        } else {
                            itemstack.getOrCreateTag().putDouble("tagCooldown", itemstack.getOrCreateTag().getDouble("tagCooldown") + (double)1.0F);
                        }
                    } else {
                        itemstack.getOrCreateTag().putBoolean("tagHooked", false);
                    }

                    CreateSaMod.queueServerWork(2, () -> {
                        ItemStack var10000;
                        if (entity instanceof LivingEntity _livEnt) {
                            var10000 = _livEnt.getMainHandItem();
                        } else {
                            var10000 = ItemStack.EMPTY;
                        }

                        if (var10000.getItem() == itemstack.getItem()) {
                            if (entity instanceof LivingEntity) {
                                LivingEntity _livEnt = (LivingEntity)entity;
                                var10000 = _livEnt.getOffhandItem();
                            } else {
                                var10000 = ItemStack.EMPTY;
                            }

                            if (var10000.getItem() != itemstack.getItem()) {
                                return;
                            }
                        }

                        itemstack.getOrCreateTag().putBoolean("tagHooked", false);
                    });
                }
            } else {
                itemstack.getOrCreateTag().putBoolean("tagHooked", false);
            }

        }
        ci.cancel();
    }
}
