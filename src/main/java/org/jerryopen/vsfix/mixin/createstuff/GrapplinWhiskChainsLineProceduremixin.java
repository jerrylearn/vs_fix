package org.jerryopen.vsfix.mixin.createstuff;

import com.mojang.blaze3d.systems.RenderSystem;
import net.mcreator.createstuffadditions.init.CreateSaModEnchantments;
import net.mcreator.createstuffadditions.init.CreateSaModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.eventbus.api.Event;
import org.joml.Vector3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.valkyrienskies.mod.common.VSGameUtilsKt;

//import static net.mcreator.createstuffadditions.procedures.GrapplinWhiskChainsLineProcedure.*;

import javax.annotation.Nullable;

import static net.mcreator.createstuffadditions.procedures.GrapplinWhiskChainsLineProcedure.renderItem;

@Mixin(net.mcreator.createstuffadditions.procedures.GrapplinWhiskChainsLineProcedure.class)
public abstract class GrapplinWhiskChainsLineProceduremixin {
    @Inject(method = "execute(Lnet/minecraftforge/eventbus/api/Event;Lnet/minecraft/world/level/LevelAccessor;D)V",at=@At( "HEAD" ),remap = false,cancellable = true)
    private static void executemixin(Event event, LevelAccessor world, double partialTick, CallbackInfo ci){
        /*double nb = (double)0.0F;
        double lcl_distance = (double)0.0F;
        double dx = (double)0.0F;
        double dy = (double)0.0F;
        double dz = (double)0.0F;
        double playerPosX = (double)0.0F;
        double playerPosY = (double)0.0F;
        double playerPosZ = (double)0.0F;
        ItemStack lcl_chains = ItemStack.EMPTY;
        ItemStack back_sword = ItemStack.EMPTY;
        if (world instanceof ClientLevel) {
            for(Entity entityiterator : ((ClientLevel)world).entitiesForRendering()) {
                ItemStack var10000;
                if (entityiterator instanceof LivingEntity) {
                    LivingEntity _livEnt = (LivingEntity)entityiterator;
                    var10000 = _livEnt.getMainHandItem();
                } else {
                    var10000 = ItemStack.EMPTY;
                }
                //if (physhiptempvector3d(world,var10000)) {break;}

                label232: {
                    if (var10000.getItem() != CreateSaModItems.GRAPPLIN_WHISK.get()) {
                        if (entityiterator instanceof LivingEntity) {
                            LivingEntity _livEnt = (LivingEntity)entityiterator;
                            var10000 = _livEnt.getOffhandItem();
                        } else {
                            var10000 = ItemStack.EMPTY;
                        }

                        if (var10000.getItem() != CreateSaModItems.GRAPPLIN_WHISK.get()) {
                            break label232;
                        }
                    }

                    playerPosX = entityiterator.getPosition((float)partialTick).x() + entityiterator.getViewVector((float)partialTick).x() * (double)1.25F;
                    playerPosY = (double)entityiterator.getBbHeight() * (double)0.75F + entityiterator.getPosition((float)partialTick).y() + entityiterator.getViewVector((float)partialTick).y() * (double)1.25F;
                    playerPosZ = entityiterator.getPosition((float)partialTick).z() + entityiterator.getViewVector((float)partialTick).z() * (double)1.25F;
                }

                if (entityiterator instanceof LivingEntity) {
                    LivingEntity _livEnt = (LivingEntity)entityiterator;
                    var10000 = _livEnt.getMainHandItem();
                } else {
                    var10000 = ItemStack.EMPTY;
                }

                if (var10000.getItem() == CreateSaModItems.GRAPPLIN_WHISK.get()) {
                    if (entityiterator instanceof LivingEntity) {
                        LivingEntity _livEnt = (LivingEntity)entityiterator;
                        var10000 = _livEnt.getMainHandItem();
                    } else {
                        var10000 = ItemStack.EMPTY;
                    }

                    if (var10000.copy().getOrCreateTag().getBoolean("tagHooked")) {
                        ItemStack var10001;
                        if (entityiterator instanceof LivingEntity) {
                            LivingEntity _livEnt = (LivingEntity)entityiterator;
                            var10001 = _livEnt.getMainHandItem();
                        } else {
                            var10001 = ItemStack.EMPTY;
                        }

                        double var91 = var10001.getOrCreateTag().getDouble("xPostion");
                        ItemStack var10002;
                        if (entityiterator instanceof LivingEntity) {
                            LivingEntity _livEnt = (LivingEntity)entityiterator;
                            var10002 = _livEnt.getMainHandItem();
                        } else {
                            var10002 = ItemStack.EMPTY;
                        }

                        double var98 = var10002.getOrCreateTag().getDouble("yPostion");
                        ItemStack var10003;
                        if (entityiterator instanceof LivingEntity) {
                            LivingEntity _livEnt = (LivingEntity)entityiterator;
                            var10003 = _livEnt.getMainHandItem();
                        } else {
                            var10003 = ItemStack.EMPTY;
                        }

                        if (!world.isEmptyBlock(BlockPos.containing(var91, var98, var10003.getOrCreateTag().getDouble("zPostion")))) {
                            if (entityiterator instanceof LivingEntity) {
                                LivingEntity _livEnt = (LivingEntity)entityiterator;
                                var10000 = _livEnt.getMainHandItem();
                            } else {
                                var10000 = ItemStack.EMPTY;
                            }

                            if (var10000.getOrCreateTag().getDouble("tagFuel") > (double)0.0F) {
                                ItemStack var96;
                                if (entityiterator instanceof LivingEntity) {
                                    LivingEntity _livEnt = (LivingEntity)entityiterator;
                                    var96 = _livEnt.getMainHandItem();
                                } else {
                                    var96 = ItemStack.EMPTY;
                                }

                                double var85 = Math.pow(playerPosX - (var96.getOrCreateTag().getDouble("xPostion") + (double)0.5F), (double)2.0F);
                                ItemStack var104;
                                if (entityiterator instanceof LivingEntity) {
                                    LivingEntity _livEnt = (LivingEntity)entityiterator;
                                    var104 = _livEnt.getMainHandItem();
                                } else {
                                    var104 = ItemStack.EMPTY;
                                }

                                var85 += Math.pow(playerPosY - (var104.getOrCreateTag().getDouble("yPostion") + (double)0.5F), (double)2.0F);
                                if (entityiterator instanceof LivingEntity) {
                                    LivingEntity _livEnt = (LivingEntity)entityiterator;
                                    var104 = _livEnt.getMainHandItem();
                                } else {
                                    var104 = ItemStack.EMPTY;
                                }

                                lcl_distance = Math.sqrt(var85 + Math.pow(playerPosZ - (var104.getOrCreateTag().getDouble("zPostion") + (double)0.5F), (double)2.0F));
                                ItemStack var87;
                                if (entityiterator instanceof LivingEntity) {
                                    LivingEntity _livEnt = (LivingEntity)entityiterator;
                                    var87 = _livEnt.getMainHandItem();
                                } else {
                                    var87 = ItemStack.EMPTY;
                                }

                                dx = var87.getOrCreateTag().getDouble("xPostion") + (double)0.5F - playerPosX;
                                if (entityiterator instanceof LivingEntity) {
                                    LivingEntity _livEnt = (LivingEntity)entityiterator;
                                    var87 = _livEnt.getMainHandItem();
                                } else {
                                    var87 = ItemStack.EMPTY;
                                }

                                dy = var87.getOrCreateTag().getDouble("yPostion") + (double)0.5F - playerPosY;
                                if (entityiterator instanceof LivingEntity) {
                                    LivingEntity _livEnt = (LivingEntity)entityiterator;
                                    var87 = _livEnt.getMainHandItem();
                                } else {
                                    var87 = ItemStack.EMPTY;
                                }

                                dz = var87.getOrCreateTag().getDouble("zPostion") + (double)0.5F - playerPosZ;
                                nb = (double)0.0F;

                                for(int index0 = 0; index0 < (int)(Math.ceil(lcl_distance) * (double)2.0F); ++index0) {
                                    lcl_chains = new ItemStack((ItemLike)CreateSaModItems.CHAINS.get());
                                    if (entityiterator instanceof LivingEntity) {
                                        LivingEntity _livEnt = (LivingEntity)entityiterator;
                                        var87 = _livEnt.getMainHandItem();
                                    } else {
                                        var87 = ItemStack.EMPTY;
                                    }

                                    if (var87.isEnchanted()) {
                                        lcl_chains.enchant((Enchantment)CreateSaModEnchantments.IMPACT.get(), 1);
                                    }

                                    if (entityiterator instanceof LivingEntity) {
                                        LivingEntity _livEnt = (LivingEntity)entityiterator;
                                        var10003 = _livEnt.getMainHandItem();
                                    } else {
                                        var10003 = ItemStack.EMPTY;
                                    }

                                    double var97 = playerPosX + (playerPosX - (var10003.getOrCreateTag().getDouble("xPostion") + (double)0.5F)) / (lcl_distance == (double)0.0F ? 0.01 : lcl_distance) * nb * (double)-1.0F;
                                    ItemStack var110;
                                    if (entityiterator instanceof LivingEntity) {
                                        LivingEntity _livEnt = (LivingEntity)entityiterator;
                                        var110 = _livEnt.getMainHandItem();
                                    } else {
                                        var110 = ItemStack.EMPTY;
                                    }

                                    double var106 = playerPosY + (playerPosY - (var110.getOrCreateTag().getDouble("yPostion") + (double)0.5F)) / (lcl_distance == (double)0.0F ? 0.01 : lcl_distance) * nb * (double)-1.0F;
                                    ItemStack var111;
                                    if (entityiterator instanceof LivingEntity) {
                                        LivingEntity _livEnt = (LivingEntity)entityiterator;
                                        var111 = _livEnt.getMainHandItem();
                                    } else {
                                        var111 = ItemStack.EMPTY;
                                    }

                                    renderItem(lcl_chains, var97, var106, playerPosZ + (playerPosZ - (var111.getOrCreateTag().getDouble("zPostion") + (double)0.5F)) / (lcl_distance == (double)0.0F ? 0.01 : lcl_distance) * nb * (double)-1.0F, (float)(Math.atan2(dx, dz) * -57.29577951308232 + (double)180.0F), (float)(Math.atan2(dy, Math.abs(dx) + Math.abs(dz)) * (180D / Math.PI)), 0.0F, 1.0F, false, false);
                                    nb += (double)0.5F;
                                }
                                continue;
                            }
                        }
                    }
                }

                if (entityiterator instanceof LivingEntity) {
                    LivingEntity _livEnt = (LivingEntity)entityiterator;
                    var10000 = _livEnt.getOffhandItem();
                } else {
                    var10000 = ItemStack.EMPTY;
                }

                if (var10000.getItem() == CreateSaModItems.GRAPPLIN_WHISK.get()) {
                    if (entityiterator instanceof LivingEntity) {
                        LivingEntity _livEnt = (LivingEntity)entityiterator;
                        var10000 = _livEnt.getOffhandItem();
                    } else {
                        var10000 = ItemStack.EMPTY;
                    }

                    if (var10000.copy().getOrCreateTag().getBoolean("tagHooked")) {
                        ItemStack var92;
                        if (entityiterator instanceof LivingEntity) {
                            LivingEntity _livEnt = (LivingEntity)entityiterator;
                            var92 = _livEnt.getOffhandItem();
                        } else {
                            var92 = ItemStack.EMPTY;
                        }

                        double var93 = var92.getOrCreateTag().getDouble("xPostion");
                        ItemStack var99;
                        if (entityiterator instanceof LivingEntity) {
                            LivingEntity _livEnt = (LivingEntity)entityiterator;
                            var99 = _livEnt.getOffhandItem();
                        } else {
                            var99 = ItemStack.EMPTY;
                        }

                        double var100 = var99.getOrCreateTag().getDouble("yPostion");
                        ItemStack var107;
                        if (entityiterator instanceof LivingEntity) {
                            LivingEntity _livEnt = (LivingEntity)entityiterator;
                            var107 = _livEnt.getOffhandItem();
                        } else {
                            var107 = ItemStack.EMPTY;
                        }

                        if (!world.isEmptyBlock(BlockPos.containing(var93, var100, var107.getOrCreateTag().getDouble("zPostion")))) {
                            if (entityiterator instanceof LivingEntity) {
                                LivingEntity _livEnt = (LivingEntity)entityiterator;
                                var10000 = _livEnt.getOffhandItem();
                            } else {
                                var10000 = ItemStack.EMPTY;
                            }

                            if (var10000.getOrCreateTag().getDouble("tagFuel") > (double)0.0F) {
                                ItemStack var94;
                                if (entityiterator instanceof LivingEntity) {
                                    LivingEntity _livEnt = (LivingEntity)entityiterator;
                                    var94 = _livEnt.getOffhandItem();
                                } else {
                                    var94 = ItemStack.EMPTY;
                                }

                                double var79 = Math.pow(playerPosX - (var94.getOrCreateTag().getDouble("xPostion") + (double)0.5F), (double)2.0F);
                                ItemStack var101;
                                if (entityiterator instanceof LivingEntity) {
                                    LivingEntity _livEnt = (LivingEntity)entityiterator;
                                    var101 = _livEnt.getOffhandItem();
                                } else {
                                    var101 = ItemStack.EMPTY;
                                }

                                var79 += Math.pow(playerPosY - (var101.getOrCreateTag().getDouble("yPostion") + (double)0.5F), (double)2.0F);
                                if (entityiterator instanceof LivingEntity) {
                                    LivingEntity _livEnt = (LivingEntity)entityiterator;
                                    var101 = _livEnt.getOffhandItem();
                                } else {
                                    var101 = ItemStack.EMPTY;
                                }

                                lcl_distance = Math.sqrt(var79 + Math.pow(playerPosZ - (var101.getOrCreateTag().getDouble("zPostion") + (double)0.5F), (double)2.0F));
                                ItemStack var81;
                                if (entityiterator instanceof LivingEntity) {
                                    LivingEntity _livEnt = (LivingEntity)entityiterator;
                                    var81 = _livEnt.getOffhandItem();
                                } else {
                                    var81 = ItemStack.EMPTY;
                                }

                                dx = var81.getOrCreateTag().getDouble("xPostion") + (double)0.5F - playerPosX;
                                if (entityiterator instanceof LivingEntity) {
                                    LivingEntity _livEnt = (LivingEntity)entityiterator;
                                    var81 = _livEnt.getOffhandItem();
                                } else {
                                    var81 = ItemStack.EMPTY;
                                }

                                dy = var81.getOrCreateTag().getDouble("yPostion") + (double)0.5F - playerPosY;
                                if (entityiterator instanceof LivingEntity) {
                                    LivingEntity _livEnt = (LivingEntity)entityiterator;
                                    var81 = _livEnt.getOffhandItem();
                                } else {
                                    var81 = ItemStack.EMPTY;
                                }

                                dz = var81.getOrCreateTag().getDouble("zPostion") + (double)0.5F - playerPosZ;
                                nb = (double)0.0F;

                                for(int index1 = 0; index1 < (int)(Math.ceil(lcl_distance) * (double)2.0F); ++index1) {
                                    lcl_chains = new ItemStack((ItemLike)CreateSaModItems.CHAINS.get());
                                    if (entityiterator instanceof LivingEntity) {
                                        LivingEntity _livEnt = (LivingEntity)entityiterator;
                                        var81 = _livEnt.getOffhandItem();
                                    } else {
                                        var81 = ItemStack.EMPTY;
                                    }

                                    if (var81.isEnchanted()) {
                                        lcl_chains.enchant((Enchantment)CreateSaModEnchantments.IMPACT.get(), 1);
                                    }

                                    if (entityiterator instanceof LivingEntity) {
                                        LivingEntity _livEnt = (LivingEntity)entityiterator;
                                        var107 = _livEnt.getOffhandItem();
                                    } else {
                                        var107 = ItemStack.EMPTY;
                                    }

                                    double var95 = playerPosX + (playerPosX - (var107.getOrCreateTag().getDouble("xPostion") + (double)0.5F)) / (lcl_distance == (double)0.0F ? 0.01 : lcl_distance) * nb * (double)-1.0F;
                                    ItemStack var10004;
                                    if (entityiterator instanceof LivingEntity) {
                                        LivingEntity _livEnt = (LivingEntity)entityiterator;
                                        var10004 = _livEnt.getOffhandItem();
                                    } else {
                                        var10004 = ItemStack.EMPTY;
                                    }

                                    double var103 = playerPosY + (playerPosY - (var10004.getOrCreateTag().getDouble("yPostion") + (double)0.5F)) / (lcl_distance == (double)0.0F ? 0.01 : lcl_distance) * nb * (double)-1.0F;
                                    ItemStack var10005;
                                    if (entityiterator instanceof LivingEntity) {
                                        LivingEntity _livEnt = (LivingEntity)entityiterator;
                                        var10005 = _livEnt.getOffhandItem();
                                    } else {
                                        var10005 = ItemStack.EMPTY;
                                    }

                                    renderItem(lcl_chains, var95, var103, playerPosZ + (playerPosZ - (var10005.getOrCreateTag().getDouble("zPostion") + (double)0.5F)) / (lcl_distance == (double)0.0F ? 0.01 : lcl_distance) * nb * (double)-1.0F, (float)(Math.atan2(dx, dz) * -57.29577951308232 + (double)180.0F), (float)(Math.atan2(dy, Math.abs(dx) + Math.abs(dz)) * (180D / Math.PI)), 0.0F, 1.0F, false, false);
                                    nb += (double)0.5F;
                                }
                            }
                        }
                    }
                }
            }
        }*/
        ci.cancel();
    }

    private static boolean physhiptempvector3d(LevelAccessor world, ItemStack stack) {
        Vector3d phyvector = new Vector3d(stack.getOrCreateTag().getDouble("xPostion"),stack.getOrCreateTag().getDouble("yPostion"),stack.getOrCreateTag().getDouble("zPostion"));
        boolean b = false;
        if (VSGameUtilsKt.getShipObjectManagingPos((Level) world, phyvector) != null) {
            b = true;
        }
        return b;
    }
}
