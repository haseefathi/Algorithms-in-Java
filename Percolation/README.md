# Percolation
Estimating the value of _percolation threshold_ using Monte Carlo Simulation

__Percolation__ <br>
Given a composite systems comprised of randomly distributed insulating and metallic materials: what fraction of the materials need to be metallic so that the composite system is an electrical conductor? Given a porous landscape with water on the surface (or oil below), under what conditions will the water be able to drain through to the bottom (or the oil to gush through to the surface)? Scientists have defined an abstract process known as percolation to model such situations.

__The Model__ <br>
We model a percolation system using an n-by-n grid of sites. Each site is either open or blocked. A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites. We say the system percolates if there is a full site in the bottom row. In other words, a system percolates if we fill all open sites connected to the top row and that process fills some open site on the bottom row. (For the insulating/metallic materials example, the open sites correspond to metallic materials, so that a system that percolates has a metallic path from top to bottom, with full sites conducting. For the porous substance example, the open sites correspond to empty space through which water might flow, so that a system that percolates lets water fill open sites, flowing from top to bottom.)

<p align="center">
<img src="https://github.com/haseefathi/Algorithms-in-Java/blob/main/Percolation/images/percolation-img1.png"  width="500" height="200" />
  </p>

__The Problem__<br>
In a famous scientific problem, researchers are interested in the following question: if sites are independently set to be open with probability p (and therefore blocked with probability 1 − p), what is the probability that the system percolates? When p equals 0, the system does not percolate; when p equals 1, the system percolates. The plots below show the site vacancy probability p versus the percolation probability for 20-by-20 random grid (left) and 100-by-100 random grid (right).

<p align="center">
<img src="https://github.com/haseefathi/Algorithms-in-Java/blob/main/Percolation/images/percolation-img2.png"  width="600" height="200" />
  </p>
  
When n is sufficiently large, there is a threshold value p* such that when p < p* a random n-by-n grid almost never percolates, and when p > p*, a random n-by-n grid almost always percolates. No mathematical solution for determining the percolation threshold p* has yet been derived. <br>
### The value of p* is derived in this program

## Monte Carlo simulation
To estimate the percolation threshold, consider the following computational experiment:

1. Initialize all sites to be blocked.
2. Repeat the following until the system percolates: <br>
      * Choose a site uniformly at random among all blocked sites. <br>
      * Open the site.
3. The fraction of sites that are opened when the system percolates provides an estimate of the percolation threshold.
For example, if sites are opened in a 20-by-20 lattice according to the snapshots below, then our estimate of the percolation threshold is 204/400 = 0.51 because the system percolates when the 204th site is opened.

<p align="center">
<img src="https://github.com/haseefathi/Algorithms-in-Java/blob/main/Percolation/images/percolation-img3.png"  width="700" height="200" />
  </p>
  
 By repeating this computation experiment T times and averaging the results, we obtain a more accurate estimate of the percolation threshold.  

## Sample Inputs and Outputs
The main function of PercolationStats.java is executed. The program takes two command line arguments, the first argument being the size of the grid and the second being the number of trials. 
<p align="center">
<img src="https://github.com/haseefathi/Algorithms-in-Java/blob/main/Percolation/images/percolation-img4.png"  width="250" height="120" />
  </p>
For the shown input, the program outputs the following: <br>
<p align="center">
<img src="https://github.com/haseefathi/Algorithms-in-Java/blob/main/Percolation/images/percolation-img5.png"  width="600" height="75" />
  </p>
