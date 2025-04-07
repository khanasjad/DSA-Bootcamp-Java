package LeetCode2.DFS;

import java.util.ArrayList;
import java.util.List;

public class AllPathInGraph {


    public static void main(String[] args) {
        AllPathInGraph solution = new AllPathInGraph();
        int[][] graph = {{1,2},{3},{3},{}};
        List<List<Integer>> paths = solution.allPathsSourceTarget(graph);
        System.out.println(paths);
    }

    private List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List <List<Integer>> results =  new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        dfs(graph,0,path,results);
        return results;
    }

    private void dfs(int[][] graph, int current, List<Integer> path, List<List<Integer>> results) {

        if( current == graph.length -1){
            results.add(new ArrayList<>(path));
            return;
        }

        for (int neighbours : graph[current] )
        {
            path.add(neighbours);
            dfs(graph,neighbours,path,results);
            path.remove(path.size() -1);
        }
    }
}
