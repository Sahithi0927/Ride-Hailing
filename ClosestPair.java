package algorithms;

import java.awt.Point;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Divide and Conquer Closest Pair of Points algorithm for Driver-Passenger Matching.
 */
public class ClosestPair {
    public static class Result {
        public Point p1, p2;
        public double distance;
        public Result(Point p1, Point p2, double distance) {
            this.p1 = p1;
            this.p2 = p2;
            this.distance = distance;
        }
    }

    public static Result findClosestPair(Point[] points) {
        Point[] px = points.clone();
        Arrays.sort(px, Comparator.comparingInt(p -> p.x));
        Point[] py = points.clone();
        Arrays.sort(py, Comparator.comparingInt(p -> p.y));
        return closestPair(px, py);
    }

    /** Finds the nearest driver to the passenger by recursively searching x-sorted regions. */
    public static Result findNearestDriver(Point passenger, Point[] drivers) {
        if (passenger == null || drivers == null || drivers.length == 0) {
            throw new IllegalArgumentException("Passenger and at least one driver are required.");
        }
        Point[] sortedDrivers = drivers.clone();
        Arrays.sort(sortedDrivers, Comparator.comparingInt(p -> p.x));
        return nearestDriver(passenger, sortedDrivers, 0, sortedDrivers.length);
    }

    private static Result nearestDriver(Point passenger, Point[] drivers, int start, int end) {
        if (end - start <= 3) {
            Result best = null;
            for (int i = start; i < end; i++) {
                double distance = passenger.distance(drivers[i]);
                if (best == null || distance < best.distance) {
                    best = new Result(passenger, drivers[i], distance);
                }
            }
            return best;
        }

        int mid = start + (end - start) / 2;
        double leftBound = distanceToXRange(passenger.x, drivers[start].x, drivers[mid - 1].x);
        double rightBound = distanceToXRange(passenger.x, drivers[mid].x, drivers[end - 1].x);
        boolean searchLeftFirst = leftBound <= rightBound;
        int firstStart = searchLeftFirst ? start : mid;
        int firstEnd = searchLeftFirst ? mid : end;
        int secondStart = searchLeftFirst ? mid : start;
        int secondEnd = searchLeftFirst ? end : mid;
        double secondBound = searchLeftFirst ? rightBound : leftBound;

        Result best = nearestDriver(passenger, drivers, firstStart, firstEnd);
        if (secondBound <= best.distance) {
            Result other = nearestDriver(passenger, drivers, secondStart, secondEnd);
            if (other.distance < best.distance) {
                best = other;
            }
        }
        return best;
    }

    private static double distanceToXRange(int x, int minX, int maxX) {
        if (x < minX) return (double) minX - x;
        if (x > maxX) return (double) x - maxX;
        return 0;
    }

    private static Result closestPair(Point[] px, Point[] py) {
        int n = px.length;
        if (n <= 3) {
            return bruteForce(px);
        }
        int mid = n / 2;
        Point midPoint = px[mid];
        Point[] Qx = Arrays.copyOfRange(px, 0, mid);
        Point[] Rx = Arrays.copyOfRange(px, mid, n);
        Point[] Qy = Arrays.stream(py).filter(p -> p.x <= midPoint.x).toArray(Point[]::new);
        Point[] Ry = Arrays.stream(py).filter(p -> p.x > midPoint.x).toArray(Point[]::new);
        Result left = closestPair(Qx, Qy);
        Result right = closestPair(Rx, Ry);
        Result min = left.distance < right.distance ? left : right;
        Result split = closestSplitPair(px, py, min.distance);
        if (split != null && split.distance < min.distance) {
            return split;
        }
        return min;
    }

    private static Result closestSplitPair(Point[] px, Point[] py, double delta) {
        int n = px.length;
        int midX = px[n / 2].x;
        Point[] sy = Arrays.stream(py).filter(p -> Math.abs(p.x - midX) < delta).toArray(Point[]::new);
        double best = delta;
        Result bestPair = null;
        for (int i = 0; i < sy.length; i++) {
            for (int j = i + 1; j < sy.length && (sy[j].y - sy[i].y) < best; j++) {
                double d = sy[i].distance(sy[j]);
                if (d < best) {
                    best = d;
                    bestPair = new Result(sy[i], sy[j], d);
                }
            }
        }
        return bestPair;
    }

    private static Result bruteForce(Point[] points) {
        double minDist = Double.POSITIVE_INFINITY;
        Point p1 = null, p2 = null;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double d = points[i].distance(points[j]);
                if (d < minDist) {
                    minDist = d;
                    p1 = points[i];
                    p2 = points[j];
                }
            }
        }
        return new Result(p1, p2, minDist);
    }
}
