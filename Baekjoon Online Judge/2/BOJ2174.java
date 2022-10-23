import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private int[][] arr;

	private Robot[] robot;
	private Info[] info;

	public static void main(String[] args) throws Exception {
		new Main();
	}

	public Main() throws Exception {
		setArray();
		bw.write(loopAction());

		bw.flush();
		br.close();
		bw.close();
	}

	private void setArray() throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		arr = new int[x][y];

		st = new StringTokenizer(br.readLine());
		robot = new Robot[Integer.parseInt(st.nextToken()) + 1];
		// 더미로봇
		robot[0] = new Robot(0, 0, '0');

		info = new Info[Integer.parseInt(st.nextToken())];

		for (int i = 1; i < robot.length; i++) {
			st = new StringTokenizer(br.readLine());
			robot[i] = new Robot(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
					st.nextToken().charAt(0));
			arr[robot[i].x][robot[i].y] = i;
		}

		for (int i = 0; i < info.length; i++) {
			st = new StringTokenizer(br.readLine());
			info[i] = new Info(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0),
					Integer.parseInt(st.nextToken()));
		}

	}

	private String loopAction() {
		int no, tx, ty, loop;
		char cmnd, direction;
		for (int i = 0; i < info.length; i++) {
			no = info[i].robotNo;
			loop = info[i].loop;
			cmnd = info[i].cmnd;
			for (int j = 0; j < loop; j++) {

				// 해당 로봇이 어느 방향을 보고 있는지 구한다.
				direction = robot[no].direction;

				// 직진인 경우
				if (cmnd == 'F') {
					tx = robot[no].x;
					ty = robot[no].y;
					straightRobot(no, direction);

					// 벽에 박은 경우
					if (robot[no].x < 0 || robot[no].y < 0 || robot[no].x >= arr.length || robot[no].y >= arr[0].length)
						return "Robot " + no + " crashes into the wall";
					// 다른 로봇에 박은 경우
					else if (arr[robot[no].x][robot[no].y] != 0)
						return "Robot " + no + " crashes into robot " + arr[robot[no].x][robot[no].y];

					// 안박은 경우
					arr[tx][ty] = 0;
					arr[robot[no].x][robot[no].y] = no;

				}
				// 방향 회전인 경우
				else if (cmnd == 'L' || cmnd == 'R') {
					rotateRobot(no, cmnd, direction);
				}
			}
		}

		return "OK";
	}

	private void straightRobot(int no, char direction) {
		if (direction == 'N')
			robot[no].y++;
		else if (direction == 'W')
			robot[no].x--;
		else if (direction == 'S')
			robot[no].y--;
		else if (direction == 'E')
			robot[no].x++;
	}

	private void rotateRobot(int no, char cmnd, char direction) {
		if (cmnd == 'L') {
			if (direction == 'N')
				robot[no].direction = 'W';
			else if (direction == 'W')
				robot[no].direction = 'S';
			else if (direction == 'S')
				robot[no].direction = 'E';
			else if (direction == 'E')
				robot[no].direction = 'N';
		} else if (cmnd == 'R') {
			if (direction == 'N')
				robot[no].direction = 'W';
			else if (direction == 'W')
				robot[no].direction = 'S';
			else if (direction == 'S')
				robot[no].direction = 'E';
			else if (direction == 'E')
				robot[no].direction = 'N';
		}
	}

//	private void print() {
//		for (int i = 0; i < arr.length; i++) {
//			for (int j = 0; j < arr[i].length; j++) {
//				System.out.print(arr[i][j]);
//			}
//			System.out.println();
//		}
//		System.out.println("--------------------------------");
//	}

	class Robot {
		private int x, y;
		private char direction;

		public Robot(int x, int y, char direction) {
			this.x = x;
			this.y = y;
			this.direction = direction;
		}
	}

	class Info {
		private int robotNo, loop;
		private char cmnd;

		public Info(int robotNo, char cmnd, int loop) {
			this.robotNo = robotNo;
			this.cmnd = cmnd;
			this.loop = loop;
		}
	}
}
