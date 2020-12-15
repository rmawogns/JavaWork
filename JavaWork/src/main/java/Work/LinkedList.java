package Work;

class Node{
    int data;    // 난수를 생성하여 입력
    Node next;
    public Node() { }
    public Node(int n) {
        data = n;
        next = null;
    }
}

class List {
    Node first, last;
    public List() {};
    public List(List l) {
        first = l.first;
    };
    public void addNode(int a) {
        Node node = new Node(a);
        if(first == null) {
            first = node;
        }
        else {
            node.next = first;
            first = node;
        }
    };

    public void showList() {
        Node temp = first;
        while(temp != null) {
            System.out.print(temp.data + "  ");
            temp = temp.next;
        }
        System.out.println();
    };
    public void addList(List l) {
        Node p = this.first;
        Node q = new Node();
        while(p != null){
            q = p;
            p = p.next;
        }
        q.next = l.first;
    }
    public void mergeList(List l) {
        Node p = this.first;
        Node pt = p;
        Node q = l.first;
        Node qt = q;
        while(p != null && q != null) {
            pt = p;
            qt = q;
            p = p.next;
            q = q.next;
            pt.next = qt;
            qt.next = p;
        }
        if(pt.next == null) {
            pt.next = p;
        }
        else if (qt.next == null) {
            qt.next = q;
        }
    }
    public int length() {
        Node node = first;
        int cnt = 0;
        while(node != null) {
            node = node.next;
            cnt++;
        }
        return cnt;
    }
    public void simpleSplit(List b) {
        Node node = this.first;
        int length = length() / 2;
        for(int i = 0; i < length -1; i++) {
            node = node.next;
        }
        b.first = node.next;
        node.next = null;
    }
    public void simpleMerge(List b) {
        if(this.first == null)   {
            this.first = b.first;
            return;
        } else if (b.first == null)  return;

        if(b.first.data > this.first.data) {
            Node temp = this.first;
            this.first = b.first;
            b.first = temp;
        }
        Node p = this.first;    Node pt = p;
        Node q = b.first;       Node qt = q;

        while(p != null && q != null) {
            if(p.data > q.data) {
                pt = p;
                p = p.next;
                if(p != null && p.data < q.data)
                    pt.next = q;
                else if (p == null)
                    pt.next = q;
            }
            else {
                qt = q;
                q = q.next;
                if(q != null && q.data < p.data)
                    qt.next = p;
                else if (q == null)
                    qt.next = p;
            }
        }
    }
}

class ListIterator {
    private List list;
    Node current;
    public ListIterator(List l) {
        list = l;
        current = l.first;
    }
    public int First() {
        return list.first.data;
    }
    public int Next() {
        int n = current.data;
        current = current.next;
        return n;
    }
    public boolean NotNull() {
        boolean b = true;
        if(current == null)     b = false;
        return b;
    }
    public boolean NextNotNull() {
        boolean b = true;
        if(current.next == null)    b = false;
        return b;
    }
}

public class LinkedList {
    public static void makeList(List l, int a) {
        for(int i = 0; i < a; i++) {
            int rnum = (int)(Math.random() * 10) +1;
            l.addNode(rnum);
        }
    }
    public static void makeList2(List l, int a, int n) {
        for(int i = 0; i < a; i++) {
            l.addNode(n);
            n += 2;
        }
    }
    public static int Sum(List l) {
        ListIterator li1 = new ListIterator(l);
        int sum = 0;
        while(li1.NotNull() == true)    sum += li1.Next();
        return sum;
    }
    public static int Max(List l) {
        ListIterator li1 = new ListIterator(l);
        int max = 0;
        while(li1.NotNull() == true) {
            int n = li1.Next();
            if(n > max) max = n;
        }
        return max;
    }
    public static void split(List[] l) {
        int top = 1;
        int current = 0;
        while (current < top) {
            while (l[current].length() > 1) {
                l[current].simpleSplit(l[top]);
                top += 1;
            }
            current += 1;
        }
    }
    public static void merge(List[] l) {
        int n = l.length;
        for(int i = 1; i < n; i++)
            l[0].simpleMerge(l[i]);
    }

    public static void main(String[] args) {
        List l2 = new List();
        List l3 = new List();

        makeList2(l2, 5, 5);
        makeList2(l3, 4, 8);
        l2.showList();
        l3.showList();

        l2.simpleMerge(l3);
        l2.showList();

        int n = l2.length();
        List[] l = new List[n];
        for(int i = 0; i < n; i++)  l[i] = new List();
        l[0] = l2;
        split(l);
        for(int i = 0; i < l.length; i++) {
            l[i].showList();
        }
        merge(l);
        l[0].showList();

//        int sum = Sum(l1);
//        System.out.println("sum = " + sum);
//        int max = Max(l1);
//        System.out.println("max = " + max);
        //l1.simpleSplit(l3);
        //l1.showList();
        //l2.showList();
        //l3.showList();
        //l1.mergeSort(l1, l2);
        //l1.showList();
    }
}