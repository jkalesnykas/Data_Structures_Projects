package kindergarten;

import javax.print.event.PrintEvent;

public class Classroom {
    private SNode studentsInLine;         // when students are in line: references the FIRST student in the LL
    private SNode musicalChairs;          // when students are in musical chairs: references the LAST student in the CLL
    private boolean[][] seatingLocation;  // represents the classroom seats that are available to students
    private Student[][] studentsSitting;  // when students are sitting in the classroom: contains the students

    public Classroom ( SNode l, SNode m, boolean[][] a, Student[][] s ) {
		studentsInLine  = l;
    musicalChairs   = m;
		seatingLocation = a;
    studentsSitting = s;
	}

    public Classroom() {
        this(null, null, null, null);
    }

    public void enterClassroom ( String filename ) {

        StdIn.setFile(filename);
        int total = StdIn.readInt();
        
        studentsInLine = null;

        for (int i = 0; i < total; i++) {

            Student student = new Student(StdIn.readString(), StdIn.readString(), StdIn.readInt());
            SNode newNode = new SNode(student, studentsInLine);
            studentsInLine = newNode;
        }
  
    }

    public void setupSeats(String seatingChart) {

        StdIn.setFile(seatingChart);
        int row = StdIn.readInt();
        int col =  StdIn.readInt();
        studentsSitting = new Student[row][col];
        seatingLocation = new boolean[row][col];
            

        for (int r = 0; r < seatingLocation.length; r++) {
            for (int c = 0; c < seatingLocation[r].length; c++) {
                seatingLocation[r][c] = StdIn.readBoolean();
            }

        }

    }

    public void seatStudents () {

        for (int r = 0; r < studentsSitting.length; r++) {
            for (int c = 0; c < studentsSitting[r].length; c++) {
                if (seatingLocation[r][c] == true && studentsSitting[r][c] == null && studentsInLine != null) {
                    SNode temp = studentsInLine;
                    studentsSitting[r][c] = temp.getStudent();
                    studentsInLine = studentsInLine.getNext();
                }
            }
        }
    }

    
    public void insertMusicalChairs () {

        musicalChairs = null;
        SNode tail = null;

        for (int r = 0; r < studentsSitting.length; r++) {
            for (int c = 0; c < studentsSitting[r].length; c++) {
                if (studentsSitting[r][c] != null) {
                    SNode stud = new SNode(studentsSitting[r][c], null);
                    if (musicalChairs == null) {
                        musicalChairs = stud;
                        tail = stud;
                    }
                    else {
                        tail.setNext(stud);
                        tail = stud;
                    }
                    studentsSitting[r][c] = null;
                }

            }

        }
        tail.setNext(musicalChairs);
        musicalChairs = tail;
        }

   
    public void moveStudentFromChairsToLine(int size) {

    SNode par = musicalChairs;
    SNode net = musicalChairs.getNext();
    int x = StdRandom.uniform(size);
    int t =0 ;

    while(t<=x)
    {
        if(x==t) {
            if(par.getNext()==musicalChairs) {
            musicalChairs=par;
        }

        par.setNext(net.getNext());
        net.setNext(null);
        size--;
        insertByHeight(net.getStudent());
        break;

    } else {
            par=par.getNext();
            net=net.getNext();
            t++;
            }
        }
    }
    
    public void insertByHeight(Student studentToInsert) {
        // remember to account for all edge cases; hardcode if needed
        
        SNode newNode = new SNode(studentToInsert, null);
        if (studentsInLine == null) {
            studentsInLine = newNode;
            return;
        }
        if (studentToInsert.getHeight() < studentsInLine.getStudent().getHeight()) {
            newNode.setNext(studentsInLine);
            studentsInLine = newNode;
            return;
        }
        if (studentToInsert.getHeight() == studentsInLine.getStudent().getHeight()) {
            SNode curr = studentsInLine;
            while (curr.getNext() != null && curr.getNext().getStudent().getHeight() == studentToInsert.getHeight()) {
                curr = curr.getNext();
            }
            newNode.setNext(curr.getNext());
            curr.setNext(newNode);
            return;
        }

        SNode curr = studentsInLine;
        while (curr.getNext() != null && curr.getNext().getStudent().getHeight() <= studentToInsert.getHeight()) {
            if (curr.getNext().getStudent().getHeight() == studentToInsert.getHeight()) {
                curr = curr.getNext();
                while (curr.getNext() != null && curr.getNext().getStudent().getHeight() == studentToInsert.getHeight()) {
                    curr = curr.getNext();
                }
                newNode.setNext(curr.getNext());
                curr.setNext(newNode);
                return;
            }
            curr = curr.getNext();
        }

        newNode.setNext(curr.getNext());
        curr.setNext(newNode);
    }

    public void eliminateLosingStudents() {

        int numStudents = 0;
        SNode node = musicalChairs;
        
        // Count the number of students in the chairs
        while (node != null) {
            numStudents++;
            node = node.getNext();
            if (node == musicalChairs) {
                break;
            }
        }
        
        // Keep removing students until one is left
        while (numStudents > 1) {
            moveStudentFromChairsToLine(numStudents);
            numStudents--;
        }
        
    }
    public void seatMusicalChairsWinner () {
        
        if (musicalChairs.getNext() == musicalChairs) {

            Student studentToPlace = musicalChairs.getStudent();
            musicalChairs = null;

            for (int r = 0; r < studentsSitting.length; r++) {
                for (int c = 0; c < studentsSitting[r].length; c++) {

                    if (seatingLocation[r][c] == true) {
                        studentsSitting[r][c] = studentToPlace;  
                        return;
                    }

                }

            }

        }
        
    }

    public void playMusicalChairs() {

	
        eliminateLosingStudents();
        seatMusicalChairsWinner();
        seatStudents();
    } 

    public void printStudentsInLine () {

        //Print studentsInLine
        StdOut.println ( "Students in Line:" );
        if ( studentsInLine == null ) { StdOut.println("EMPTY"); }

        for ( SNode ptr = studentsInLine; ptr != null; ptr = ptr.getNext() ) {
            StdOut.print ( ptr.getStudent().print() );
            if ( ptr.getNext() != null ) { StdOut.print ( " -> " ); }
        }
        StdOut.println();
        StdOut.println();
    }

    public void printSeatedStudents () {

        StdOut.println("Sitting Students:");

        if ( studentsSitting != null ) {
        
            for ( int i = 0; i < studentsSitting.length; i++ ) {
                for ( int j = 0; j < studentsSitting[i].length; j++ ) {

                    String stringToPrint = "";
                    if ( studentsSitting[i][j] == null ) {

                        if (seatingLocation[i][j] == false) {stringToPrint = "X";}
                        else { stringToPrint = "EMPTY"; }

                    } else { stringToPrint = studentsSitting[i][j].print();}

                    StdOut.print ( stringToPrint );
                    
                    for ( int o = 0; o < (10 - stringToPrint.length()); o++ ) {
                        StdOut.print (" ");
                    }
                }
                StdOut.println();
            }
        } else {
            StdOut.println("EMPTY");
        }
        StdOut.println();
    }

    public void printMusicalChairs () {
        StdOut.println ( "Students in Musical Chairs:" );

        if ( musicalChairs == null ) {
            StdOut.println("EMPTY");
            StdOut.println();
            return;
        }
        SNode ptr;
        for ( ptr = musicalChairs.getNext(); ptr != musicalChairs; ptr = ptr.getNext() ) {
            StdOut.print(ptr.getStudent().print() + " -> ");
        }
        if ( ptr == musicalChairs) {
            StdOut.print(musicalChairs.getStudent().print() + " - POINTS TO FRONT");
        }
        StdOut.println();
    }


    public void printClassroom() {
        printStudentsInLine();
        printSeatedStudents();
        printMusicalChairs();
    }

    public SNode getStudentsInLine() { return studentsInLine; }
    public void setStudentsInLine(SNode l) { studentsInLine = l; }

    public SNode getMusicalChairs() { return musicalChairs; }
    public void setMusicalChairs(SNode m) { musicalChairs = m; }

    public boolean[][] getSeatingLocation() { return seatingLocation; }
    public void setSeatingLocation(boolean[][] a) { seatingLocation = a; }

    public Student[][] getStudentsSitting() { return studentsSitting; }
    public void setStudentsSitting(Student[][] s) { studentsSitting = s; }

}
