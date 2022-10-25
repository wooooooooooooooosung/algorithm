import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Baekjoon Online Judge No.10845 Problem: 큐<br>
 * 
 * Date : 2022-10-25
 * 
 * @see <a href="https://www.acmicpc.net/problem/10845">큐</a>
 * @version 1.0.0
 * @author Woosung Jo
 * @category 자료구조,큐
 * 
 * Timeout Limit : 500 ms
 * Memory Limit  : 256 MB
 */
public class BOJ10845 {

	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st;

	/*
	 * Java에서의 Queue는 add(push)와 poll(pop)이라는 함수를 사용한다.
	 * (타 언어들은 Java처럼 Stack, Queue 등 자료구조별 함수 명이 상이하지 않다.) 
	 * 
	 * queue.add() 큐 리스트 마지막에 데이터 삽입 
	 * 		- 값 추가 시 true 반환
	 * 		- 큐 리스트가 꽉 찬 경우, IllegalStateException 에러 발생
	 * queue.offer() 큐 리스트 마지막에 데이터 삽입
	 * 		- 값 추가 시 true 반환
	 * 		- 값 추가 실패 시 false 반환 
	 * 
	 * queue.remove() 큐 리스트 맨 앞(front) 값을 반환하고 삭제
	 * 		- 큐가 비어있는 경우 NoSuchElementException 에러 발생
	 * queue.poll() 큐 리스트 맨 앞(front) 값을 반환하고 삭제
	 * 		- 큐가 비어있는 경우 null 반환
	 * queue.clear() 큐 지우기
	 * 
	 * queue.element() 큐 리스트 맨 앞(front) 값 반환
	 * 		- 큐가 비어있는 경우 NoSuchElementException 에러 발생
	 * queue.peek() 큐 리스트 맨 앞(front) 값 반환
	 * 		- 큐가 비어있는 경우 null 반환
	 * 
	 * 
	 * Queue에서 데이터를 추가, 삭제, 검색할 때 제공되는 메서드들의 차이는
	 * 문제 상황에서 에러를 발생시키느냐(add, remove, element)
	 * 아니면 null 혹은 false를 반환(offer, poll, peek) 하는가입니다.
	 * 
	 */
	
	private Queue<Integer> q = new LinkedList<Integer>();
	private int lastValue = -1;

	public static void main(String[] args) throws Exception {
		new BOJ10845();
	}

	public BOJ10845() throws Exception {
		loopAction(Integer.parseInt(br.readLine()));

		bw.flush();
		br.close();
		bw.close();
	}

	/**
	 * 반복 횟수 지정
	 * 
	 * 명령에 따른 함수(push, pop, size, empty, front, back)를 실행한다.
	 * 
	 * @param loop 반복 횟수
	 */
	private void loopAction(int loop) throws Exception {
		String prop = null;
		while (loop-- > 0) {
			st = new StringTokenizer(br.readLine());
			prop = st.nextToken();

			if (prop.equals("push")) pushQueue(Integer.parseInt(st.nextToken()));
			else if (prop.equals("pop")) bw.write(Integer.toString(popQueue()));
			else if (prop.equals("size")) bw.write(Integer.toString(getQueueSize()));
			else if (prop.equals("empty")) bw.write(Integer.toString(isEmpty()));
			else if (prop.equals("front")) bw.write(Integer.toString(getFrontValue()));
			else if (prop.equals("back")) bw.write(Integer.toString(getBackValue()));

			if (!prop.equals("push")) bw.newLine();
		}
	}

	/**
	 * 입력 값 삽입
	 * 
	 * 파라미터로 전달받은 값을 큐 리스트의 마지막에 삽입한다. 
	 * 
	 * @param x 큐에 삽입할 값
	 */
	private void pushQueue(int x) {
		q.add(x);
		lastValue = x;
	}

	/**
	 * 값 꺼내기
	 * 
	 * 큐 리스트의 가장 앞에 있는 값을 꺼내어 리턴한다.
	 * 만약, 큐가 비어있다면 -1을, 
   * 아니라면 가장 앞의 값을 리턴하고 큐 리스트에서 해당 값을 삭제한다.
	 * 
	 * @return -1 또는 큐 리스트의 가장 앞의 값
	 */
	private int popQueue() {
		return q.isEmpty() ? -1 : q.poll();
	}

	/**
	 * 큐 리스트 요소 개수 조회
	 * 
	 * 큐 리스트의 요소 개수를 리턴한다.
	 * 
	 * @return 큐 리스트 요소 개수
	 */
	private int getQueueSize() {
		return q.size();
	}

	/**
	 * 큐 리스트 가장 앞의 값 구하기
	 * 
	 * 큐 리스트의 가장 앞의 값을 리턴한다.
	 * 큐 리스트가 비어있다면 -1을 리턴한다.
	 * 
	 * @return -1 또는, 큐 리스트 가장 앞의 값
	 */
	private int getFrontValue() {
		return q.isEmpty() ? -1 : q.peek();
	}

	/**
	 * 큐 리스트 empty 여부 확인
	 * 
	 * 큐 리스트가 비어있다면 1, 
   * 아니라면 0을 리턴한다.
	 * 
	 * @return empty 여부
	 */
	private int isEmpty() {
		return q.isEmpty() ? 1 : 0;
	}

	/**
	 * 큐 리스트 가장 마지막 값 구하기
	 * 
	 * 큐 리스트의 가장 마지막 값을 리턴한다.
	 * 큐 리스트가 비어있다면 -1을 리턴한다.
	 * 
	 * @return -1 또는, 큐 리스트 가장 마지막 값
	 */
	private int getBackValue() {
		return q.isEmpty() ? -1 : lastValue;
	}
}
