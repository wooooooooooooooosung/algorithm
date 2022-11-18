import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * Baekjoon Online Judge No.2468 Problem: 안전 영역<br>
 * 
 * Date : 2022-11-18
 * 
 * @see <a href="https://www.acmicpc.net/problem/2468">안전 영역</a>
 * @version 1.0.0
 * @author Woosung Jo
 * @category 그래프 이론, 브루트포스 알고리즘, 그래프 탐색, 너비 우선 탐색, 깊이 우선 탐색
 * 
 * Timeout Limit : 1000 ms
 * Memory Limit  : 128 MB
 */
public class BOJ2468 {

	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st;

	private int[][] arr;
	private boolean[][] chk;
	private int mHeight = 0;
	private int mSafeZone = 0;

	private int[] dx = { -1, 0, 1, 0 };
	private int[] dy = { 0, 1, 0, -1 };
	
	private int cnt = 0;
	private int tx = 0;
	private int ty = 0;

	public static void main(String[] args) throws Exception {
		new BOJ2468();
	}

	public BOJ2468() throws Exception {
		setArray(Integer.parseInt(br.readLine()));
		loopAction();
		
		bw.write(Integer.toString(mSafeZone));
		
		bw.flush();
		br.close();
		bw.close();
	}

	/**
	 * 배열 정의 및 값 지정
	 * 
	 * 배열 사이즈 정의 및 지역별 높이 값 지정 
	 * 
	 * @param n 배열 사이즈
	 */
	private void setArray(int n) throws Exception {
		arr = new int[n][n];
		for (int i = 0; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()) + 1;
				// 비가 전혀오지 않는 경우를 고려하여 1 증가
				if (arr[i][j] > mHeight) mHeight = arr[i][j];
			}
		}
	}

	/**
	 * 안전한 영역의 최대 개수 구하기
	 * 
	 * 가장 높은 지역이 잠길때까지 1씩 증가하며 안전 영역의 최대 개수 구하기 
	 */
	private void loopAction() {
		while (mHeight-- > 0) {
			cnt = 0;
			chk = new boolean[arr.length][arr[0].length];
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					arr[i][j]--;
				}
			}

			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					if (arr[i][j] > 0 && chk[i][j] == false) {
						depthFirstSearch(i, j);
						cnt++;
					}
				}
			}
			// 최대 개수 변경
			if (mSafeZone < cnt) mSafeZone = cnt; 
		}
	}

	/**
	 * 안전한 영역의 최대 개수 구하기
	 * 
	 * 가장 높은 지역이 잠길때까지 1씩 증가하며 안전 영역의 최대 개수 구하기
	 * 
	 *  @param x 아직 잠기지 않은 지역의 좌표 x
	 *  @param y 아직 잠기지 않은 지역의 좌표 y
	 */
	private void depthFirstSearch(int x, int y) {
		chk[x][y] = true;
		
		for (int i = 0; i < 4; i++) {
			tx = x + dx[i];
			ty = y + dy[i];
			if (tx >= 0 && tx < arr.length && ty >= 0 && ty < arr.length) {
				if (chk[tx][ty] == false && arr[tx][ty] > 0) {
					depthFirstSearch(tx, ty);
				}
			}
		}
	}
}
