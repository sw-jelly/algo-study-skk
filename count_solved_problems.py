import os
import pandas as pd

name_map = {
    "SA": "권송아",
    "SL": "고수림",
    "YK": "양유경",
}

algorithm_map = {
    BackTracking: '백트래킹',
    BFS_DFS: '그래프 탐색',
    BinarySearch: '이분 탐색',
    Bitmasking: '비트마스킹',
    Bruteforce: '브루트포스',
    DataStructure: '자료구조',
    Combinatorics: '조합론',
    Dijkstra: '다익스트라',
    DivideAndConquer: '분할정복',
    DynamicProgramming: '다이나믹 프로그래밍',
    FloydWarshall: '플로이드 워셜',
    Greedy: '그리디',
    Implematation: '구현',
    KMP: 'KMP',
    LCA: 'LCA(최소 공통 조상)',
    Math: '수학',
    MST: 'MST(최소 스패닝 트리)',
    PrefixSum: '누적합',
    SegmentTree: '세그먼트 트리',
    String: '문자열',
    TopologySort: '위상정렬',
    Tree: '트리',
    Trie: '트라이',
    TwoPointer: '투포인터',
    UnionFind: '유니온 파인드',
    Programmers: '프로그래머스',
}

INVALID = 'invalid'

def count_solved_problems(root):
    result = {}
    
    for algorithm in os.listdir(root):
        algorithm_path = os.path.join(root, algorithm)
        if os.path.isdir(algorithm_path):
            for problem_path = os.path.join(algorithm_path, problem)
            if os.path.isdir(problem_path):
                for solver in os.listdir(problem_path):
                    solver_name = name_map.get(solver.split('_')[-1], INVALID)
                    algorithm_name = name_map.get(algorithm, algorithm)
                    if algorithm_name not in result:
                        result[algorithm_name] = {}
                    if solver_name != INVALID:
                        if solver_name not in result[algorithm]:
                            result[algorithm_name][solver_name] = 0
                        result[algorithm_name][solver_name] += 1

def update_readme(counts, readme_path):
    df = pd.DataFrame(counts).fillna(0).astype(int).T
    table = df.to_markdown()
    
    with open(readme_path, 'w') as readme_file:
        readme_file.write("# 알고리즘 스터디 송곳갱 현황\n\n")
        readme_file.write(table)

if __name__ == "__main__":
    repo = os.path.dirname(os.path.abspath(__file__))
    readme_path = os.path.join(repo_dir, 'README.md')

    counts = count_solved_problems(root)
    update_readme(counts, readme_path)
