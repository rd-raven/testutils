package com.sappers.testutils.read;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;

import com.sappers.testutils.exception.IncompatibleFileContentsException;

public class FileReaderTest {

	private static final String RESOURCES_BASE = "./src/test/resources/";
	private static final String BASE_PATH = RESOURCES_BASE.concat("com/sappers/testutils/read");
	private static final String INPUT_ARRAY_LINES_FOLLOWED_BY_RESULT_ARRAY_LINES = "input-array-lines-followed-by-result-array-lines";
	private static final String INPUT_ARRAY_LINES_FOLLOWED_BY_RESULT_ARRAY_LINES_ODDLINES = "input-array-lines-followed-by-result-array-lines-oddlines";

	@Test
	void readInputArrayLineFollowedByResultLine_Simple_Test() throws IOException {

		List<Entry<int[], Integer[]>> testCases = FileReader.readInputArrayLineFollowedByResultLine(BASE_PATH,
				INPUT_ARRAY_LINES_FOLLOWED_BY_RESULT_ARRAY_LINES, int[].class, Integer.class);

		assertNotNull(testCases);

		int[] result;
		Integer[] input;

		result = testCases.get(0).getKey();
		input = testCases.get(0).getValue();

		assertArrayEquals(result, new int[] { 3, 7, 5 });
		assertArrayEquals(input, new Integer[] { 1, 2, 3, 4, 5 });

		result = testCases.get(1).getKey();
		input = testCases.get(1).getValue();

		assertArrayEquals(result, new int[] { 3, 7, 11, 15 });
		assertArrayEquals(input, new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8 });

		result = testCases.get(2).getKey();
		input = testCases.get(2).getValue();

		assertArrayEquals(result, new int[] { 3, 7, 11, 15, 19 });
		assertArrayEquals(input, new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
	}

	@Test
	void readInputArrayLineFollowedByResultLine_Odd_Lines_Test() throws IOException {

		assertThrows(IncompatibleFileContentsException.class, () -> {
			FileReader.readInputArrayLineFollowedByResultLine(BASE_PATH,
					INPUT_ARRAY_LINES_FOLLOWED_BY_RESULT_ARRAY_LINES_ODDLINES, int[].class, Integer.class);
		});
	}

}
