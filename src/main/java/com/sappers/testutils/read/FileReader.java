package com.sappers.testutils.read;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;

import com.sappers.testutils.exception.IncompatibleFileContentsException;

public class FileReader {

	/**
	 * Reads a file in a way that first line is considered input array (having
	 * array elements separated by commas) and second line is considered it's result
	 * (could be array or single element defined by {@code <R>}) and so on until end
	 * of file, if file contains odd number of lines then it throws
	 * {@code IncompatibleFileContentsException}
	 * 
	 * @param <R> provided by {@code resultType}
	 * @param <T> provided by {@code inputsType}
	 * @param filePath	Base Path for input file
	 * @param fileName	Name of input file
	 * @param resultType Result line type could be Array Type or just Component Type (if result lines contain only one object)
	 * @param inputsType Component Type of input line Array e.g. provide Integer.class if line is array of integers
	 * @return List of Map.Entry where key is result {@code <R>} and value is input {@code T[]}
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static <R, T> List<Map.Entry<R, T[]>> readInputArrayLineFollowedByResultLine(String filePath,
			String fileName, Class<R> resultType, Class<T> inputsType) throws IOException {

		List<String> lines = Files.readAllLines(Paths.get(filePath, fileName));

		if (lines.size() % 2 != 0)
			throw new IncompatibleFileContentsException();

		List<Map.Entry<R, T[]>> inputs = new ArrayList<Map.Entry<R, T[]>>();

		int i = 0;
		while (i < lines.size()) {

			T[] input = parseInput(lines.get(i), inputsType);

			R result = (R) ConvertUtils.convert(lines.get(i + 1), resultType);

			inputs.add(Map.entry(result, input));
			i += 2;
		}

		return inputs;
	}

	@SuppressWarnings("unchecked")
	private static <T> T[] parseInput(String line, Class<T> inputsType) {

		String[] intsAsStrings = line.split(",");

		if ((intsAsStrings.length == 1) && intsAsStrings[0].isBlank())
			return (T[]) Array.newInstance(inputsType, 0);

		T[] input = (T[]) Array.newInstance(inputsType, intsAsStrings.length);

		for (int i = 0; i < intsAsStrings.length; i++)
			input[i] = (T) ConvertUtils.convert(intsAsStrings[i].strip(), inputsType);

		return input;
	}
}
