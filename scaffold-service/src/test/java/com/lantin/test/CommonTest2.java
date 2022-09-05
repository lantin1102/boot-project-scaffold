package com.lantin.test;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonTest2 {


	public static void main(String[] args) {
		String source = "[\n" +
				"    {\n" +
				"        \"no\":1,\n" +
				"        \"value\":{\n" +
				"            \"1\":1\n" +
				"        }\n" +
				"    },\n" +
				"    {\n" +
				"        \"no\":2,\n" +
				"        \"value\":{\n" +
				"            \"2\":1\n" +
				"        }\n" +
				"    },\n" +
				"    {\n" +
				"        \"no\":3,\n" +
				"        \"value\":{\n" +
				"            \"3\":1\n" +
				"        }\n" +
				"    },    \n" +
				"    {\n" +
				"        \"no\":4,\n" +
				"        \"value\":{\n" +
				"            \"4\":1\n" +
				"        }\n" +
				"    }, \n" +
				"    {\n" +
				"        \"no\":5,\n" +
				"        \"value\":{\n" +
				"            \"5\":1\n" +
				"        }\n" +
				"    },\n" +
				"    {\n" +
				"        \"no\":6,\n" +
				"        \"value\":{\n" +
				"            \"2\":2\n" +
				"        }\n" +
				"    },\n" +
				"    {\n" +
				"        \"no\":7,\n" +
				"        \"value\":{\n" +
				"            \"1\":2\n" +
				"        }\n" +
				"    },\n" +
				"    {\n" +
				"        \"no\":8,\n" +
				"        \"value\":{\n" +
				"            \"3\":2\n" +
				"        }\n" +
				"    },\n" +
				"    {\n" +
				"        \"no\":9,\n" +
				"        \"value\":{\n" +
				"            \"4\":2\n" +
				"        }\n" +
				"    },\n" +
				"    {\n" +
				"        \"no\":10,\n" +
				"        \"value\":{\n" +
				"            \"5\":2\n" +
				"        }\n" +
				"    }\n" +
				"]";

		List<MaterialProperty> list = JSON.parseArray(source,MaterialProperty.class);
		Panda panda = Panda.init();
		list.forEach(x-> incrAbility(panda,x.getValue()));

		// MaterialProperty diaryProperty = o.stream().filter(x -> {
		//     return Objects.equals(1, x.getNo());
		// }).findFirst().orElse(null);
		// String choose = "2";
		// Map<String, Integer> item = diaryProperty.getValueMap().get(choose);
		// System.out.println(item);
		// incrAbility(panda,item);

		System.out.println(panda);
	}

	static void incrAbility(Panda panda, Map<String, Integer> item) {
		item.forEach((k, v) -> {
			String name = AbilityMap.findName(Integer.parseInt(k));
			Field field = ReflectionUtils.findField(Panda.class, name);
			Assert.notNull(field, "field为空");
			ReflectionUtils.makeAccessible(field);
			Integer oldVal = (Integer) ReflectionUtils.getField(field, panda);
			Assert.notNull(oldVal, "old value为空");
			ReflectionUtils.setField(field, panda, oldVal + v);
		});
	}


	@Data
	static class Panda{
		Integer intel;
		Integer stamina;
		Integer imagine;
		Integer charm;
		Integer create;

		static Panda init(){
			Panda panda = new Panda();
			panda.setStamina(0);
			panda.setIntel(0);
			panda.setCharm(0);
			panda.setImagine(0);
			panda.setCreate(0);
			return panda;
		}

	}

	enum AbilityMap{
		INT("intel",1),
		STA("stamina",2),
		IMA("imagine",3),
		CHA("charm",4),
		CRE("create",5)
		;
		AbilityMap(String name,int index){
			this.name = name;
			this.index = index;
		}
		static final Map<Integer,AbilityMap> mapping = new HashMap<>();
		final String name;
		final int index;
		static {
			for (AbilityMap value : values()) {
				mapping.put(value.index, value);
			}
		}

		static String findName(Integer index) {
			return mapping.get(index).name;
		}

	}

	@Data
	static
	class MaterialProperty {

		Integer no;
		Map<String,Integer> value;
	}


	@Data
	static
	class DiaryProperty{

		Integer no;
		Map<String,Map<String,Integer>> valueMap;

	}

}
