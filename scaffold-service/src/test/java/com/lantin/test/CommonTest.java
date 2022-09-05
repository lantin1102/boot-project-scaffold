package com.lantin.test;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lantin.common.domain.response.CommonResponse;
import com.lantin.web.domain.account.Account;
import lombok.Data;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.Collator;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author Gan Luanqing
 * @date 2021/11/27 20:13 周六
 */
public class CommonTest {


	@Test
	public void test5(){
		String reg = "^[1-9]$";

		String reg1 = "^bucket$";

		// String str = "bucket";
		// boolean matches = str.matches(reg1);
		// System.out.println(matches);

		String str = "12";
		System.out.println(str.matches(reg));

	}

	@Test
	public void uuid(){
		String replace = UUID.randomUUID().toString().replace("-", "");

		String str1 = "fadsba|fda";

		str1.replace("|","%7C");

		System.out.println(str1);

		ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
		HashMap<Object, Object> map1 = new HashMap<>();


	}

	@Test
	public void test3(){

		String str = "358490314";

		String[] split = str.split(",");

		List<String> strings = Arrays.asList(split);

		System.out.println(strings);

		String s = new String("358490314");

		System.out.println(strings.contains(s));

	}


	@Test
	public void test123(){

		String s = "https://uat-i0.hdslb.com/bfs/game/27412346f286da2871a15ae36318bf5cf2bea5c9.jpg";

		System.out.println(s.length());
	}

	@Test
	public void sortTest() {
		List<Integer> objects = Arrays.asList(24, 11, 12, 9, 18, 20);
		Collections.sort(objects);
		System.out.println(objects);
	}

	@Test
	public void test2() {
		System.out.println(System.currentTimeMillis());


	}

	@Test
	public void test() throws JsonProcessingException {
		Account account = new Account();
		CommonResponse response = new CommonResponse(account);
		String s = JSON.toJSONString(response);
		System.out.println(s);
		ObjectMapper objectMapper = new ObjectMapper();

		String s1 = objectMapper.writeValueAsString(response);

		System.out.println(s1);
	}

	@Test
	public void numberTest() {
		int b = -1 << 5;
		// -1^其他数可以用 ~其他数来标示
		int a = ~b;
		// ~(-1<< x) 可以快速计算x位的二进制数能表示的最大十进制数为多少
		// -1<<-1 表示在当前的数字类型所能表示的最小数的值
		// 例如 -1<<-1 在int 类型 表示 -2147483648 相当于 -1<<31
		System.out.println(-1 << -1);
		System.out.println(a);
		System.out.println(b);
	}

	@Test
	public void test33() {
		Collator instance = Collator.getInstance(Locale.CHINESE);
		String[] newArray = {"简单", "写写", "我的", "理解", "张泮祺", "孙荣⼤", "万⾬⾠", "1HeHe", "安祉⾂", "宋明瑶", "李博⼀", "Mike", "李正彭", "吴政航", "徐豪"};
		ArrayList<String> strings = new ArrayList<>(Arrays.asList(newArray));
		List<People> peopleList = new ArrayList<>();
		for (int i = 0; i < strings.size(); i++) {
			People p = new People();
			p.setId(i+1);
			p.setName(strings.get(i));
			// People people = new com.lantin.test.CommonTest.People(i + 1, strings.get(i));
			// peopleList.add(people);
			peopleList.add(p);

		}

		Collections.sort(strings);

		Collections.sort(peopleList, (o1, o2) -> instance.compare(o1.getName(), o2.getName()));
		System.out.println(strings);

		System.out.println(peopleList);
	}

	@Test
	public void test444() {
		String[] newArray = {"简单", "写写", "我的", "理解", "张泮祺", "孙荣⼤", "万⾬⾠", "1HeHe", "安祉⾂", "宋明瑶", "李博⼀", "Mike", "李正彭", "吴政航", "徐豪"};
		ArrayList<String> strings = new ArrayList<>(Arrays.asList(newArray));
		List<People> peopleList = new ArrayList<>();
		for (int i = 0; i < strings.size(); i++) {
			People p = new People();
			p.setId(i+1);
			p.setName(strings.get(i));
			peopleList.add(p);
		}

		Collections.sort(peopleList);
		System.out.println(peopleList);
	}

	@Test
	public void streamSort() {

		// stream sort多条件排序
		// list.sort(comparing()
		// .thenComparing()
		// .thenComparing())
		int i = 3 << 29;
		double pow = Math.pow(2, 29) * 3;
		int i1 = BigDecimal.valueOf(pow).intValue();
		System.out.println(i);
		System.out.println(i1);

		System.out.println("---------");

		int b = -1 << 29;
		System.out.println(b);
		int x = 0b11111111111111111111111111111111;
		System.out.println();
		System.out.println(x);
		int y = 0b11111111111111111111111111111110;
		System.out.println(y);
	}

	@Test
	public void testJiuGongGe() {
		/**
		 *  1 2 3
		 *  4 5 6
		 *  7 8 9
		 *
		 *          1  2  3  4
		 * 		 *  5  6  7  8
		 * 		    9 10 11 12
		 * 		 * 13 14 15 16
		 *
		 *
		 */
		List<List<Integer>> lineUpMatrix = new ArrayList<>();
		int dimension = 3;
		int[][] matrix = new int[dimension][dimension];
		List<Integer> allPosition = new ArrayList<>();
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				matrix[i][j] = i * dimension + (j + 1);
				allPosition.add(matrix[i][j]);
			}
		}
		List<List<Integer>> columns = new ArrayList<>();
		for (int i = 0; i < dimension; i++) {
			columns.add(new ArrayList<>());
		}
		List<Integer> diagonal = new ArrayList<>();
		List<Integer> backDiagonal = new ArrayList<>();
		// 每一行
		for (int i = 0; i < matrix.length; i++) {
			int[] row = matrix[i];
			//加入每行的数据
			lineUpMatrix.add(new ArrayList<>(Arrays.stream(row).boxed().toList()));
			for (int j = 0; j < matrix[i].length; j++) {
				int curNum = matrix[i][j];
				// 加入列数据
				List<Integer> column = columns.get(j);
				column.add(curNum);
				// 对角线
				if (i == j) {
					diagonal.add(curNum);
				} //反对角线
				if ((i + j) == (dimension - 1)) {
					backDiagonal.add(curNum);
				}
			}
		}
		lineUpMatrix.addAll(columns);
		lineUpMatrix.add(diagonal);
		lineUpMatrix.add(backDiagonal);

		System.out.println(Arrays.deepToString(matrix));
		System.out.println(lineUpMatrix);

		List<Integer> userPositionList = Arrays.asList(1,2, 4, 5, 6, 8,7,9);

		long count = lineUpMatrix.stream().filter(userPositionList::containsAll)
				.count();

		System.out.println(count);


	}

	@Test
	public void testMark() {
		int x = 0, y = 0;
		mark:
		for (; ; ) {
			if (x > 10) {
				return;
			}
			for (; ; ) {
				y++;
				if (x == 10) {
					break mark;
				}
				if (y % 2 == 0) {
					// continue mark 作用
					// 回到mark标志下的代码重新执行
					continue mark;

				}
				x++;
			}
		}
		System.out.println("x:" + x + "y:" + y);
	}


	@Data
	static
	class People implements Comparable<People> {
		private int id;

		private String name;


		@Override
		public int compareTo(People o) {

			Collator instance = Collator.getInstance(Locale.CHINESE);
			return instance.compare(this.name, o.getName());
		}
	}

	@Test
	public void testUUID(){
		String replace = UUID.randomUUID().toString().replace("-", "");

		System.out.println(replace.length());
	}

}
