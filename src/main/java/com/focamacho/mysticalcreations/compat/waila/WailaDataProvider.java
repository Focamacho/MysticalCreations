package com.focamacho.mysticalcreations.compat.waila;

import com.blakebr0.mysticalagriculture.lib.Tooltips;
import com.focamacho.mysticalcreations.blocks.BlockCrop;
import com.focamacho.mysticalcreations.util.Utils;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import java.util.List;

public class WailaDataProvider implements IWailaDataProvider {

	public static void callbackRegister(IWailaRegistrar registrar) {
		registrar.registerBodyProvider(new WailaDataProvider(), BlockCrop.class);
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		Block block = accessor.getBlock();
		if (block instanceof BlockCrop) {
			int tier = ((BlockCrop)block).getTier();
			currenttip.add(Tooltips.TIER.substring(0, 4) + " : " + Utils.getTierColor(tier) + tier);
		}
		
		return currenttip;
	}
}