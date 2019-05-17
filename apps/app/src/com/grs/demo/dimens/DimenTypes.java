package com.grs.demo.dimens;


public enum DimenTypes {

	//适配Android 3.2以上   大部分手机的sw值集中在  300-460之间
	// values-sw300,
	DP_sw__240(240),
	DP_sw__300(300),
	DP_sw__320(320),
	DP_sw__340(340),
	DP_sw__360(360),
	DP_sw__375(375),
	DP_sw__380(380),
	DP_sw__411(411),
	DP_sw__420(420),
	DP_sw__445(445),
	DP_sw__450(450),
	DP_sw__480(480),
	DP_sw__500(500),
	DP_sw__520(520),
	DP_sw__560(560);
	// 想生成多少自己以此类推


	/**
	 * 屏幕最小宽度
	 */
	private int swWidthDp;


	DimenTypes(int swWidthDp) {

		this.swWidthDp = swWidthDp;
	}

	public int getSwWidthDp() {
		return swWidthDp;
	}

	public void setSwWidthDp(int swWidthDp) {
		this.swWidthDp = swWidthDp;
	}

}
