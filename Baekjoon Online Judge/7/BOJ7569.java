import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Baekjoon Online Judge No.7569 Problem: 토마토<br>
 * 
 * Date : 2022-12-09
 * 
 * @see <a href="https://www.acmicpc.net/problem/7569">토마토</a>
 * @version 1.0.0
 * @author Woosung Jo
 * @category 그래프 이론, 그래프 탐색, 너비 우선 탐색
 * 
 * Timeout Limit : 1000 ms
 * Memory Limit  : 256 MB
 * 
 * Soltion       : BFS
 */

public class BOJ7569 {

	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;

	private int dx[] = { -1, 0, 1, 0, 0, 0 };
	private int dy[] = { 0, 1, 0, -1, 0, 0 };
	private int dz[] = { 0, 0, 0, 0, 1, -1 };
	private int m, n, h;
	private int arr[][][];
	
	private Queue<Node> list = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		new BOJ7569();
	}

	public BOJ7569() throws Exception {
		setArray();
		breathFirstSearch();
		bw.write(Integer.toString(getResult()));
		
		bw.flush();
		br.close();
		bw.close();
	}

	/**
	 * 토마토 위치 및 값 입력
	 * 
	 * 배열 사이즈 정의 및 초기화, 토마토의 위치 및 상태 값을 입력 받는다.
	 */
	private void setArray() throws Exception {
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		arr = new int[h + 1][n + 1][m + 1];

		for (int i = 1; i <= h; i++) {
			for (int j = 1; j <= n; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 1; k <= m; k++) {
					arr[i][j][k] = Integer.parseInt(st.nextToken());
					if (arr[i][j][k] == 1) list.add(new Node(i, j, k));
				}
			}
		}
	}

	/**
	 * 너비 우선 탐색
	 * 
	 * 큐에 입력된 모든 위치의 x, y, z 값을  ±1씩 탐색한다.
	 * 만약 아직 익지 않은 토마토가 발견되면 현 위치의 값 +1을 해준다.
	 */
	private void breathFirstSearch() {
		
		int tx;
		int ty;
		int tz;
		Node root = null;
		
		while (!list.isEmpty()) {
			root = list.poll();

			for (int i = 0; i < 6; i++) {
				tz = root.z + dz[i];
				tx = root.x + dx[i];
				ty = root.y + dy[i];
				
				if (tz >= 1 && tx >= 1 && ty >= 1 && tz <= h && tx <= n && ty <= m) {
					if (arr[tz][tx][ty] == 0) {
						list.add(new Node(tz, tx, ty));
						arr[tz][tx][ty] = arr[root.z][root.x][root.y] + 1;						
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
	 * 모든 토마토가 익지 못하는 상황이라면 -1 을 출력한다. 
	 * 
	 * @return 토마토가 익었는지
	 */
	private int getResult() {
		int result = Integer.MIN_VALUE;

		for (int i = 1; i <= h; i++) {
			for (int j = 1; j <= n; j++) {
				for (int k = 1; k <= m; k++) {
					if (arr[i][j][k] == 0)
						return -1;
					result = Math.max(result, arr[i][j][k]);
				}
			}
		}
		if (result == 1) return 0;
		else return result - 1;
	}
}

class Node {
	int z;
	int x;
	int y;

	public Node(int z, int x, int y) {
		this.z = z;
		this.x = x;
		this.y = y;
	}
}
