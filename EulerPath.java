import java.util.*;

class EulerPath {
    Map<String, List<String>> graph = new HashMap<>(); // graph as adjacency list
    Set<String> nodes = new HashSet<>(); // just set of nodes for convenience

    List<String> result = new ArrayList<>(); // result words' sequence storage
    List<String> path = new ArrayList<>(); // result as Euler's path storage
    Stack<String> vertexStack = new Stack<>(); // vertices backtracking stack
    Stack<String> edgeStack = new Stack<>(); // edges backtracking stack

    /*
    Two hepler methods just to add syntax sugar
     */
    static String firstLetterOf(String word) {
        return word.substring(0, 1).toLowerCase();
    }

    static String lastLetterOf(String word) {
        return word.substring(word.length() - 1).toLowerCase();
    }

    /*
    We initialize set of nodes and graph itself using input word sequence
    in parametrized class constructor

     */
    public EulerPath(String[] inputSequence) {
        for (String word : inputSequence) {
            nodes.add(firstLetterOf(word));
            nodes.add(lastLetterOf(word));
        }
        for (String node : nodes) {
            graph.put(node, new ArrayList<>());
        }
        for (String word : inputSequence) {
            String node = firstLetterOf(word);
            List<String> tmp = graph.get(node);
            tmp.add(word);
            graph.put(node, tmp);
        }
    }

    /*
    Helper method to define start node for DFS algorithm
    Returns node or, if nonexistent, "any" string
     */
    String getStartNode(String[] inputSequence) {
        Map<String, Integer[]> counter = new HashMap<>();
        for (String node : nodes) {
            Integer[] tuplet = {0, 0};
            counter.put(node, tuplet);
        }
        for (String word : inputSequence) {
            String fl = firstLetterOf(word);
            String ll = lastLetterOf(word);
            Integer[] tmp = counter.get(fl);
            tmp[0]++;
            counter.put(fl, tmp);
            tmp = counter.get(ll);
            tmp[1]++;
            counter.put(ll, tmp);

        }
        for (String node : counter.keySet()) {
            if (counter.get(node)[0] > counter.get(node)[1]) {
                return node;
            }
        }
        return "any";
    }

    void findEulerPath(String startNode){
        if(startNode.equals("any")) startNode = nodes.toArray(new String[0])[0];
        vertexStack.push(startNode);
        while (!vertexStack.isEmpty()){
            List<String> currentEdgeList = graph.get(vertexStack.peek());
            if(currentEdgeList.size() > 0){
                edgeStack.push(currentEdgeList.get(0));
                currentEdgeList.remove(0);
                graph.put(vertexStack.peek(), currentEdgeList);
                String currentNode = lastLetterOf(edgeStack.peek());
                vertexStack.push(currentNode);
            } else {
                path.add(vertexStack.pop());
                if(!edgeStack.isEmpty()) result.add(edgeStack.pop());
            }
        }
    }

    public static void main(String[] args) {
        //String[] inputSequence = {"test"};
        //String[] inputSequence = {"Адлер", "Рыбинск", "Курган", "Нарьян-Мар", "Рим", "Мурманск", "Константинополь"};
        //String[] inputSequence = {"alpha", "elephant", "kick", "linea", "android", "eels", "kill", "sum", "eye", "spud", "drink", "even", "bee", "dad", "num", "tea", "test", "sims", "apple", "mob", "mate"};
        String[] inputSequence = {"test", "sum", "num", "kill", "sims", "mate", "even", "dad", "android", "eye", "kick", "elephant", "drink", "alpha", "eels", "bee", "mob", "linea", "tea", "apple"};
        EulerPath solution = new EulerPath(inputSequence);
        String startNode = solution.getStartNode(inputSequence);
        solution.findEulerPath(startNode);
        Collections.reverse(solution.result);
        System.out.println(solution.result);
    }
}