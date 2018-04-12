package cn.count.jineng;

public class Skill implements Comparable<Skill> {
	// 技能名字
	private String name;
	// 施放技能所需时间
	private double castTimes;
	// 技能伤害
	private double damage;
	// 技能CD
	private double CD;

	// 该技能上一次施放的时间
	private double skillLastTime;
	// 技能是否进入CD状态
	private boolean isCD = false;
	// 技能使用次数
	private int useNumber = 0;

	public Skill(String name, double castTimes, double damage, double CD) {
		super();
		this.name = name;
		this.castTimes = castTimes;
		this.damage = damage;
		this.CD = CD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCastTimes() {
		return castTimes;
	}

	public void setCastTimes(int castTimes) {
		this.castTimes = castTimes;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public double getCD() {
		return CD;
	}

	public void setCD(int cD) {
		
		this.CD = cD;
	}

	public int getPriority() {
		return (int) (damage / castTimes);
	}

	public double getSkillLastTime() {
		return skillLastTime;
	}

	public void setSkillLastTime(double skillLastTime) {
		this.skillLastTime = skillLastTime;
	}

	public boolean getIsCD() {
		
		return isCD;
	}

	public void setIsCD(boolean isCD) {
		/*if(!this.getIsCD()) {
			System.out.println(this.name+"冷却好啦！");
		}*/
		this.isCD = isCD;
	}

	public void addUseNumber() {
		++useNumber;
	}

	public int getUseNumber() {
		return useNumber;
	}

	/**
	 * 重写compareTo方法，使其返回秒伤值的负数，以便进行降序排列
	 */
	@Override
	public int compareTo(Skill skill) {
		return (skill.getPriority() - this.getPriority());
	}

	@Override
	public String toString() {
		return "Skill [name=" + name + ", castTimes=" + castTimes + ", damage=" + damage + ", CD=" + CD + ", priority="
				+ this.getPriority() + ", skillLastTime=" + skillLastTime + ", isCD=" + isCD + ", useNumber="
				+ useNumber + "]";
	}

}
