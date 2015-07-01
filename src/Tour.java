
/**
 *
 * @author prestes
 */
public class Tour {

    Node node1 = new Node();

    //construtor Tour do node1
    public Tour() {
        this.node1.next = null;
    }

    //construtor Tour dos pontos da parte1 do trabalho para o debug
    public Tour(Point a, Point b, Point c, Point d) {
        Node nodoX = new Node();
        Node nodoY = new Node();
        Node nodoZ = new Node();
        
        node1.p = a;
        nodoX.p = b;
        nodoY.p = c;
        nodoZ.p = d;
        
        node1.next = nodoX;
        nodoX.next = nodoY;
        nodoY.next = nodoZ;
        nodoZ.next = node1;
    }

    //método que escreve os pontos
    void show() {
        Node node = new Node();
        node = this.node1.next;
        StdOut.println(node.p);
        for (node = node.next; node != this.node1.next; node = node.next) {
            StdOut.println(node.p);
        }
    }

    // o método retorna 0  caso a lista seja nula, caso não seja retorna o tamanho da lista
    int size() {
        // contador armazena o numero de pontos
        int count = 0;
        Node node = new Node();
        node = this.node1.next;
        count++;
        for (node = node.next; node != this.node1.next; node = node.next) {
            count++;
        }
        if (count == 0 || node == null) {
            return 0;
        } else {
            return count;
        }
    }

    //método que calcula a distancia entre dois pontos, 
    // se a lista for nula retorna 0
    double distance() {
        double total = 0;
        Node node = new Node();
        node = this.node1.next;
        total += node.p.distanceTo(node.next.p);
        for (node = node.next; node != node1.next; node = node.next) {
            total += node.p.distanceTo(node.next.p);
        }
        if (this.size() == 0) {
            return 0.0;
        } else if (node == null) {
            return 0;
        } else {
            return total;
        }
    }

    //método que desenha uma linha de um ponto a outro até o final da lista
    //se a lista for nula retorna 0
    void draw() {
        Node node = new Node();
        node = this.node1.next;
        node.p.drawTo(node.next.p);
        for (node = node.next; node != this.node1.next; node = node.next) {
            node.p.drawTo(node.next.p);
        }
        if (this.size() == 0 || node == null) {
            return;
        }
    }

    //método que insere um ponto p em uma rota.
    void insertInOrder(Point p) {
        Node node = new Node();
        Node aux = new Node();
        int count = 0;
        int i;

        aux.p = p;

        node = node1.next;
        if (node != null) {
            do {
                count++;
                node = node.next;
            } while (node != node1.next);           
            for (i = 0; i < count - 1; i++) {
                node = node.next;
            }
            aux.next = node.next;
            node.next = aux;
        } else {
            node1.next = aux;
            aux.next = aux;
        }
    }

    //método para determinar qual nodo que
    //antecede o ponto p que será inserido, a distância Euclidiana entre cada   
    //ponto da rota e o ponto p que deve ser calculado
    void insertNearest(Point p) {

        double cDistance;
        Node node = new Node();
        Node aux = new Node();
        Node newNode = new Node();
        node = this.node1.next;

        if (node == null) {
            aux.p = p;
            this.node1.next = aux;
            aux.next = aux;
            return;
        }

        aux.p = p;
        cDistance = node.p.distanceTo(p);
        newNode = node;

        for (node = node.next; node != this.node1.next; node = node.next) {
            if (cDistance > node.p.distanceTo(p)) {
                cDistance = node.p.distanceTo(p);
                newNode = node;
            }
        }
        if (newNode.next == this.node1.next) {
            newNode.next = aux;
            aux.next = this.node1.next;
        } else {
            node = newNode.next;
            newNode.next = aux;
            aux.next = node;
        }
    }

    //método parecido com o InsertNearest, porém o objetivo é inserir o ponto p onde a inserção irá
    //resultar no menor aumento do comprimento total.
    void insertSmallest(Point p) {
        Node node = new Node();
        Node aux = new Node();
        Node newNode = new Node();
        node = this.node1.next;

        if (node == null) {
            aux.p = p;
            this.node1.next = aux;
            aux.next = aux;
            return;
        }

        double cDistance, distance = this.distance();
        aux.p = p;
        cDistance = node.p.distanceTo(aux.p) + aux.p.distanceTo(node.next.p) + distance - node.p.distanceTo(node.next.p);
        newNode = node;

        for (node = node.next; node != this.node1.next; node = node.next) {
            if (cDistance > node.p.distanceTo(aux.p) + aux.p.distanceTo(node.next.p) + distance - node.p.distanceTo(node.next.p)) {
                cDistance = node.p.distanceTo(aux.p) + aux.p.distanceTo(node.next.p) + distance - node.p.distanceTo(node.next.p);
                newNode = node;
            }
        }

        if (newNode.next == this.node1.next) {
            newNode.next = aux;
            aux.next = this.node1.next;
        } else {
            node = newNode.next;
            newNode.next = aux;
            aux.next = node;
        }
    }

    // main utilizado para a parte1 do trabalho e debug
    public static void main(String[] args) {
        // define 4 points forming a square
        Point a = new Point(100.0, 100.0);
        Point b = new Point(500.0, 100.0);
        Point c = new Point(500.0, 500.0);
        Point d = new Point(100.0, 500.0);
        StdDraw.setXscale(0, 600);
        StdDraw.setYscale(0, 600);
        
        Tour squareTour = new Tour(a, b, c, d);
        squareTour.show();
        System.out.println(squareTour.size());
        System.out.println(squareTour.distance());
        squareTour.draw();
    }
}
