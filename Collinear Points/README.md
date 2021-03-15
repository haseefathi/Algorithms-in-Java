# Collinear Points
Computer vision involves analyzing patterns in visual images and reconstructing the real-world objects that produced them. The process is often broken up into two phases: feature detection and pattern recognition. Feature detection involves selecting important features of the image; pattern recognition involves discovering patterns in the features. We will investigate a particularly clean pattern recognition problem involving points and line segments. This kind of pattern recognition arises in many other applications such as statistical data analysis.

This program recognizes line patterns in a given set of points. 

## The Problem 
Given a set of n distinct points in the plane, find every (maximal) line segment that connects a subset of 4 or more of the points.

<p align="center">
<img src="https://github.com/haseefathi/Algorithms-in-Java/blob/main/Collinear%20Points/images/collinear-points-1.png"  width="500" height="200" />
</p>

## Brute force method to get required line segments
BruteCollinearPoints.java  examines 4 points at a time and checks whether they all lie on the same line segment, returning all such line segments. To check whether the 4 points p, q, r, and s are collinear, the three slopes between p and q,between p and r, and between p and s are all equal.

### Performance
The order of growth of the running time of the program is n<sup>4</sup> in the worst case and it uses space proportional to n plus the number of line segments returned.

## Faster, sorting-based solution
Remarkably, it is possible to solve the problem much faster than the brute-force solution described above. Given a point p, the following method determines whether p participates in a set of 4 or more collinear points.
<ul>
<li>Think of p as the origin.</li>
<li>For each other point q, determine the slope it makes with p.</li>
<li>Sort the points according to the slopes they makes with p.</li>
<li>Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to p. If so, these points, together with p, are collinear.</li>
</ul>
Applying this method for each of the n points in turn yields an efficient algorithm to the problem. The algorithm solves the problem because points that have equal slopes with respect to p are collinear, and sorting brings such points together. The algorithm is fast because the bottleneck operation is sorting.

<p align="center">
<img src="https://github.com/haseefathi/Algorithms-in-Java/blob/main/Collinear%20Points/images/collinear-points-2.png"  width="150" height="400" />
</p>

### Performance
The order of growth of the running time of your program is be n<sup>2</sup> log n in the worst case and it uses space proportional to n plus the number of line segments returned. FastCollinearPoints should work properly even if the input has 5 or more collinear points.