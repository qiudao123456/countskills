package cn.count.jineng;

import java.text.DecimalFormat;
import java.util.Arrays;

public class CountSkillDamages {
	// 消耗的总时间
	double timeCosuming = 0;
	// 所有技能所造成的伤害量
	double totalDamages = 0;

	/*
	 * 施放技能
	 */
	public double count(Skill[] skills, int times) {

		while (timeCosuming < times) {
			// 选取技能
			Skill s = choiceSkill(skills);
			// 施放技能
			castSkill(s);

			// 判断是否能刷新CD
			setIsCD(skills);

		}
		return totalDamages;
	}

	/*
	 * 施放一个技能
	 */
	public void castSkill(Skill skill) {

		// 技能进入CD状态
		skill.setIsCD(true);
		// 技能使用次数+1
		skill.addUseNumber();
		// 设置该技能上次施放时间
		skill.setSkillLastTime(timeCosuming);
		// 施放技能后的时间
		double castTimes = timeCosuming;
		this.timeCosuming = castTimes + skill.getCastTimes();
		DecimalFormat df = new DecimalFormat("0.00");
		System.out.println(skill.getName() + "在" + df.format(skill.getSkillLastTime()) + "施放了一次");
		// 加上该次技能伤害
		totalDamages += skill.getDamage();
	}

	/*
	 * 对数组以秒伤进行排序
	 */
	public void arraySkills(Skill[] skills) {
		Arrays.sort(skills);

	}

	/*
	 * 从一个技能数组中筛选出未进入CD 且秒伤最高的技能
	 */
	public Skill choiceSkill(Skill[] skills) {

		Skill s = null;
		// 循环数组，直至遇到未进入CD的秒伤最高的技能
		for (int i = 0; i < skills.length; i++) {
			if (skills[i].getIsCD() == false) {
				s = skills[i];
				break;
			}
		}
		return s;
	}

	/*
	 * 判断一组技能中是否有技能符合重置CD标准，符合则刷新CD 不符合则不变
	 */
	public void setIsCD(Skill[] skills) {
		int i = 0;
		for (int j = 0; j < skills.length; j++) {
			// 判断当前时间-上次施放技能的时间是否大于等于CD 是则重置CD
			if ((timeCosuming - skills[j].getSkillLastTime()) >= skills[j].getCD()) {
				skills[j].setIsCD(false);
			}

			if (!skills[j].getIsCD()) {
				i++;
			}
		}
		if (i == 0) {
			setAllCD(skills);
		}
	}

	/*
	 * 假设技能全部进入CD，没有技能符合重置条件， 则重置剩余CD最短的技能，并使其他技能施放时间上移这个最短CD
	 */
	public void setAllCD(Skill[] skills) {
		// 剩余冷却时间最短的技能序号
		int a = 0;
		// 技能冷却时间
		double c = 0;
		// 找出剩余冷却时间最短的技能序号，以及剩余冷却时间
		for (int i = 0; i < skills.length; i++) {
			// 当前技能的剩余冷却CD
			double d = (skills[i].getCD() - (timeCosuming - skills[i].getSkillLastTime()));
			// 循环得出剩余冷却 CD最短的技能
			if (i < skills.length - 1
					&& d > (skills[i + 1].getCD() - (timeCosuming - skills[i + 1].getSkillLastTime()))) {
				// 如果skills[i+1]的技能剩余冷却时间比skill[i]少，那将i+1赋给a
				a = i + 1;
			}
		}
		c = (skills[a].getCD() - (this.timeCosuming - skills[a].getSkillLastTime()));
		// System.out.println("由于技能全部进入CD，所以进行技能重置,此时要冷却的技能为"+skills[a].getName()+",其剩余冷却时间为"+c);
		timeCosuming += c;
		// 把剩余冷却时间最短的技能CD状态设置为false
		skills[a].setIsCD(false);
		

	}
}
