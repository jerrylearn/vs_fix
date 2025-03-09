package org.jerryopen.vsfix.mixin.clockwork;


import net.minecraft.util.Mth;
import org.joml.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import org.valkyrienskies.clockwork.content.forces.PropellerController;

import java.lang.Math;


@Mixin(PropellerController.class)
public class proprllercontrollermixin {
    @Inject(method = "airPressure",at=@At( "HEAD" ),remap = false,cancellable = true)

    private final void airPressure(Vector3dc pos, CallbackInfoReturnable<Double> cir) {
        double offset = Math.exp(-1.3333333333333333D);
             double height = -64;//pos.y()/10;
             double airPress = (Math.exp(-(height - 64.0D) / 'À') - offset) / (1.0D - offset);
             cir.setReturnValue(Double.isFinite(airPress) ? airPress : 0.0D);

    }
}