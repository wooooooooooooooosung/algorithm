import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;


/**
 * Baekjoon Online Judge No.5430 Problem: AC<br>
 * 
 * Date : 2023-10-23
 * 
 * @see <a href="https://www.acmicpc.net/problem/5430">AC</a>
 * @version 1.0.0
 * @author Woosung Jo
 * @category 구현, 자료구조, 문자열, 파싱, 덱
 * 
 * Timeout Limit : 1,000 ms
 * Memory Limit  : 256 MB
 */

public class BOJ5430 {

	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringBuilder sb = new StringBuilder();
	private StringTokenizer st = null;

	Deque<Integer> list = new ArrayDeque<Integer>();
	String line = "";
	boolean isReverse = false;
	boolean isBreak = false;

	public static void main(String[] args) throws Exception {
		new BOJ5430();
	}

	public BOJ5430() throws Exception {
		loopAction();
		System.out.println(sb);
	}

	/**
	 * 명령 입력받기
	 * 
	 * 입력받은 횟수만큼 반복하며 결과를 출력한다.
	 */
	private void loopAction() throws Exception {
		int n = Integer.parseInt(br.readLine()), m;
		String cmd = "";
		
		while (n-- > 0) {
			list.clear();
			isReverse = false;
			isBreak = false;
			cmd = br.readLine();

			// 파싱
			m = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), "[],");

			for (int i = 0; i < m; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}

			for (char c : cmd.toCharArray()) {
				// 배열 순서를 뒤집는다
				if (c == 'R') {
					isReverse = !isReverse;
					continue;
					
				} 
				// 배열 순서의 맨 앞 원소를 버린다.
				else if (c == 'D') {
					if (list.isEmpty()) {
						isBreak = true;
						break;
					}
					// 뒤집힌 상태라면 덱의 맨 뒤 원소를 버린다.
					if (isReverse) list.pollLast();
					// 뒤집히지 않은 상태라면 덱의 맨 앞 원소를 버린다.
					else list.pollFirst();
				}
			}
			
			doWrite();
		}
	}
	
	/**
	 * 명령 입력받기
	 * 
	 * 입력받은 횟수만큼 반복하며 결과를 출력한다.
	 */
	private void doWrite() throws Exception {
		if (isBreak) {
			// 브레이크가 걸렸다면 error 출력
			sb.append("error");
		} else {
			sb.append("[");
			if (!list.isEmpty()) {
				// 뒤집힌 상태라면 덱의 맨 뒤 원소부터 출력한다.
				if (isReverse) {
					sb.append(list.pollLast());
					while (!list.isEmpty()) {
						sb.append("," + list.pollLast());
					}
				} 
				// 뒤집히지 않은 상태라면 덱의 맨 앞 원소부터 출력한다.
				else {
					sb.append(list.pollFirst());
					while (!list.isEmpty()) {
						sb.append("," + list.pollFirst());
					}
				}
			}
			sb.append(line).append("]");
		}
		sb.append("\n");
	}
}
