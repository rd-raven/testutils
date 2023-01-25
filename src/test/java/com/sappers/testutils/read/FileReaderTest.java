package com.sappers.testutils.read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;

import com.sappers.testutils.exception.IncompatibleFileContentsException;

import static org.junit.jupiter.api.Assertions.*;

public class FileReaderTest {

	private static final String RESOURCES_BASE = "./src/test/resources/";
	private static final String BASE_PATH = RESOURCES_BASE.concat("com/sappers/testutils/read");
	private static final String INPUT_ARRAY_LINES_FOLLOWED_BY_RESULT_ARRAY_LINES = "input-array-lines-followed-by-result-array-lines";
	private static final String INPUT_ARRAY_LINES_FOLLOWED_BY_RESULT_ARRAY_LINES_ODDLINES = "input-array-lines-followed-by-result-array-lines-oddlines";
	private static final String INPUT_ARRAY_LINES_FOLLOWED_BY_RESULT_ARRAY_EMPTY_INPUT = "input-array-lines-followed-by-result-array-empty-input";
	private static final String INPUT_ARRAY_LINES_FOLLOWED_BY_RESULT_ARRAY_EMPTY_OUTPUT = "input-array-lines-followed-by-result-array-empty-output";
	private static final String INPUT_ARRAY_LINES_FOLLOWED_BY_RESULT_ARRAY_EMPTY_INPUT_OUTPUT = "input-array-lines-followed-by-result-array-empty-input-output";
	private static final String INPUT_ARRAY_LINES_FOLLOWED_BY_RESULT_ARRAY_EMPTY_FILE = "input-array-lines-followed-by-result-array-empty-file";
	private static final String INPUT_ARRAY_LINES_FOLLOWED_BY_RESULT_ARRAY_ALPHABETS = "input-array-lines-followed-by-result-array-alphabets";
	private static final String INPUT_ARRAY_LINES_FOLLOWED_BY_RESULT_ARRAY_DIFF_TYPE_INPUT_OUTPUT = "input-array-lines-followed-by-result-array-diff-type-input-output";
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

	@Test
	void readInputArrayLineFollowedByResultLine_Empty_Input() throws IOException {
		List<Entry<int[], Integer[]>> testCases = FileReader.readInputArrayLineFollowedByResultLine(BASE_PATH,
				INPUT_ARRAY_LINES_FOLLOWED_BY_RESULT_ARRAY_EMPTY_INPUT, int[].class, Integer.class);

		assertNotNull(testCases);

		int[] result;
		Integer[] input;

		result = testCases.get(0).getKey();
		input = testCases.get(0).getValue();

		assertArrayEquals(result, new int[] {1});
		assertArrayEquals(input, new Integer[] {});
	}

	@Test
	void readInputArrayLineFollowedByResultLine_Empty_Output() throws IOException {
		List<Entry<int[], Integer[]>> testCases = FileReader.readInputArrayLineFollowedByResultLine(BASE_PATH,
				INPUT_ARRAY_LINES_FOLLOWED_BY_RESULT_ARRAY_EMPTY_OUTPUT, int[].class, Integer.class);

		assertNotNull(testCases);

		int[] result;
		Integer[] input;

		result = testCases.get(0).getKey();
		input = testCases.get(0).getValue();

		assertArrayEquals(result, new int[] {});
		assertArrayEquals(input, new Integer[] {1});
	}

	@Test
	void readInputArrayLineFollowedByResultLine_Empty_Input_Output() throws IOException {
		List<Entry<int[], Integer[]>> testCases = FileReader.readInputArrayLineFollowedByResultLine(BASE_PATH,
				INPUT_ARRAY_LINES_FOLLOWED_BY_RESULT_ARRAY_EMPTY_INPUT_OUTPUT, int[].class, Integer.class);

		assertNotNull(testCases);

		int[] result;
		Integer[] input;

		result = testCases.get(0).getKey();
		input = testCases.get(0).getValue();

		assertArrayEquals(result, new int[] {});
		assertArrayEquals(input, new Integer[] {});
	}

	@Test
	void readInputArrayLineFollowedByResultLine_Empty_File() throws IOException {

		assertEquals(new ArrayList<>(), FileReader.readInputArrayLineFollowedByResultLine(BASE_PATH,
					INPUT_ARRAY_LINES_FOLLOWED_BY_RESULT_ARRAY_EMPTY_FILE, int[].class, Integer.class));
	}

	@Test
	void readInputArrayLineFollowedByResultLine_Alphabets() throws IOException {

		List<Entry<char[], Character[]>> testCases = FileReader.readInputArrayLineFollowedByResultLine(BASE_PATH,
					INPUT_ARRAY_LINES_FOLLOWED_BY_RESULT_ARRAY_ALPHABETS, char[].class, Character.class);

		assertNotNull(testCases);

		char[] result;
		Character[] input;

		result = testCases.get(0).getKey();
		input = testCases.get(0).getValue();

		assertArrayEquals(result, new char[] {'a'});
		assertArrayEquals(input, new Character[] {'a', 'b', 'c'});
	}

	@Test
	void readInputArrayLineFollowedByResultLine_Diff_Type_Input_Output() throws IOException {

		List<Entry<int[], Character[]>> testCases = FileReader.readInputArrayLineFollowedByResultLine(BASE_PATH,
				INPUT_ARRAY_LINES_FOLLOWED_BY_RESULT_ARRAY_DIFF_TYPE_INPUT_OUTPUT, int[].class, Character.class);

		assertNotNull(testCases);

		int[] result;
		Character[] input;

		result = testCases.get(0).getKey();
		input = testCases.get(0).getValue();

		assertArrayEquals(result, new int[] {1, 2, 3});
		assertArrayEquals(input, new Character[] {'a', 'b', 'c'});
	}
}
