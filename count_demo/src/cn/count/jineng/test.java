package cn.count.jineng;

import java.text.DecimalFormat;

public class test {
	public static void test1(Skill[] skills, int times) {
		// 创建CountSkillDamages对象
		CountSkillDamages csd = new CountSkillDamages();
		// 对技能数组进行排序
		csd.arraySkills(skills);
		DecimalFormat df = new DecimalFormat("0.00");
		double damages = csd.count(skills, times);
		System.out.println(+times + "秒总输出为" + df.format(damages));
		System.out.println("用时" + csd.timeCosuming + "s");
		System.out.println("其中:  ");

		for (Skill ss : skills) {
			System.out.println(ss.getName() + "使用了" + ss.getUseNumber() + "次,总伤害为" + ss.getUseNumber() * ss.getDamage()
					+ "   占总伤害"
					+ df.format((float) (ss.getUseNumber() * ss.getDamage()) / (float) csd.totalDamages * 100) + "%");
		}
	}

	public static void main(String[] args) {

		// name castTime damages CD
		Skill s1 = new Skill("二觉", 1.44, 48, 136.8);
		Skill s2 = new Skill("血球", 0.7, 18, 34.2);
		Skill s3 = new Skill("血爆", 0.6, 18, 30.4);
		Skill s4 = new Skill("怒气", 0.4, 12, 8.6);
		Skill s5 = new Skill("一觉", 1.72, 21, 110.2);
		Skill s6 = new Skill("大蹦", 1.25, 19, 22.8);
		Skill s7 = new Skill("大吸", 1.8, 13, 17.1);
		Skill s8 = new Skill("大怒气", 0.6, 13, 34.4);
		Skill s9 = new Skill("血剑", 1.1, 12, 11.4);
		Skill s10 = new Skill("崩山击", 0.58, 3.7, 3);
		Skill s11 = new Skill("平砍", 1, 1.15, 0);
		Skill[] skills = { s1, s2, s3, s4, s5, s6, s7, s8, s9, s10 };
		test1(skills, 600);

		

	}

}
