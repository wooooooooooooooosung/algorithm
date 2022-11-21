import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * Baekjoon Online Judge No.3184 Problem: 양<br>
 * 
 * Date : 2022-11-21
 * 
 * @see <a href="https://www.acmicpc.net/problem/3184">양</a>
 * @version 1.0.0
 * @author Woosung Jo
 * @category 그래프 이론, 그래프 탐색, 너비 우선 탐색, 깊이 우선 탐색
 * 
 * Timeout Limit : 1000 ms 
 * Memory Limit : 128 MB
 */
public class BOJ3184 {

	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st;

	private char[][] arr;
	private boolean[][] chk;

	private int[] dx = { -1, 0, 1, 0 };
	private int[] dy = { 0, 1, 0, -1 };
	
	private int tx = 0;
	private int ty = 0;
	
	private int sCount;
	private int wCount;
	
	private int sheep = 0;
	private int wolf = 0;

	public static void main(String[] args) throws Exception {
		new BOJ3184();
	}

	public BOJ3184() throws Exception {
		setArray();
		loopAction();
		
		bw.write(sheep + " " + wolf);
			
		bw.flush();
		br.close();
		bw.close();
	}

	/**
	 * 배열 정의 및 값 지정
	 * 
	 * 배열 사이즈 정의 및 요소별 값 지정
	 * '#' - 울타리, '.' = 필드, 'v' = 늑대, 'o' = 양
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
	 * 살아남는 양과 늑대의 수 구하기
	 * 
	 * 모든 필드를 탐색하며 살아남는 양과 늑대의 수를 구한다.
	 * 양 > 늑대의 경우, 늑대는 탈출한다.
	 * 늑대 >= 양일 경우, 양은 잡아먹힌다.
	 */
	private void loopAction() {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] != '#' && chk[i][j] == false) {
					sCount = 0;
					wCount = 0;
					depthFirstSearch(i, j);
					if (sCount > wCount) sheep += sCount;
					else wolf += wCount;
				}
			}
		}
	}

	/**
	 * 깊이 우선 탐색
	 * 
	 * 탐색하지 않은 필드에 양이 있는지, 늑대가 있는지, 비어있는지 파악한다.
	 * 
	 * @param x 탐색할 위치의 x값
	 * @param y 탐색할 위치의 y값
	 */
	private void depthFirstSearch(int x, int y) {
		chk[x][y] = true;
		if (arr[x][y] == 'o') sCount++;
		else if (arr[x][y] == 'v') wCount++;
		
		for (int i = 0; i < 4; i++) {
			tx = x + dx[i];
			ty = y + dy[i];
			
			if (tx >= 0 && ty >= 0 && tx < arr.length && ty < arr[0].length) { 
				if (arr[tx][ty] != '#' && chk[tx][ty] == false) {
					depthFirstSearch(tx, ty);
				}
			}
		}
	}
}
