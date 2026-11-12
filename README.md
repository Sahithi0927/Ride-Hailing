# Unit II - Divide & Conquer and Greedy

## Case Study: Ride-Hailing Services (Uber/Ola)

This Java Swing application demonstrates how algorithm design can support driver matching, cloud task scheduling, network design, and route planning. All four interactive sections run together in one dashboard window.

### Case Study Applications

1. **Closest Pair of Points - Driver-Passenger Matching:** Treat map coordinates as points and use divide and conquer to find the available driver closest to the passenger. This reduces matching delay. The simulator accepts integer coordinates and reports Euclidean distance in coordinate units.
2. **Job Sequencing with Deadlines - Cloud Resource Allocation:** Treat ride processing, payments, tracking, analysis, or notifications as jobs with deadlines and profits/priorities. A greedy schedule selects jobs to maximize total profit while meeting deadlines.
3. **Minimum Cost Spanning Tree - Network Design:** Treat towers, data centers, or charging stations as vertices and infrastructure costs as weighted edges. Prim's and Kruskal's algorithms select a minimum-cost set of connections that keeps the network connected.
4. **Dijkstra's Algorithm - Shortest Route:** Treat road locations as vertices and road distances or travel times as non-negative edge weights. Dijkstra finds a minimum-cost route from the selected source to destination and reports when no route exists.

| Algorithm | Technique | Application | Objective |
| --- | --- | --- | --- |
| Closest pair / nearest driver | Divide and conquer | Driver-passenger matching | Find the closest available driver |
| Job sequencing with deadlines | Greedy | Cloud resource allocation | Maximize scheduled profit |
| Prim's / Kruskal's | Greedy | Network design | Minimize connection cost |
| Dijkstra's | Greedy | Route planning | Find a shortest path |

## Project Structure

```
DAA1/
  src/
    algorithms/   # Algorithm logic classes
    gui/          # GUI panels and main dashboard
```

## How to Compile and Run

1. **Open a terminal in the `DAA1` directory.**
2. **Compile all Java files:**
   ```sh
   javac -d bin src/algorithms/*.java src/gui/*.java
   ```
   (Create a `bin` directory if it doesn't exist.)
3. **Run the application:**
   ```sh
   java -cp bin gui.MainDashboard
   ```

## Sample Inputs and Expected Results

### Closest Pair (Driver-Passenger)
- Passenger: `50, 50`
- Drivers:
  ```
  20,30
  80,60
  55,90
  120,40
  60,55
  ```
  Expected: driver `(60,55)`, distance approximately `11.18`.

### Job Sequencing
```
A,2,100
B,1,19
C,2,27
D,1,25
E,3,15
```
Expected maximum profit: `142` (jobs A, C, and E).

### MST (Prim's/Kruskal's)
- Vertices: `5`
- Edges:
  ```
  0,1,2
  0,3,6
  1,2,3
  1,3,8
  1,4,5
  2,4,7
  3,4,9
  ```
  Expected minimum spanning tree cost: `16` for both algorithms.

### Dijkstra
- Vertices: `6`
- Source: `0`
- Destination: `5`
- Edges:
  ```
  0,1,7
  0,2,9
  0,5,14
  1,2,10
  1,3,15
  2,3,11
  2,5,2
  3,4,6
  4,5,9
  ```
Expected shortest path: `0 -> 2 -> 5`, total distance `11`.

## Features
- Separate algorithm classes keep the computations independent of the GUI.
- Input validation and error dialogs are included in each panel.
- Demo/sample data buttons are available for each algorithm.
- The closest-driver result is computed against the passenger, not against other drivers.

---

