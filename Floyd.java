import java.util.Scanner;
import java.lang.StringBuilder;

public class Floyd {
    private int nVertices;
    private int[] vertexWeights;
    private Vertex[][] weightTable;
    private Scanner kb = new Scanner(System.in);

    protected class Vertex {
        private double weight;
        private int nextVertex;
        private boolean infinity;

        /**
         * Returns value of weight
         *
         * @return
         */
        public double getWeight() {
            return weight;
        }

        /**
         * Sets new value of weight
         *
         * @param
         */
        public void setWeight(double weight) {
            this.weight = weight;
        }

        /**
         * Returns value of nextVertex
         *
         * @return
         */
        public int getNextVertex() {
            return nextVertex;
        }

        /**
         * Sets new value of nextVertex
         *
         * @param
         */
        public void setNextVertex(int nextVertex) {
            this.nextVertex = nextVertex;
        }

        /**
         * Returns value of infinity
         *
         * @return
         */
        public boolean isInfinity() {
            return infinity;
        }

        /**
         * Sets new value of infinity
         *
         * @param
         */
        public void setInfinity(boolean infinity) {
            this.infinity = infinity;
        }
    }

    protected void obtainShortestPath() {
        for (int i = 0; i < nVertices; i++) {
            for (int j = 0; j < nVertices; j++) {
                if (weightTable[j][i].isInfinity()) {
                    continue;
                }
                for (int k = 0; k < nVertices; k++) {
                    double testWeight = weightTable[j][i].getWeight() +
                                        weightTable[i][k].getWeight();
                    if (weightTable[i][k].isInfinity() || 
                            (weightTable[i][k].getWeight() > testWeight)) {
                        weightTable[j][k].setWeight(testWeight);
                        weightTable[j][k].setNextVertex(i);
                    }
                }
            }
        }
        printWeightTable();
    }

    protected void getVerticeNumber() {
        System.out.print("Enter number of vertices: ");
        nVertices = kb.nextInt();
        weightTable = new Vertex[nVertices][nVertices];
    }

    protected void getVertexWeights() {
        for (int i = 0; i < nVertices; i++) {
            for (int j = 0; j < nVertices; j++) {
                System.out.print("Enter weight for (" +
                        (i + 1) + ", " + (j + 1) + ")\n" +
                        "(or -1 for no edge): ");
                double weight = kb.nextDouble();
                Vertex vertex = new Vertex();
                if (weight != -1) {
                    vertex.setInfinity(false);
                    vertex.setWeight(weight);
                    vertex.setNextVertex(j);
                } else {
                    vertex.setInfinity(true);
                    vertex.setWeight(-1);
                    vertex.setNextVertex(-1);
                }
                weightTable[i][j] = vertex;
            }
        }
        printWeightTable();
    }

    protected void printWeightTable() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nVertices; i++) {
            for (int j = 0; j < nVertices; j++) {
                if (weightTable[i][j].isInfinity()) {
                    sb.append("-");
                } else {
                    sb.append(weightTable[i][j].getWeight() + "\t");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    /*
     * Interact with user to get directed graph data.
     */
    protected void userInteraction() {
        getVerticeNumber();
        getVertexWeights();
    }

    public static void main(String[] args) {
        Floyd driver = new Floyd();
        driver.userInteraction();
        driver.obtainShortestPath();
    }
}
