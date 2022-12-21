import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Baekjoon Online Judge No.2589 Problem: 보물섬<br>
 * 
 * Date : 2022-12-21
 * 
 * @see <a href="https://www.acmicpc.net/problem/2589">보물섬</a>
 * @version 1.0.0
 * @author Woosung Jo
 * @category 그래프 이론, 브루트포스 알고리즘, 그래프 탐색, 너비 우선 탐색
 * 
 * Timeout Limit : 1000 ms
 * Memory Limit  : 512 MB
 * 
 * Solution      : BFS, Brute-Force
 */
public class BOJ2589 {

	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st;
	
	private char arr[][];
	private int chk[][];
	
	private int dx[] = { -1, 0, 1, 0 };
	private int dy[] = { 0, -1, 0, 1 };
	
	private Queue<Node> list = new LinkedList<Node>();
	private Node root;
	private int tx, ty;
	
	private int max = 0;

	public static void main(String[] args) throws Exception {
		new BOJ2589();
	}
	
	public BOJ2589() throws Exception {
		setArray();
		loopAction();
		
		bw.write(Integer.toString(max));
		
		bw.flush();
		br.close();
		bw.close();
	}
	
	/**
	 * 보물지도 입력
	 * 
	 * 배열 초기화 및 보물지도 입력
	 * L : 육지
	 * W : 바다
	 */
	private void setArray() throws Exception {
		st = new StringTokenizer(br.readLine());
		String input = null;
		
		arr = new char[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];
		for (int i = 0; i < arr.length; i++) {
			input = br.readLine();
			for (int j = 0; j < arr[0].length; j++) {
				arr[i][j] = input.charAt(j);
			}
		}
	}
	
	/**
	 * 모든 육지 탐색
	 * 
	 * 배열에 담긴 값이 육지를 뜻하는 'L'이라면 
	 * 해당 위치를 시작점으로 하여 BFS 메소드를 실행한다.
	 */
	private void loopAction() {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == 'L') {
					breathFirstSearch(i, j);
				}
			}
		}
	}
	
	/**
	 * 최단거리 계산
	 * 
	 * 전달받은 x, y값을 시작점으로 하여 연결된 모든 육지의 최단거리를 구한다.
	 * 
	 * @param x 탐색할 위치의 x 좌표
	 * @param y 탐색할 위치의 y 좌표 
	 */
	private void breathFirstSearch(int x, int y) {
		list.add(new Node(x, y));
		chk = new int[arr.length][arr[0].length];
		for (int i = 0; i < chk.length; i++) {
			for (int j = 0; j < chk[0].length; j++) {
				chk[i][j] = -1;
			}
		}
		chk[x][y] = 0;
		
		while(!list.isEmpty()) {
			root = list.poll();
			for (int i = 0; i < 4; i++) {
				tx = root.x + dx[i];
				ty = root.y + dy[i];
				if (tx >= 0 && ty >= 0 && tx < arr.length && ty < arr[0].length) {
					if (arr[tx][ty] == 'L' && chk[tx][ty] == -1) {
						chk[tx][ty] = chk[root.x][root.y] + 1;
						list.add(new Node(tx, ty));
						max = Math.max(max, chk[tx][ty]);
					}
				}
			}
		}
	}
	
	private class Node {
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
