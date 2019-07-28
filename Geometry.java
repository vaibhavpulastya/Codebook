

// https://codeforces.com/blog/entry/48122

// do computations in integers as long as possible
//avoid using floating point division, square root, and especially trigonometric functions as much as possible.

// POINT-------------------------

// Dot Product - (dot product sign indicates whether two vectors point in about the same direction)
//  a*b = |a||b|cos(alpha) = |a| * (projection of b onto a)

// Cross Product - (indicates whether the shortest turn from a to b is in the counter-clockwise direction)
// a^b = |a||b|sin(alpha) = doubled area of triangle
// a^b = -(b^a)

// rotation by alpha
//  [ x' ]   [ cos(alpha)    - sin(alpha) ] [ x ]
//  [ y' ] = [ sin(alpha)      cos(alpha) ] [ y ]

// SideNotes -
// If we multiply each vector by the length of the other vector,
// their length will be equal, and the parallelogram will become a rhombus, where diagonal is also the angle bisector.


// LINE -------------------------------------

// A + t*(AB)   A - point     AB - direction    [both A and AB will be represted by Point DS]
// representing line segment -- set bounds on variable 't' [0,1]

public class Geometry {

}

class Point{
    long x;
    long y;

    Point(long x1, long y1){x = x1; y = y1;}

    long dot(Point a){ return x*a.x + y*a.y; }
    long cross(Point a){ return x*a.y - y*a.x;}
    Point rotate90(){ return new Point(-y, x); }
    long abs(){return dot(this);}
    double dist(Point P){ return sub(P).norm(); }
    double norm(){return Math.sqrt((double) dot(this));}
    Point add(Point a){return new Point(x + a.x, y + a.y);}
    Point sub(Point a){return new Point(x - a.x, y - a.y);}
    Point mul(long n){ return new Point(x*n, y*n); }
    boolean eq(Point a){return (a.x == x) && (a.y == y);}
    int ccw(Point a){ return (int)Math.signum(cross(a)); } // returns 1 if 'a' is ccw to point
}

class Line{

    Point A;
    Point AB;  //  A ----> B

    Line(long x1, long y1, long x2, long y2){
        A = new Point(x1, y1);
        AB = new Point(x2 - x1, y2 - y1);  // line could shrink to a point
    }

    boolean onLine(Point P){
        if(AB.x == 0 && AB.y == 0){ return A.eq(P);}
        else{ return AB.cross(P.sub(A)) == 0; }
    }

    boolean onSegment(Point P){
        if(AB.x == 0 && AB.y == 0){ return A.eq(P); }
        Point v1 = A.sub(P);  Point v2 = (A.add(AB)).sub(P);
        // point lies on the line and lies inside the disk built with line segment as its diameter
        return (v1.cross(v2)) == 0 && v1.dot(v2) <= 0;
    }

    double distLine(Point P){  // perpendicular distance
        if(AB.x == 0 && AB.y == 0){ return P.sub(A).norm(); }
        Point v1 = P.sub(A);
        return (((double)v1.cross(AB))/2)/AB.norm();
    }

    double distSeg(Point P){
        if((P.sub(A)).dot(AB) <= 0){ return  P.dist(A); }   // point on the left of the line AB
        if((P.sub(A)).dot(AB) >= 0){ return P.dist(A.add(AB)); }  // on right
        else return distLine(P);  // else perp dist
    }

    Point projection(Point P){
        // CAREFUL - INTEGER DIVISION
        return A.add(AB.mul((P.sub(A).dot(AB))/AB.abs()));
    }

    Point reflection(Point P){
        // CAREFUL - INTEGER DIVISION IN projection()
        return (projection(P).mul(2)).sub(P);
    }

}