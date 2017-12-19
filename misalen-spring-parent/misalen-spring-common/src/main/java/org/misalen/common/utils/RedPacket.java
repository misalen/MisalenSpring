package org.misalen.common.utils;

import java.util.Random;

public class RedPacket {
	static Random random = new Random();
	static {
		random.setSeed(System.currentTimeMillis());
	}

	public static void main(String[] args) {
		// 测试数据：100块钱分成20分，最大20元，最小1元
		int[] result = generate(1990, 200, 20, 1);
		long all = 0;
		for (int i = 0; i < result.length; i++) {
			all += result[i];
			System.out.println("第" + (i + 1) + "份:" + result[i] + "分");
		}
		System.out.println("总金额：" + all + "分");
	}

	/**
	 * 写这段代码的时候
	 * 只有上帝和我知道他是什么意思
	 * 现在,只有上帝知道
	 * @author DO·VIS
	 * 2017年11月20日
	 */
	public static int[] generate(int total, int count, int min, int max) {
		if (!isRight(total, count, min, max)) {
			return null;
		}
		int[] result = new int[count];
		int average = total / count;
		for (int i = 0; i < result.length; i++) {
			if (nextInt(min, max) > average) {
				int temp = min + nextInt(min, average);
				result[i] = temp;
				total -= temp;
			} else {
				int temp = max - nextInt(average, max);
				result[i] = temp;
				total -= temp;
			}
		}
		while (total > 0) {
			for (int i = 0; i < result.length; i++) {
				if (total > 0 && result[i] < max) {
					result[i]++;
					total--;
				}
			}
		}
		while (total < 0) {
			for (int i = 0; i < result.length; i++) {
				if (total < 0 && result[i] > min) {
					result[i]--;
					total++;
				}
			}
		}
		return result;
	}

	private static boolean isRight(int money, int count, int min, int max) {
		return money >= 0 && count > 0 && min > 0 && max >= min && money >= count * min && money <= count * max;
	}

	private static int nextInt(int min, int max) {
		return random.nextInt((max - min + 1)) + min;
	}
}
