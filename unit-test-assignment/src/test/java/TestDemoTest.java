import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class TestDemoTest {
	
	private TestDemo testDemo;

	@BeforeEach
	void setUp() throws Exception {
		testDemo = new TestDemo();
	}

	@ParameterizedTest
	@MethodSource("TestDemoTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, Boolean expectException) {
		if(!expectException) {
			  assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
			} else {
				assertThatThrownBy ( () ->
					testDemo.addPositive (a, b))
						.isInstanceOf(IllegalArgumentException.class);
			}

	}
	
	static Stream<Object> argumentsForAddPositive () {
		
		Object [] arrayOfArguments = new Object [] {
				
				arguments(2, 4, 6, false), // Positive Positive
				arguments(1, -1, 0, true), // Positive Negative
				arguments(-1, 1, 0, true), // Negative Positive
				arguments(-1, 0, -1, true), // Negative Zero
				arguments(0, -1, -1, true), // Zero Negative
				arguments(1, 0, 1, true), // Positive Zero
				arguments(0, 1, 0, true), // Zero Positive
				arguments(0, 0, 0, true), // Zero Zero
				arguments(-1, -1, -2, true), // Negative Negative
		};
		
		return Stream.of(arrayOfArguments);
	}
	
	@Test
	void assertThatNumberSquaredIsCorrect() {
		
		TestDemo mockDemo = spy(testDemo);
		
		doReturn(5).when(mockDemo).getRandomInt();
		
		int fiveSquared = mockDemo.randomNumberSquared();
		
		assertThat(fiveSquared).isEqualTo(25);
	}
}
