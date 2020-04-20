/*
 * Author: Christopher Medlin
 * Email: cmedlin@cnm.edu
 * Date: 06 Feb 2020
 * Course: CSCI1152
 * 
 * Determines highest elevation of square shaped area given topographical data in file.
 */

import java.util.Scanner;
import java.util.NoSuchElementException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class Lab05 {

	public static void main(String[] args) {
		GolfCourseMap[] maps = parseData(Paths.get("GolfCourseData"));
		for (int mapI = 0; mapI < maps.length; mapI++) {
			System.out.printf("Case %d:%n", mapI+1);
			GolfCourseResult[][] results = maps[mapI].solve();
			for (int i = 0; i < results.length; i++) {
				for (int j = 0; j < results[i].length; j++) {
					System.out.printf("%d", results[i][j].highest);
					if (results[i][j].count > 1) {
						System.out.printf("(%d)", results[i][j].count);
					}
					System.out.print(" ");
				}
				System.out.println();
			}
			System.out.println();
		}
	}

	private static GolfCourseMap[] parseData(Path path) {
		GolfCourseMap[] maps;
		try(Scanner in = new Scanner(path)) {
			int cases = Integer.parseInt(in.nextLine());
			maps = new GolfCourseMap[cases];

			for (int i = 0; i < cases; i++) {
				maps[i] = parseMap(in);

				// for the lines between maps
				if (in.hasNext()) {
					in.nextLine();
				}
			}
			return maps;
		} catch (IOException e) {
			System.err.println("File does not exist.");
		} catch (NoSuchElementException e) {
			System.err.println("Invalid file format.");
		}

		return null;
	}

	private static GolfCourseMap parseMap(Scanner in) {
		String[] metadata = in.nextLine().split(" ");
		int length = Integer.parseInt(metadata[0]);
		int width = Integer.parseInt(metadata[1]);
		int golfCourseWidth = Integer.parseInt(metadata[2]);
		int[][] map = new int[length][width];
		
		for (int i = 0; i < length; i++) {
			String[] split = in.nextLine().split(" ");
			for (int j = 0; j < split.length; j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		}

		return new GolfCourseMap(width, length, golfCourseWidth, map);
	}

	private static class GolfCourseResult {
		int highest;
		int count;
		
		public GolfCourseResult(int highest, int count) {
			this.highest = highest;
			this.count = count;
		}
	}
	
	// I kept this as a local class so that this program only has one file,
	// since that's probably easier for you to test.
	private static class GolfCourseMap {
		private int width;
		private int length;
		private int golfCourseWidth;
		private int[][] map;

		public GolfCourseMap(int width, int length, int golfCourseWidth, int[][] map) {
			this.width = width;
			this.length = length;
			this.golfCourseWidth = golfCourseWidth;
			this.map = map;
		}

		public GolfCourseResult[][] solve() {
			GolfCourseResult[][] results = 
				new GolfCourseResult[length-golfCourseWidth+1][width-golfCourseWidth+1];
			
			for (int i = 0; i <= length-golfCourseWidth; i++) {
				for (int j = 0; j <= width-golfCourseWidth; j++)
					results[i][j] = solveSubArea(i, j);
			}

			return results;
		}

		public GolfCourseResult solveSubArea(int startRow, int startCol) {
			int highest = 0;
			int count = 0;

			for (int i = startRow; i < startRow+golfCourseWidth; i++) {
				for (int j = startCol; j < startCol+golfCourseWidth; j++) {
					if (map[i][j] > highest) {
						highest = map[i][j];
						count = 1;
					} else if (map[i][j] == highest) {
						count++;
					}
				}
			}
			
			return new GolfCourseResult(highest, count);
		}
	}
}
