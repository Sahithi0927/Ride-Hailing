package algorithms;

import java.util.*;

/**
 * Greedy Job Sequencing with Deadlines algorithm for Cloud Resource Allocation.
 */
public class JobSequencing {
    public static class Job {
        public String id;
        public int deadline;
        public int profit;
        public Job(String id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    public static class Result {
        public List<Job> scheduledJobs;
        public int maxProfit;
        public Result(List<Job> scheduledJobs, int maxProfit) {
            this.scheduledJobs = scheduledJobs;
            this.maxProfit = maxProfit;
        }
    }

    public static Result scheduleJobs(List<Job> jobs) {
        List<Job> sortedJobs = new ArrayList<>(jobs);
        sortedJobs.sort(Comparator.comparingInt((Job job) -> job.profit).reversed());
        int maxDeadline = sortedJobs.stream().mapToInt(j -> j.deadline).max().orElse(0);
        Job[] slots = new Job[maxDeadline + 1];
        List<Job> result = new ArrayList<>();
        int profit = 0;
        for (Job job : sortedJobs) {
            if (job.deadline <= 0 || job.profit <= 0) continue;
            for (int j = Math.min(maxDeadline, job.deadline); j > 0; j--) {
                if (slots[j] == null) {
                    slots[j] = job;
                    profit += job.profit;
                    break;
                }
            }
        }
        for (int slot = 1; slot < slots.length; slot++) {
            if (slots[slot] != null) result.add(slots[slot]);
        }
        return new Result(result, profit);
    }
}
