/*
 * Exercise 10 FLoyd's Algorithm
 * @author: Kyle L Frisbie, Ryan Newsom
 * @date: 4/30/16
 */
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

        public Vertex(double weight, int nextVertex, boolean infinity) {
            this.weight = weight;
            this.nextVertex = nextVertex;
            this.infinity = infinity;
        }

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
        printWeightTable(0);
        for (int i = 0; i < nVertices; i++) {
            for (int j = 0; j < nVertices; j++) {
                if (weightTable[j][i].isInfinity()) {
                    continue;
                }
                for (int k = 0; k < nVertices; k++) {
                    if (weightTable[j][i].isInfinity() ||
                            weightTable[i][k].isInfinity()) {
                        continue;
                    }
                    double testWeight = weightTable[j][i].getWeight() +
                            weightTable[i][k].getWeight();
                    if (weightTable[j][k].isInfinity() ||
                            (weightTable[j][k].getWeight() > testWeight)) {
                        weightTable[j][k].setWeight(testWeight);
                        weightTable[j][k].setNextVertex(i + 1);
                        weightTable[j][k].setInfinity(false);
                    }
                }
            }
            printWeightTable(i + 1);
        }
    }

    protected void getVerticesNumber() {
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
                if (weight != -1) {
                    weightTable[i][j] = new Vertex(weight, 0, false);
                } else {
                    weightTable[i][j] = new Vertex(-1, 0, true);
                }
            }
        }
        System.out.println();
    }

    protected void printWeightTable(int pass) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nVertices; i++) {
            if (i == 0) {
                sb.append(String.format("%10s", "D(" + pass + ")|"));
            }
            sb.append(String.format("%9d%1s", (i + 1), "|"));
        }
        sb.append("\n");
        for (int i = 0; i <= nVertices; i++) {
            sb.append(String.format("%10s", "---------|"));
        }
        sb.append("\n");
        for (int i = 0; i < nVertices; i++) {
            for (int j = 0; j < nVertices; j++) {
                if (j == 0) {
                    sb.append(String.format("%9d%1s", i + 1, "|"));
                }
                if (weightTable[i][j].isInfinity()) {
                    sb.append(String.format("%10s", "-(0)|"));
                } else {
                    sb.append(String.format("%10s", weightTable[i][j].getWeight() + "(" + weightTable[i][j].getNextVertex() + ")" + "|"));
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

//    protected void setTestTable() {
//        nVertices = 5;
//        weightTable = new Vertex[nVertices][nVertices];
//        weightTable[0][0] = new Vertex(0, 1, false);    weightTable[1][0] = new Vertex(1, 2, false);    weightTable[2][0] = new Vertex(-1, -1, true);   weightTable[3][0] = new Vertex(1, 4, false);   weightTable[4][0] = new Vertex(5, 5, false);
//        weightTable[0][1] = new Vertex(9, 1, false);    weightTable[1][1] = new Vertex(0, 2, false);    weightTable[2][1] = new Vertex(3, 3, false);    weightTable[3][1] = new Vertex(2, 4, false);   weightTable[4][1] = new Vertex(-1, -1, true);
//        weightTable[0][2] = new Vertex(-1, -1, true);   weightTable[1][2] = new Vertex(-1, -1, true);   weightTable[2][2] = new Vertex(0, 3, false);    weightTable[3][2] = new Vertex(4, 4, false);   weightTable[4][2] = new Vertex(-1, -1, true);
//        weightTable[0][3] = new Vertex(-1, -1, true);   weightTable[1][3] = new Vertex(-1, -1, true);   weightTable[2][3] = new Vertex(2, 3, false);    weightTable[3][3] = new Vertex(0, 4, false);   weightTable[4][3] = new Vertex(3, 5, false);
//        weightTable[0][4] = new Vertex(3, 1, false);    weightTable[1][4] = new Vertex(-1, -1, true);   weightTable[2][4] = new Vertex(-1, -1, true);   weightTable[3][4] = new Vertex(-1, -1, true);   weightTable[4][4] = new Vertex(0, 5, false);
//    }

    /*
     * Interact with user to get directed graph data.
     */
    protected void userInteraction() {
        getVerticesNumber();
        getVertexWeights();
    }

    public static void main(String[] args) {
        Floyd driver = new Floyd();
        driver.userInteraction();
//        driver.setTestTable();
        driver.obtainShortestPath();
    }
}