from collections import deque

def topological_sort_bfs(graph, V):
    in_degree = [0] * V

    # 1. in-degree 계산
    for u in range(V):
        for v in graph[u]:
            in_degree[v] += 1

    # 2. in-degree가 0인 노드 큐에 추가
    queue = deque()
    for i in range(V):
        if in_degree[i] == 0:
            queue.append(i)

    result = []

    # 3. BFS
    while queue:
        curr = queue.popleft()
        result.append(curr)

        for next_node in graph[curr]:
            in_degree[next_node] -= 1
            if in_degree[next_node] == 0:
                queue.append(next_node)

    return result


# 그래프 정의
V = 4
graph = {
    0: [1, 2],  # A → B, C
    1: [3],     # B → D
    2: [3],     # C → D
    3: []       # D
}

order = topological_sort_bfs(graph, V)
print([chr(ord('A') + x) for x in order])
