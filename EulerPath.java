import java.util.*;

class EulerPath {
    Map<String, List<String>> graph = new HashMap<>(); // граф в виде списка смежности
    Set<String> nodes = new HashSet<>(); // отдельный список нодов, для удобства

    List<String> result = new ArrayList<>(); // массив для результатов
    List<String> path = new ArrayList<>(); // массив для хранения эйлерова пути (цикла)
    Stack<String> vertexStack = new Stack<>(); // стек нодов
    Stack<String> edgeStack = new Stack<>(); // стек ребер (самих слов)

    /*
    Два вспомогательных метода, для лучшей читаемости кода
     */
    String firstLetterOf(String word) {
        return word.substring(0, 1).toLowerCase();
    }

    String lastLetterOf(String word) {
        return word.substring(word.length() - 1).toLowerCase();
    }

    /*
    Будем инициализировать граф и список нодов 
    с помощью параметризованного конструктора

     */
    EulerPath(String[] inputSequence) {
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
    Вспомогательный метод для определения стартовой ноды для основного DFS
    Если у нас эйлеров цикл, и можно начинать с любой ноды, возвращаем строку "any"
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
    /*
    Модифицированный DFS, основной алгоритм
    */
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
        EulerPath solution = new EulerPath(inputSequence); // создаем и инициализируем объект, содержащий граф 
        String startNode = solution.getStartNode(inputSequence); // находим стартовую ноду
        solution.findEulerPath(startNode); // вычисляем решение
        Collections.reverse(solution.result); // массив результатов необходимо инвертировать
        System.out.println(solution.result);
    }
}
