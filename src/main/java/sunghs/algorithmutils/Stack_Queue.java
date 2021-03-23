package sunghs.algorithmutils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class Stack_Queue {

    @Test
    public void stack() {
        Stack<Integer> stack = new Stack<>();

        // 넣기
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        // is empty
        log.info("isEmpty {}", stack.isEmpty());

        // 꺼내기
        stack.pop();

        // 맨위에 뭐있나 보기 = 안꺼냈으므로 계속 같음
        log.info("peek {}", stack.peek());
        log.info("peek {}", stack.peek());
    }

    @Test
    public void queue() {
        Queue<Integer> queue = new LinkedList<>();

        // 넣기
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);

        // is empty
        log.info("isEmpty {}", queue.isEmpty());

        // 꺼내기
        queue.poll();
        queue.poll();
        queue.poll();

        log.info("peek {}", queue.peek());
        log.info("peek {}", queue.peek());
    }
}
