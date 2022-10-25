import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Baekjoon Online Judge No.2174 Problem: 로봇 시뮬레이션<br>
 * 
 * Date : 2022-10-25
 * 
 * @see <a href="https://www.acmicpc.net/problem/2174">로봇 시뮬레이션</a>
 * @version 1.0.1
 * @author Woosung Jo
 * @category 구현, 시뮬레이션
 * 
 * Timeout Limit : 2000 ms
 * Memory Limit  : 128 MB
 */
public class BOJ2174 {

	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st;

	private int a, b, n, m;
	private int[][] arr;
	private HashMap<Integer, Robot> hash = new HashMap<>();

	public static void main(String[] args) throws Exception {
		new BOJ2174();
	}

	public BOJ2174() throws Exception {
		setArray();
		loopAction();

		bw.flush();
		br.close();
		bw.close();
	}

	/**
	 * 배열 정의, 로봇 정보, 명령 입력받기
	 * 
	 * 배열 사이즈를 정의하고, 명령을 수행할 횟수를 입력받고, 로봇을 정보를 해시맵에 삽입한다.
	 */
	private void setArray() throws Exception {
		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		arr = new int[b + 1][a + 1];
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		for (int i = 1, x, y; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			hash.put(i, new Robot(x, y, st.nextToken().charAt(0)));
			arr[x][y] = i;
		}
	}

	/**
	 * 명령 반복
	 * 
	 * 명령에 따라 로봇이 한칸 앞으로 이동하거나 좌, 우로 회전한다.
	 * 
	 * @return x번 로봇이 벽우 부딪힌 경우 "Robot x crashes into the wall"
	 * @return x번 로봇과 y번 로봇이 서로 부딪힌 경우 "Robot x crashes into robot y"
	 * @return 문제가 없는 경우 "OK"
	 */
	private void loopAction() throws Exception {
		int num, rep, nx, ny;
		char prop, dir;

		Robot robot = null;

		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			num = Integer.parseInt(st.nextToken());
			prop = st.nextToken().charAt(0);
			rep = Integer.parseInt(st.nextToken());

			// 명령을 수행할 로봇을 tmp에 넣음
			robot = hash.get(num);

			if (prop == 'F') {
				// 앞으로 한칸 이동
				nx = robot.x;
				ny = robot.y;
				for (int r = 0; r < rep; r++) {
					if (robot.dir == 'E') ny++;
					else if (robot.dir == 'N') nx++;
					else if (robot.dir == 'W') ny--;
					else nx--;
					
					// 벽에 부딪힌 경우
					if (nx <= 0 || ny <= 0 || nx > b || ny > a) {
						bw.write("Robot "+ num +" crashes into the wall");
						return;
					}
					
					// 한칸 앞으로 이동했는데 이미 다른 로봇이 위치하고 있어 부딪힌 경우
					if (arr[nx][ny] != 0) {
						bw.write("Robot "+ num +" crashes into robot "+ arr[nx][ny]);
						return;
					}
				}
				
				arr[robot.x][robot.y] = 0;
				hash.remove(num);
				hash.put(num, new Robot(nx, ny, robot.dir));
				arr[nx][ny] = num;
			} else if (prop == 'L' || prop == 'R') {
				// 좌 또는 우로 회전
				
				dir = robot.dir;
				for (int r = 0; r < rep; r++) {
					dir = rotateRobot(dir, prop);
				}
				// 현재 로봇을 삭제하고
				hash.remove(num);
				// 응시하고 있는 방향을 수정하여 해시맵에 삽입
				hash.put(num, new Robot(robot.x, robot.y, dir));
			}
		}
		
		bw.write("OK");
	}

	/**
	 * 로봇 회전
	 * 
	 * 명령에 따라 좌 또는 우로 회전한다.
	 * 
	 * @param dir 현재 응시하고 있는 방향
	 * @param prop 회전할 방향
	 * @return 회전한 방향
	 */
	private char rotateRobot(char dir, char prop) {
		if (dir == 'E') return prop == 'L' ? 'N' : 'S';
		else if (dir == 'S') return prop == 'L' ? 'E' : 'W';
		else if (dir == 'W') return prop == 'L' ? 'S' : 'N';
		else return prop == 'L' ? 'W' : 'E';
	}

	/**
	 * 로봇 클래스
	 * 
	 * 현재 위치, 응시하고 있는 방향 정보를 갖고 있다.
	 * 해당 클래스를 해시맵에 삽입한다.
	 */
	class Robot {

		int x, y;
		char dir;

		public Robot(int x, int y, char dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
}
