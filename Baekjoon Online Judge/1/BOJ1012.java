import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * Baekjoon Online Judge No.1012 Problem: 유기농 배추<br>
 * 
 * Date : 2022-10-12
 * 
 * @see <a href="https://www.acmicpc.net/problem/1012">유기농 배추</a>
 * @version 1.0.0
 * @author Woosung Jo
 * @category 그래프 이론, 그래프 탐색, 너비 우선 탐색, 깊이 우선 탐색
 * 
 * Timeout Limit : 1000 ms
 * Memory Limit  : 512 MB
 */
public class BOJ1012 {

	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st;

	private int[][] arr;

	private int[] dx = { -1, 0, 1, 0 };
	private int[] dy = { 0, 1, 0, -1 };

	private int cnt = 0;

	public static void main(String[] args) throws Exception {
		new BOJ1012();
	}

	public BOJ1012() throws Exception {
		loopAction(Integer.parseInt(br.readLine()));

		bw.flush();
		br.close();
		bw.close();
	}

	/**
	 * 변수 초기화
	 * 
	 * 배열 사이즈 정의 및 배추 위치 지정 
	 * 
	 * @param input 베열 사이즈, 입력받을 횟수
	 */
	private void setArray(String input) throws Exception {

		st = new StringTokenizer(input);
		arr = new int[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];

		int K = Integer.parseInt(st.nextToken());

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = 0;
			}
		}

		while (K-- > 0) {
			st = new StringTokenizer(br.readLine());
			arr[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
		}
	}

	
	/**
	 * 깊이 우선 탐색
	 * 
	 * 위치값을 입력받아 상, 하, 좌, 우에 존재하는 배추를 탐색한다. 
	 * 
	 * @param x 탐색할 위치의 x 좌표
	 * @param y 탐색할 위치의 y 좌표
	 */
	private void depthFirstSearch(int x, int y) {

		arr[x][y] = 0;

		for (int i = 0; i < 4; i++) {
			int tx = x + dx[i];
			int ty = y + dy[i];
			if (tx >= 0 && tx < arr.length && ty >= 0 && ty < arr[0].length && arr[tx][ty] == 1) {
				depthFirstSearch(tx, ty);
			}
		}
	}

	/**
	 * 반복 동작
	 * 
	 * 입력받은 테스트 케이스만큼 반복 동작하며, 
	 * 흰 배추 지렁이가 최소 몇 마리 필요한지 구한다.
	 * 
	 * @param T 테스트 케이스
	 */
	private void loopAction(int T) throws Exception {
		while (T-- > 0) {
			cnt = 0;
			setArray(br.readLine());
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					if (arr[i][j] == 1) {
						cnt++;
						depthFirstSearch(i, j);
					}
				}
			}
			bw.write(Integer.toString(cnt));
			bw.newLine();
		}
	}

}
