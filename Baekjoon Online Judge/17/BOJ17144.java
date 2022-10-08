import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * Baekjoon Online Judge No.17144 Problem: 미세먼지 안녕!<br>
 * 
 * Date : 2022-10-08
 * 
 * @see <a href="https://www.acmicpc.net/problem/17144">미세먼지 안녕!</a>
 * @version 1.0.0
 * @author Woosung Jo
 * @category 구현, 시뮬레이션
 * 
 * Timeout Limit : 1000 ms
 * Memory Limit  : 512 MB
 */
public class BOJ17144 {

	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st;

	private int[][] arr;

	private int t;
	private int r;

	private int[] x = { -1, 0, 0, 1 };
	private int[] y = { 0, -1, 1, 0 };

	public static void main(String[] args) throws Exception {
		new BOJ17144();
	}

	public BOJ17144() throws Exception {
		setArray(br.readLine());
		while (t-- > 0) {
			spreadDust();
			rotateDust();
		}

		bw.write(Integer.toString(getDust()));

		bw.flush();
		br.close();
		bw.close();
	}

	/**
	 * 변수 초기화
	 * 
	 * 배열 사이즈 정의 및 요소별 값 저장
	 * 
	 * @param input 베열 사이즈, 종료 시간에 대한 입력값
	 */
	private void setArray(String input) throws Exception {
		st = new StringTokenizer(input);
		arr = new int[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];
		t = Integer.parseInt(st.nextToken());

		for (int i = 0; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
			r = arr[i][0] == -1 ? i - 1 : r;
		}
	}
	
	/**
	 * 미세먼지 확산
	 * 
	 * 미세먼지가 있는 모든 칸에서 동시에 확산된다.
	 * 인접한 네 방향(상, 하, 좌, 우)으로 방향 당 a[r, c] / 5 가 확산된다.
	 */
	private void spreadDust() {
		int[][] tmp = new int[arr.length][arr[0].length];

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] == -1 || arr[i][j] == 0) {
					continue;
				}
				int cnt = 0;
				int val = arr[i][j] / 5;

				for (int r = 0; r < x.length; r++) {
					int tx = i + x[r];
					int ty = j + y[r];
					if (tx >= 0 && ty >= 0 && tx < arr.length && ty < arr[i].length && arr[tx][ty] != -1) {
						tmp[tx][ty] += val;
						cnt++;
					}
				}
				tmp[i][j] += arr[i][j] - val * cnt;
			}
		}
		arr = tmp;
		arr[r][0] = arr[r + 1][0] = -1;
	}

	/**
	 * 공기 청정기 작동
	 * 
	 * 공기 청정기의 바람으로 인해 미세먼지 위치가 한 칸씩 이동한다.
	 * 공기 청정기의 위치를 기준으로 
	 * 		위쪽 바람은 반시계 방향으로, 
	 * 		아래쪽 바람은 시계 방향으로 순환한다.
	 */
	private void rotateDust() {
		for (int i = r - 1; i > 0; i--) arr[i][0] = arr[i - 1][0];
		for (int i = 0; i < arr[0].length - 1; i++) arr[0][i] = arr[0][i + 1];
		for (int i = 0, lc = arr[0].length - 1; i < r; i++) arr[i][lc] = arr[i + 1][lc];
		for (int i = arr[0].length - 1; i > 1; i--) arr[r][i] = arr[r][i - 1];
		
		for (int i = r + 2; i < arr.length - 1; i++) arr[i][0] = arr[i + 1][0];
		for (int i = 0, lr = arr.length - 1; i < arr[0].length - 1; i++) arr[lr][i] = arr[lr][i + 1];
		for (int i = arr.length - 1, lc = arr[0].length - 1; i > r + 1; i--) arr[i][lc] = arr[i - 1][lc];
		for (int i = arr[0].length - 1; i > 1; i--) arr[r + 1][i] = arr[r + 1][i - 1];
		
		arr[r][1] = arr[r + 1][1] = 0;
	}
	
	/**
	 * 남은 먼지의 합계 리턴
	 * 
	 * 공기 청정기의 작동이 끝난 후, 남은 먼지의 합계를 리턴한다.
	 * 
	 * @return 남은 먼지의 합계
	 */
	private int getDust() {
		int sum = 2;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				sum += arr[i][j];
			}
		}
		return sum;
	}
}
