package com.kennedy.java.kata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

public class KataTest {

	@Test
    public void stairsIn20_BasicTest1() {
        int[] sunday = {
	          6737, 7244, 5776, 9826, 7057, 9247, 5842, 5484, 6543, 5153, 6832, 8274,
	          7148, 6152, 5940, 8040, 9174, 7555, 7682, 5252, 8793, 8837, 7320, 8478, 6063, 
	          5751, 9716, 5085, 7315, 7859, 6628, 5425, 6331, 7097, 6249, 8381, 5936, 8496, 
	          6934, 8347, 7036, 6421, 6510, 5821, 8602, 5312, 7836, 8032, 9871, 5990, 6309, 7825};

        int[] monday = {
	          9175, 7883, 7596, 8635, 9274, 9675, 5603, 6863, 6442, 9500, 7468, 9719,
	          6648, 8180, 7944, 5190, 6209, 7175, 5984, 9737, 5548, 6803, 9254, 5932, 7360, 9221, 
	          5702, 5252, 7041, 7287, 5185, 9139, 7187, 8855, 9310, 9105, 9769, 9679, 7842,
	          7466, 7321, 6785, 8770, 8108, 7985, 5186, 9021, 9098, 6099, 5828, 7217, 9387};

        int[] tuesday = {
	           8646, 6945, 6364, 9563, 5627, 5068, 9157, 9439, 5681, 8674, 6379, 8292,
	           7552, 5370, 7579, 9851, 8520, 5881, 7138, 7890, 6016, 5630, 5985, 9758, 8415, 7313,
	           7761, 9853, 7937, 9268, 7888, 6589, 9366, 9867, 5093, 6684, 8793, 8116, 8493, 
	           5265, 5815, 7191, 9515, 7825, 9508, 6878, 7180, 8756, 5717, 7555, 9447, 7703};

        int[] wednesday = {
		       6353, 9605, 5464, 9752, 9915, 7446, 9419, 6520, 7438, 6512, 7102, 
		       5047, 6601, 8303, 9118, 5093, 8463, 7116, 7378, 9738, 9998, 7125, 6445, 6031, 8710,
		       5182, 9142, 9415, 9710, 7342, 9425, 7927, 9030, 7742, 8394, 9652, 5783, 7698, 
		       9492, 6973, 6531, 7698, 8994, 8058, 6406, 5738, 7500, 8357, 7378, 9598, 5405, 9493};

        int[] thursday = {
        	   6149, 6439, 9899, 5897, 8589, 7627, 6348, 9625, 9490, 5502, 5723, 8197,
	           9866, 6609, 6308, 7163, 9726, 7222, 7549, 6203, 5876, 8836, 6442, 6752, 8695, 8402,
	           9638, 9925, 5508, 8636, 5226, 9941, 8936, 5047, 6445, 8063, 6083, 7383, 7548, 5066, 
	           7107, 6911, 9302, 5202, 7487, 5593, 8620, 8858, 5360, 6638, 8012, 8701};

        int[] friday = {
	           5000, 5642, 9143, 7731, 8477, 8000, 7411, 8813, 8288, 5637, 6244, 6589, 6362, 
		       6200, 6781, 8371, 7082, 5348, 8842, 9513, 5896, 6628, 8164, 8473, 5663, 9501, 
		       9177, 8384, 8229, 8781, 9160, 6955, 9407, 7443, 8934, 8072, 8942, 6859, 5617,
		       5078, 8910, 6732, 9848, 8951, 9407, 6699, 9842, 7455, 8720, 5725, 6960, 5127};

        int[] saturday = {
	           5448, 8041, 6573, 8104, 6208, 5912, 7927, 8909, 7000, 5059, 6412, 6354, 8943, 
	           5460, 9979, 5379, 8501, 6831, 7022, 7575, 5828, 5354, 5115, 9625, 7795, 7003, 
	           5524, 9870, 6591, 8616, 5163, 6656, 8150, 8826, 6875, 5242, 9585, 9649, 9838, 
	           7150, 6567, 8524, 7613, 7809, 5562, 7799, 7179, 5184, 7960, 9455, 5633, 9085};
        int[][] stairs = {sunday,monday,tuesday,wednesday,thursday,friday,saturday};  
        long expectedResult = 54636040;
        assertEquals(expectedResult, Kata.stairsIn20(stairs));
    }
	
	@Test
    public void testFixed() {
        assertEquals("b***i***t***c***o***i***n", Kata.twoSort(new String[] {"bitcoin", "take", "over", "the", "world", "maybe", "who", "knows", "perhaps"}));
        assertEquals("a***r***e", Kata.twoSort(new String[] {"turns", "out", "random", "test", "cases", "are", "easier", "than", "writing", "out", "basic", "ones"}));
    }
	
	@Test
    public void testSomething() {
        assertEquals("1 sheep...", Kata.countingSheep(1));
        assertEquals("1 sheep...2 sheep...", Kata.countingSheep(2));
        assertEquals("1 sheep...2 sheep...3 sheep...", Kata.countingSheep(3));
    }
	
	@Test
	public void basicTests() throws Exception{
	    Cockroach cockroach = new Cockroach();
	    assertEquals(30, cockroach.cockroachSpeed(1.08));
	    assertEquals(30, cockroach.cockroachSpeed(1.09));
	    assertEquals(0, cockroach.cockroachSpeed(0));
	}
	
	@Test
    public void testFixed1() {
        assertEquals("S.H", Kata.abbrevName("Sam Harris"));
        assertEquals("P.F", Kata.abbrevName("Patrick Feenan"));
        assertEquals("E.C", Kata.abbrevName("Evan Cole"));
        assertEquals("P.F", Kata.abbrevName("P Favuzzi"));
        assertEquals("D.M", Kata.abbrevName("David Mendieta"));
    }
	
	@Test
	public void testCompareString() {
		String[] type = {"Amount", "Link<Currency>", "Link<SocialInsurance>", "Link<VatCase>"} ;
		
		for (String string : type) {
			switch (string.toLowerCase()) {
			case "amount":
				System.err.println("amount=" + string);
				break;

			case "link<currency>":
				System.err.println("currency=" + string);
				break;
			default:
				if (string.startsWith("Link")) {
					System.err.println("link=" + string);
				}
				break;
			}
		}
	}
	
	@Test
	public void test_Count_letter() {
		assertEquals(Kata.strCount("Hello", 'o'),1);
        assertEquals(Kata.strCount("Hello", 'l'),2);
        assertEquals(Kata.strCount("",'z'),0);
	}
	
	@Test
	public void RowSumOddNumbersTest() {
		assertEquals(1, Kata.rowSumOddNumbers(1));
		assertEquals(8, Kata.rowSumOddNumbers(2));
		assertEquals(27, Kata.rowSumOddNumbers(3));
		assertEquals(64, Kata.rowSumOddNumbers(4));
		assertEquals(125, Kata.rowSumOddNumbers(5));
		assertEquals(74088, Kata.rowSumOddNumbers(42));
	}

	@Test
	public void toAlternativeStringTests() {
		assertEquals("HELLO WORLD", Kata.toAlternativeString("hello world"));
		assertEquals("hello world", Kata.toAlternativeString("HELLO WORLD"));
		assertEquals("HELLO world", Kata.toAlternativeString("hello WORLD"));
		assertEquals("hEllO wOrld", Kata.toAlternativeString("HeLLo WoRLD"));
		assertEquals("Hello World", Kata.toAlternativeString(Kata.toAlternativeString("Hello World")));
		assertEquals("12345", Kata.toAlternativeString("12345"));
		assertEquals("1A2B3C4D5E", Kata.toAlternativeString("1a2b3c4d5e"));
		assertEquals("sTRINGuTILS.TOaLTERNATINGcASE", Kata.toAlternativeString("StringUtils.toAlternatingCase"));
		assertEquals("ALTerNAtiNG CaSe", Kata.toAlternativeString("altERnaTIng cAsE"));
		assertEquals("altERnaTIng cAsE", Kata.toAlternativeString("ALTerNAtiNG CaSe"));
		assertEquals("ALTerNAtiNG CaSe <=> altERnaTIng cAsE", Kata.toAlternativeString("altERnaTIng cAsE <=> ALTerNAtiNG CaSe"));
		assertEquals("altERnaTIng cAsE <=> ALTerNAtiNG CaSe", Kata.toAlternativeString("ALTerNAtiNG CaSe <=> altERnaTIng cAsE"));
	}

	@Test
	public void simpleTest() {
		assertArrayEquals(new int[] { 5, 4, 3, 2, 1 }, Kata.reverse(5));
	}
	
	@Test
	public void startTest() {
		assertEquals("Your string should start with a 1", '1', Kata.stringy(1).charAt(0));
		assertEquals("Your string should start with a 1", '0', Kata.stringy(3).charAt(1));
		assertEquals("Your string should start with a 1", '1', Kata.stringy(3).charAt(0));
	}
	
	@Test
	public void tests() {
		Object[] haystack1 = { "3", "123124234", null, "needle", "world", "hay", 2, "3", true, false };
		Object[] haystack2 = { "283497238987234", "a dog", "a cat", "some random junk", "a piece of hay", "needle", "something somebody lost a while ago" };
		Object[] haystack3 = { 1, 2, 3, 4, 5, 6, 7, 8, 8, 7, 5, 4, 3, 4, 5, 6, 67, 5, 5, 3, 3, 4, 2, 34, 234, 23, 4, 234, 324, 324, "needle", 1, 2, 3, 4, 5, 5, 6, 5, 4, 32, 3, 45,
				54 };
		assertEquals("found the needle at position 3", Kata.findNeedle(haystack1));
		assertEquals("found the needle at position 5", Kata.findNeedle(haystack2));
		assertEquals("found the needle at position 30", Kata.findNeedle(haystack3));
	}
	
	@Test
    public void countPositivesSumNegatives_BasicTest() {
      int[] expectedResult = new int[] {10, -65};
      assertArrayEquals(expectedResult, Kata.countPositivesSumNegatives(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -11, -12, -13, -14, -15}));
      int[] expectedResult1 = new int[] {8, -50};
      assertArrayEquals(expectedResult1, Kata.countPositivesSumNegatives(new int[] {0, 2, 3, 0, 5, 6, 7, 8, 9, 10, -11, -12, -13, -14}));
    }
	
	@Test
    public void basicTest() {
         assertEquals("apple", Kata.subtractSum(10));
    }
	
	@Test
    public void testMakeUpperCase() {
       assertEquals("HELLO",Kata.makeUpperCase("hello"));
    }
	
	@Test
	public void testHowOld() {
		assertEquals(5, Kata.howOld("5 years old"));
	}
}
