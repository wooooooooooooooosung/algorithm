import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * Baekjoon Online Judge No.11123 Problem: 양 한마리... 양 두마리...<br>
 * 
 * Date : 2022-11-21
 * 
 * @see <a href="https://www.acmicpc.net/problem/11123">양 한마리... 양 두마리...</a>
 * @version 1.0.0
 * @author Woosung Jo
 * @category 그래프 이론, 그래프 탐색, 너비 우선 탐색, 깊이 우선 탐색
 * 
 * Timeout Limit : 1000 ms 
 * Memory Limit : 256 MB
 */
public class BOJ11123 {

	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st;

	private char[][] arr;
	private boolean[][] chk;

	private int[] dx = { -1, 0, 1, 0 };
	private int[] dy = { 0, 1, 0, -1 };
	
	private int tx = 0;
	private int ty = 0;

	public static void main(String[] args) throws Exception {
		new BOJ11123();
	}

	public BOJ11123() throws Exception {
		int n = Integer.parseInt(br.readLine());
		while (n-- > 0) {
			setArray();
			bw.write(Integer.toString(getSheepSwarm()));
			bw.newLine();
		}
		bw.flush();
		br.close();
		bw.close();
	}

	/**
	 * 배열 정의 및 값 지정
	 * 
	 * 배열 사이즈 정의 및 요소별 값 지정
	 * '#' - 양, '.' = 풀
	 */
	private void setArray() throws Exception {
		st = new StringTokenizer(br.readLine());
		arr = new char[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];
		chk = new boolean[arr.length][arr[0].length];

		String tmp = null;
		for (int i = 0; i < arr.length; i++) {
			tmp = br.readLine();
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = tmp.charAt(j);
			}
		}
	}

	/**
	 * 양 무리 수 구하기
	 * 
	 * 입력값만큼 반복하며 양의 무리가 총 몇개인지 구한다
	 * 
	 * @return 양 무리 수
	 */
	private int getSheepSwarm() {
		int cnt = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] == '#' && chk[i][j] == false) {
					depthFirstSearch(i, j);
					cnt++;
				}
			}
		}
		return cnt;
	}

	/**
	 * 깊이 우선 탐색
	 * 
	 * 탐색하지 않은 땅에 양이 있다면 해당 위치로 이동하여 근처 땅을 탐색한다.
	 * 
	 * @param x 탐색할 위치의 x값
	 * @param y 탐색할 위치의 y값
	 */
	private void depthFirstSearch(int x, int y) {
		chk[x][y] = true;
		
		for (int i = 0; i < 4; i++) {
			tx = x + dx[i];
			ty = y + dy[i];
			
			if (tx >= 0 && ty >= 0 && tx < arr.length && ty < arr[0].length) { 
				if (arr[tx][ty] == '#' && chk[tx][ty] == false) {
					depthFirstSearch(tx, ty);
				}
			}
		}
	}
}
