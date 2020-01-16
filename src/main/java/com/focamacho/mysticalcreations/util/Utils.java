package com.focamacho.mysticalcreations.util;

import net.minecraft.util.text.TextFormatting;

public class Utils {

	public static TextFormatting getTierColor(Integer tier) {
		switch(tier - 1){
	    case 0:
	    	return TextFormatting.YELLOW;
	    case 1:
	    	return TextFormatting.GREEN;
	    case 2:
	    	return TextFormatting.GOLD;
	    case 3:
	    	return TextFormatting.AQUA;
	    case 4:
	    	return TextFormatting.RED;
	    case 5:
	    	return TextFormatting.DARK_PURPLE;
	    }
		return TextFormatting.GRAY;
	}
}
