import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * Baekjoon Online Judge No.15970 Problem: 화살표 그리기<br>
 * 
 * Date : 2022-10-13
 * 
 * @see <a href="https://www.acmicpc.net/problem/15970">화살표 그리기</a>
 * @version 1.0.0
 * @author Woosung Jo
 * @category 브루트포스 알고리즘, 정렬
 * 
 * Timeout Limit : 2000 ms
 * Memory Limit  : 512 MB
 */
public class BOJ15970 {

	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st;

	private ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();

	public static void main(String[] args) throws Exception {
		new BOJ15970();
	}

	private BOJ15970() throws Exception {
		setArrayList(Integer.parseInt(br.readLine()));
		bw.write(Integer.toString(getLengthTotal()));

		bw.flush();
		br.close();
		bw.close();
	}

	/**
	 * 리스트 초기화
	 * 
	 * 리스트를 정의하고 점의 좌표 및 색깔을 입력받는다.
	 * 
	 * @param n 점의 개수
	 */
	private void setArrayList(int n) throws Exception {
		for (int i = 0; i <= n; i++)
			list.add(new ArrayList<Integer>());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int loc = Integer.parseInt(st.nextToken());
			list.get(Integer.parseInt(st.nextToken())).add(loc);
		}
		for (int i = 0; i <= n; i++)
			Collections.sort(list.get(i));
	}

	/**
	 * 화살표들의 길이 합 구하기
	 * 
	 * 모든 점에서 시작하는 화살표들의 길이 값을 출력한다.
	 * 
	 * @return 화살표들의 길이 합
	 */
	private int getLengthTotal() {
		int sum = 0;
		for (int i = 0, n = list.size(); i < n; i++) {
			for (int j = 0; j < list.get(i).size(); j++) {
				if (j == 0) sum += list.get(i).get(j + 1) - list.get(i).get(j);
				else if (j == list.get(i).size() - 1) sum += list.get(i).get(j) - list.get(i).get(j - 1);
				else sum += Math.min(list.get(i).get(j + 1) - list.get(i).get(j), list.get(i).get(j) - list.get(i).get(j - 1));
			}
		}
		return sum;
	}

}
