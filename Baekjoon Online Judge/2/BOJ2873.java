import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * Baekjoon Online Judge No.2873 Problem: 롤러코스터<br>
 * 
 * Date : 2022-11-25
 * 
 * @see <a href="https://www.acmicpc.net/problem/2873">롤러코스터</a>
 * @version 1.0.0
 * @author Woosung Jo
 * @category 구현, 그리디 알고리즘, 구성적
 * 
 * Timeout Limit : 1000 ms
 * Memory Limit  : 256 MB
 */
public class BOJ2873 {

	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st;
	private StringBuffer buffer = new StringBuffer();

	private int r, c;
	private int[][] arr;

	public static void main(String[] args) throws Exception {
		new BOJ2873();
	}

	public BOJ2873() throws Exception {
		setArray();
		bw.write(loopAction());
		
		bw.flush();
		br.close();
		bw.close();
	}

	/**
	 * 초기화
	 * 
	 * 배열 사이즈 정의 및 값 지정
	 */
	private void setArray() throws Exception {
		st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		arr = new int[r][c];

		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	/**
	 * 반복
	 * 
	 * 명령에 따라 롤러코스터가 사람들에게 가장 큰 기쁨을 주기위해 움직이는 순서를 기록한다.
	 * 
	 * @return 가장 큰 기쁨을 주는 롤러코스터가 움직이는 순서
	 */
	private String loopAction() {
		if (r % 2 != 0) {
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c - 1; j++) {
					if (i % 2 == 0)
						buffer.append("R");
					else
						buffer.append("L");
				}
				if (i < r - 1)
					buffer.append("D");
			}
		}

		else if (r % 2 == 0 && c % 2 != 0) {
			for (int i = 0; i < c; i++) {
				for (int j = 0; j < r - 1; j++) {
					if (i % 2 == 0) buffer.append("D");
					else buffer.append("U");
				}
				if (i < c - 1) buffer.append("R");
			}
		}

		else if (r % 2 == 0 && c % 2 == 0) {
			int mr = 0, mc = 0; // 가장 최소 기쁨을 가진 검은타일 위치
			int min = 1001; // 가장 최소 기쁨을 가진 검은타일의 기쁨
			for (int i = 0; i < r; i++) {
				int j;
				if (i % 2 == 0) j = 1;
				else j = 0;
				for (; j < c; j += 2) {
					if (arr[i][j] < min) {
						min = arr[i][j];
						mr = i;
						mc = j;
					}
				}
			}

			StringBuffer buf1 = new StringBuffer();
			StringBuffer buf2 = new StringBuffer();
			int sr = 0;
			int sc = 0;
			int er = r - 1;
			int ec = c - 1;

			while (er - sr > 1) {
				if (sr / 2 < mr / 2) {
					for (int i = 0; i < c - 1; i++) buf1.append("R");
					buf1.append("D");

					for (int i = 0; i < c - 1; i++) buf1.append("L");
					buf1.append("D");
					
					sr += 2;
				}
				if (er / 2 > mr / 2) {
					for (int i = 0; i < c - 1; i++) buf2.append("R");
					buf2.append("D");

					for (int i = 0; i < c - 1; i++) buf2.append("L");
					buf2.append("D");
					
					er -= 2;
				}
			}

			while (ec - sc > 1) {
				if (sc / 2 < mc / 2) {
					buf1.append("D").append("R").append("U").append("R");
					sc += 2;
				}
				if (ec / 2 > mc / 2) {
					buf2.append("D").append("R").append("U").append("R");
					ec -= 2;
				}
			}
			if (mc == sc) buf1.append("R").append("D");
			else buf1.append("D").append("R");
			

			buffer.append(buf1);
			buffer.append(buf2.reverse());

		}
		return buffer.toString();
	}
}
