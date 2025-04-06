package de.chennai.guvi.utilities;

import java.util.HashMap;
import java.util.Map;

public class ScreenUtil {

	private static ScreenUtil screenUtil = new ScreenUtil();
	
	private static int gridX = 5, gridY = 5;
	
	private ScreenUtil() {

	}
	
	public static ScreenUtil getInstatce() {
		if (screenUtil == null) {
			screenUtil = new ScreenUtil();
		}

		return screenUtil;
	}
	
	public Map<String, Object> getCoordinates(int gridHeight, int gridWdith, int blockX, int blockY) {
		Map<String, Object> dimMap = new HashMap<String, Object>();
		
		int x = (gridWdith / gridX)* blockX;
		int y = (gridHeight / gridY) * blockY;
		
		dimMap.put("x", x);
		dimMap.put("y", y);
		
		return dimMap;
	}
	
	public void setgridX(int x, int y) {
		gridX = x;
		gridY = y;
	}
}
