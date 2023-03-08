package kindergarten;

public class SNode {
    private Student student;
    private SNode   next;    

    public SNode ( Student s, SNode n ) {
		student = s;
		next = n;
	}

    public SNode(String string, String string2, int i, Object object) {
        this(null, null);
    }

    public Student getStudent () { return student; }
    public void setStudent (Student s) { student = s; }

    public SNode getNext () { return next; }
    public void setNext (SNode n) { next = n; }
    
}
