import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Baekjoon Online Judge No.7576 Problem: 토마토<br>
 * 
 * Date : 2022-12-12
 * 
 * @see <a href="https://www.acmicpc.net/problem/7576">토마토</a>
 * @version 1.0.0
 * @author Woosung Jo
 * @category 그래프 이론, 그래프 탐색, 너비 우선 탐색
 * 
 * Timeout Limit : 1000 ms
 * Memory Limit  : 256 MB
 * 
 * Soltion       : BFS
 */
public class BOJ7576 {

	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st;

	private int[][] arr;

	private int[] dx = { -1, 1, 0, 0 };
	private int[] dy = { 0, 0, -1, 1 };

	private Queue<Node> list = new LinkedList<Node>();

	public static void main(String[] args) throws Exception {
		new BOJ7576();
	}

	private BOJ7576() throws Exception {
		setArray(br.readLine());
		loopAction();
		bw.write(Integer.toString(getResult()));

		bw.flush();
		br.close();
		bw.flush();
	}

	/**
	 * 토마토 위치 및 값 입력
	 * 
	 * 배열 사이즈 정의 및 초기화, 토마토의 위치 및 상태 값을 입력 받는다.
	 */
	private void setArray(String input) throws Exception {
		st = new StringTokenizer(input);

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		arr = new int[m][n];

		for (int i = 0; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < arr[0].length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	/**
	 * 익은 토마토 조회
	 * 
	 * 배열의 모든 위치를 순회하며 익은 토마토를 Queue 리스트에 삽입한다.
	 */
	private void loopAction() {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == 1) {
					list.add(new Node(i, j));
				}
			}
		}

		breadthFirstSearch();
	}

	/**
	 * 너비 우선 탐색
	 * 
	 * BFS 알고리즘을 통해 큐에 입력된 모든 위치의 x, y 값을 ±1씩 탐색한다.
	 * 만약 아직 익지 않은 토마토가 발견되면 현 위치의 값 +1을 해준다.
	 */
	private void breadthFirstSearch() {
		
		Node root;
		
		int tx;
		int ty;

		while (!list.isEmpty()) {
			root = list.poll();

			for (int i = 0; i < 4; i++) {
				tx = root.x + dx[i];
				ty = root.y + dy[i];
				if (tx >= 0 && ty >= 0 && tx < arr.length && ty < arr[0].length) {
					if (arr[tx][ty] == 0 || arr[tx][ty] > arr[root.x][root.y] + 1) {
						arr[tx][ty] = arr[root.x][root.y] + 1;
						list.add(new Node(tx, ty));
					}
				}

			}
		}
	}

	/**
	 * 토마토가 익었는가
	 * 
	 * 토마토가 모두 익을 때까지 며칠이 걸리는지 출력한다.
	 * 저장될 때부터 모든 토마토가 익은 상태라면 0, 
	 * 모든 토마토가 익지 못하는 상황이라면 -1을 출력한다.
	 * 
	 * @return 토마토가 익었는가
	 */
	private int getResult() {
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == 0)
					return -1;
				else if (arr[i][j] > max)
					max = arr[i][j];
			}
		}
		return max - 1;
	}

	class Node {

		private int x;
		private int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
