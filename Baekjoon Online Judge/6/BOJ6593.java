import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Baekjoon Online Judge No.6593 Problem: 상범 빌딩<br>
 * 
 * Date : 2022-12-25
 * 
 * @see <a href="https://www.acmicpc.net/problem/6593">상범 빌딩</a>
 * @version 1.0.0
 * @author Woosung Jo
 * @category 그래프 이론, 그래프 탐색, 너비 우선 탐색
 * 
 * Timeout Limit : 1000 ms
 * Memory Limit  : 128 MB
 * 
 * Solutiomn : BFS 
 */
public class BOJ6593 {

	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st;

	private char[][][] arr;
	private int[][][] cnt;
	
	private Queue<Node> list = new LinkedList<Node>();
	private Node root, escape;
	
	private int[] dz = { -1, 1, 0, 0, 0, 0 };
	private int[] dx = { 0, 0, -1, 1, 0, 0 };
	private int[] dy = { 0, 0, 0, 0, -1, 1 };
	private int tz, tx, ty;

	public static void main(String[] args) throws Exception {
		new BOJ6593();
	}

	public BOJ6593() throws Exception {
		int z, x, y;
		while (true) {
			st = new StringTokenizer(br.readLine());
			z = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			if (z == 0 && x == 0 && y == 0) break;
			else {
				setArray(z, x, y);
				loopAction();
				bw.write(getResult());
			}
		}
		
		bw.flush();
		br.close();
		bw.close();
	}
	
	/**
	 * 배열 초기화
	 * 
	 * 배열 사이즈 정의 및 요소별 값을 저장한다.
	 * 
	 * 금으로 막혀있어 지나갈 수 없는 칸은 '#', 
	 * 비어있는 칸은 '.', 
	 * 시작 지점은 'S', 
	 * 탈출할 수 있는 출구는 'E'로 표현된다.
	 * 
	 * @param z 정의할 배열의 최대 z값
	 * @param x 정의할 배열의 최대 x값
	 * @param y 정의할 배열의 최대 y값
	 */
	private void setArray(int z, int x, int y) throws Exception {
		arr = new char[z][x][y];
		cnt = new int[z][x][y];
		String input;
		
		for (int i = 0; i < z; i++) {
			for (int j = 0; j < x; j++) {
				input = br.readLine();
				for (int k = 0; k < y; k++) {
					arr[i][j][k] = input.charAt(k);
					cnt[i][j][k] = -1;
					
					if (arr[i][j][k] == 'E') {
						escape = new Node(i, j, k);
					}
				}
			}
			br.readLine();
		}
	}
	
	/**
	 * 배열 탐색
	 * 
	 * 배열의 모든 값을 탐색하며 시작점 'S'일 경우, BFS 알고리즘을 실행한다.
	 */
	private void loopAction() {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				for (int k = 0; k < arr[0][0].length; k++) {
					if (arr[i][j][k] == 'S') {
						breathFirstSearch(i, j, k);
					}
				}
			}
		}
	}
	
	/**
	 * 너비 우선 탐색
	 * 
	 * Queue에 있는 모든 Node를 탐색하며, 
	 * 해당 Node에서의 	동, 서, 남, 북, 상, 하에 1칸씩 인접한 값을 탐색한다.
	 * 만약 탐색한 값이 현재 Node의 cnt값 + 1보다 크다면, 
	 * 새로운 값을 cnt 변수에 저장하고 Queue에 Node를 추가한다.
	 */
	private void breathFirstSearch(int z, int x, int y) { 
		list.add(new Node(z, x, y));
		cnt[z][x][y] = 0;
		
		while(!list.isEmpty()) {
			root = list.poll();
			for (int i = 0; i < 6; i++) {
				tz = dz[i] + root.z;
				tx = dx[i] + root.x;
				ty = dy[i] + root.y;
				if (tz >= 0 && tx >= 0 && ty >= 0 && tz < arr.length && tx < arr[0].length && ty < arr[0][0].length) {
					if (arr[tz][tx][ty] == '.' || arr[tz][tx][ty] == 'E' || arr[tz][tx][ty] == 'S') {
						if (cnt[tz][tx][ty] == -1 || cnt[tz][tx][ty] > cnt[root.z][root.x][root.y] + 1) {
							cnt[tz][tx][ty] = cnt[root.z][root.x][root.y] + 1;
							list.add(new Node(tz, tx, ty));
						}
					}
				}
			}
		}
	}
	
	/**
	 * 탈출 여부 출력
	 * 
	 * 상범 빌딩에서 탈출하는 최단 시간을 출력한다.
	 * 
	 * @return 탈출구 'E'의 cnt 값(탈출 최단 시간)을 출력한다.
	 * @return 탈출구 'E'의 cnt 값이 -1이라면, 탈출하지 못한것이기 때문에 "Trapped!"를 출력한다.
	 */
	private String getResult() {
		if (cnt[escape.z][escape.x][escape.y] == -1) return "Trapped!\n";
		else return "Escaped in " + cnt[escape.z][escape.x][escape.y] + " minute(s).\n";
	}
	
	private class Node {
		int z, x, y;
		
		public Node(int z, int x, int y) {
			this.z = z;
			this.x = x;
			this.y = y;
		}
	}
}
