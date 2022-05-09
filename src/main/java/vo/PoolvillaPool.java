package vo;

public class PoolvillaPool {
	private int poolNo;
	private int pvNo;
	private double poolWidth;
	private double poolLength;
	private double depth;
	private String hotWater;
	private String inOut;
	private String updateDate;
	public PoolvillaPool() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PoolvillaPool(int poolNo, int pvNo, double poolWidth, double poolLength, double depth, String hotWater,
			String inOut, String updateDate) {
		super();
		this.poolNo = poolNo;
		this.pvNo = pvNo;
		this.poolWidth = poolWidth;
		this.poolLength = poolLength;
		this.depth = depth;
		this.hotWater = hotWater;
		this.inOut = inOut;
		this.updateDate = updateDate;
	}
	public int getPoolNo() {
		return poolNo;
	}
	public void setPoolNo(int poolNo) {
		this.poolNo = poolNo;
	}
	public int getPvNo() {
		return pvNo;
	}
	public void setPvNo(int pvNo) {
		this.pvNo = pvNo;
	}
	public double getPoolWidth() {
		return poolWidth;
	}
	public void setPoolWidth(double poolWidth) {
		this.poolWidth = poolWidth;
	}
	public double getPoolLength() {
		return poolLength;
	}
	public void setPoolLength(double poolLength) {
		this.poolLength = poolLength;
	}
	public double getDepth() {
		return depth;
	}
	public void setDepth(double depth) {
		this.depth = depth;
	}
	public String getHotWater() {
		return hotWater;
	}
	public void setHotWater(String hotWater) {
		this.hotWater = hotWater;
	}
	public String getInOut() {
		return inOut;
	}
	public void setInOut(String inOut) {
		this.inOut = inOut;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "PoolvillaPool [poolNo=" + poolNo + ", pvNo=" + pvNo + ", poolWidth=" + poolWidth + ", poolLength="
				+ poolLength + ", depth=" + depth + ", hotWater=" + hotWater + ", inOut=" + inOut + ", updateDate="
				+ updateDate + "]";
	}
	
	

}
