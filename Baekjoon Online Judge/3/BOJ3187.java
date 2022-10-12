import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * Baekjoon Online Judge No.1012 Problem: 양치기 꿍<br>
 * 
 * Date : 2022-10-12
 * 
 * @see <a href="https://www.acmicpc.net/problem/3187">양치기 꿍</a>
 * @version 1.0.0
 * @author Woosung Jo
 * @category 그래프 이론, 그래프 탐색, 너비 우선 탐색, 깊이 우선 탐색
 * 
 * Timeout Limit : 1000 ms
 * Memory Limit  : 128 MB
 */
public class BOJ3187 {

	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private int arr[][];
	
	private int v = 0; // 늑대
	private int k = 0; // 양
	
	private int tv = 0;
	private int tk = 0;
	
	public static void main(String[] args) throws Exception {
		new BOJ3187();
	}

	public BOJ3187() throws Exception {
		setArray(br.readLine());
		loopAction();
		
		bw.write(tk + " " + tv);
		
		bw.flush();
		br.close();
		bw.close();
	}

	/**
	 * 변수 초기화
	 * 
	 * 배열 사이즈 정의 및 요소 초기화
	 * 	빈 공간: 0
	 * 	울타리: 1
	 * 	늑대: 2
	 * 	양: 3
	 * 
	 * @param size 베열 사이즈
	 */
	private void setArray(String size) throws Exception {
		StringTokenizer st = new StringTokenizer(size);
		arr = new int[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];

		for (int i = 0; i < arr.length; i++) {
			String input = br.readLine();
			for (int j = 0; j < input.length(); j++) {
				switch (input.charAt(j)) {
				case '.': arr[i][j] = 0; break;
				case '#': arr[i][j] = 1; break;
				case 'v': arr[i][j] = 2; break;
				case 'k': arr[i][j] = 3; break;
				}
			}
		}
	}
	
	/**
	 * 반복 동작
	 * 
	 * 배열을 전체 탐색하며 몇 마리의 양과 늑대가 살아남는지 출력한다. 
	 */
	private void loopAction() {
		
		int n = arr.length - 1;
		int m = arr[0].length - 1;
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] != 1) {
					depthFirstSearch(i, j, n, m);
					
					if (v >= k) tv += v;
					else tk += k;
					
					v = 0;
					k = 0;
				}
			}
		}
	}
	
	/**
	 * 깊이 우선 탐색
	 * 
	 * 위치값을 입력받아 상, 하, 좌, 우의 값을 확인하고 현재 함수를 재호출한다.
	 * 
	 * @param x 탐색할 위치의 x 좌표
	 * @param y 탐색할 위치의 y 좌표
	 * @param n x축 최대 값
	 * @param m y축 최대 값
	 */
	private void depthFirstSearch(int x, int y, int n, int m) {
		if (arr[x][y] == 1) return;
		else if (arr[x][y] == 2) v++;
		else if (arr[x][y] == 3) k++;
		
		arr[x][y] = 1;
		
		if (x > 0) depthFirstSearch(x - 1, y, n, m);
		if (y < m) depthFirstSearch(x, y + 1, n, m);
		if (x < n) depthFirstSearch(x + 1, y, n, m);
		if (y > 0) depthFirstSearch(x, y - 1, n, m);
	}
}
