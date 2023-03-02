import copy
from collections import deque

def get_size(graph, target, n):
    visited = [False] * (n+1)
    visited[target] = True
    queue = deque([target])
    while queue:
        x = queue.popleft()
        for g in graph[x]:
            if not visited[g]:
                visited[g] = True
                queue.append(g)
    return visited.count(True)
        

def solution(n, wires):
    # hint! 차수가 가장 높은 것
    answer = n
    degree = [0] * (n+1)
    graph = {}
    for wire in wires:
        a, b = wire
        if not graph.get(a, False):
            graph[a] = []
        if not graph.get(b, False):
            graph[b] = []
        graph[a].append(b)
        degree[a] += 1
        graph[b].append(a)
        degree[b] += 1
        
    for wire in wires:
        vertex1, vertex2 = wire
        temp_graph = copy.deepcopy(graph)
        temp_graph[vertex1].remove(vertex2)
        temp_graph[vertex2].remove(vertex1)
        size1 = get_size(temp_graph, vertex1, n)
        size2 = get_size(temp_graph, vertex2, n)
        answer = min(abs(size1- size2), answer)
    return answer

